package scut.legend.cg.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.CustomerDao;
import scut.legend.cg.dao.FactoryProductInventoryDao;
import scut.legend.cg.dao.ProductDao;
import scut.legend.cg.dao.StaffDao;
import scut.legend.cg.dao.WarehouseOrderDao;
import scut.legend.cg.dao.WarehouseProductInventoryDao;
import scut.legend.cg.exception.TimeFormatException;
import scut.legend.cg.po.Customer;
import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.po.WarehouseOrder;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.service.WarehouseOrderService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.SMSUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service
public class WarehouseOrderServiceImpl implements WarehouseOrderService {

	private static Lock lock = new ReentrantLock();

	@Resource
	WarehouseOrderDao warehouseOrderDao;

	@Resource
	CustomerDao customerDao;

	@Resource
	ProductDao productDao;

	@Resource
	StaffDao staffDao;

	@Resource
	FactoryProductInventoryDao factoryProductInventoryDao;
	
	@Resource
	WarehouseProductInventoryDao warehouseProductInventoryDao;

	@Override
	@Transactional
	public CommonDTO createOrder(WarehouseOrder warehouseOrder) {
		if (warehouseOrder.getCustomer() == null || warehouseOrder.getCustomer().getCustomerAddress() == null
				|| warehouseOrder.getCustomer().getCustomerName() == null
				|| warehouseOrder.getCustomer().getCustomerPhone() == null || warehouseOrder.getDeliveryDate() == null
				|| warehouseOrder.getDeliveryQuantityTotal() == null || warehouseOrder.getOrderDate() == null
				|| warehouseOrder.getProduct() == null || warehouseOrder.getUnitPrice() == null
				|| warehouseOrder.getWarehouseManagerId() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}

		Staff staff = (Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if (staff == null) {
			return new CommonDTO(Result.USER_NOT_LOGIN);
		}
		warehouseOrder.setStaff(staff);
		warehouseOrder.setStaffName(staff.getStaffName());

		// 如果产品ID为空，根据前端传过来的产品型号、规格、形态查找产品ID，若该产品不存在，则新建该产品
		if (warehouseOrder.getProduct().getProductId() == null) {
			if (warehouseOrder.getProduct().getProductModelInfo().getProductModel() == null
					|| warehouseOrder.getProduct().getProductShape() == null
					|| warehouseOrder.getProduct().getProductSize() == null) {
				return new CommonDTO(Result.PARAM_ERROR);
			}
			Integer productId = warehouseOrderDao.getProductId(warehouseOrder);
			if (productId == null) {
				// return new CommonDTO(Result.PRODUCT_NOT_EXISTED);
				productDao.createOneProduct(warehouseOrder.getProduct());
				warehouseOrder.getProduct().setProductId(
						productDao.getOneProduct(warehouseOrder.getProduct().getProductModelInfo().getProductModel(),
								warehouseOrder.getProduct().getProductSize(),
								warehouseOrder.getProduct().getProductShape()).getProductId());

				FactoryProductInventory factoryProductInventory = new FactoryProductInventory();
				factoryProductInventory.setProduct(warehouseOrder.getProduct());
				factoryProductInventory.setProductInventory(0.00F);
				factoryProductInventoryDao.createOneFactoryProductInventory(factoryProductInventory);
				// 发短信提醒工厂生产
				sendSMSToFactory(warehouseOrder);
			} else {
				warehouseOrder.getProduct().setProductId(productId);
				//获取该工厂现有库存
				WarehouseProductInventory warehouseProductInventory = warehouseProductInventoryDao.getproductInventoryByProduct(warehouseOrder.getProduct());
				if (warehouseProductInventory.getProductInventory()<warehouseOrder.getDeliveryQuantityTotal()) {
					//库存不足发短信提醒工厂生产
					sendSMSToFactory(warehouseOrder);
				}
				
			}
		}

		// TODO (暂时)从数据库查询客户ID
		Integer customerId = warehouseOrderDao.getCustomerId(warehouseOrder.getCustomer().getCustomerName(),
				warehouseOrder.getCustomer().getCustomerAddress(), warehouseOrder.getCustomer().getCustomerPhone());
		if (customerId == null) {
			// 客户信息不存在，新增客户信息
			warehouseOrderDao.createCustomer(warehouseOrder.getCustomer());
		} else {
			// 否则将现有的客户ID传入
			warehouseOrder.getCustomer().setCustomerId(customerId);
		}

		// 刚下单需配送量等于总量，配送状态为未配送
		warehouseOrder.setDeliveryQuantityNeed(warehouseOrder.getDeliveryQuantityTotal());
		warehouseOrder.setOrderStatus("未配送");
		// 计算总价
		Float totalPrice = warehouseOrder.getUnitPrice() * warehouseOrder.getDeliveryQuantityTotal();
		warehouseOrder.setTotalPrice(totalPrice);

		lock.lock();
		try {
			// 生成订单编号
			if (!constructNum(warehouseOrder)) {
				return new CommonDTO(Result.SYSTEM_EXCEPTION);
			}

			// 给仓库管理员发送通知短信
			sendSMS(warehouseOrder);
			warehouseOrderDao.createOrder(warehouseOrder);
			return new CommonDTO(Result.CREATE_ORDER_SUCCESS);
		} finally {
			lock.unlock();
		}

	}

	@Override
	public CommonDTO getWarehouseOrderByOrderNum(String orderNum) {
		WarehouseOrder warehouseOrder = warehouseOrderDao.getWarehouseOrderByOrderNum(orderNum);
		if (warehouseOrder == null) {
			return new CommonDTO(Result.ORDER_NOT_EXISTED);
		}
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(warehouseOrder);
		return commonDTO;
	}

	@Override
	public CommonDTO getWarehouseOrderToDelivery() {
		Integer warehouseManagerId = null;
		// Staff
		// staff=(Staff)SecurityUtils.getSubject().getSession().getAttribute("staff");
		// if(staff==null){
		// return new CommonDTO(Result.USER_NOT_LOGIN);
		// }
		// String staffRole=staff.getRole().getRoleDescription();
		// if (staffRole.equals("仓库管理员")) {
		// warehouseManagerId = staff.getStaffId();
		// }
		List<WarehouseOrder> result = warehouseOrderDao.getWarehouseOrderToDelivery(warehouseManagerId);
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(result);
		return commonDTO;
	}

	@Override
	public CommonDTO getWarehouseOrderByTime(Long startTime, Long endTime, Integer pageNum) {
		if (startTime < 0 || endTime < 0 || endTime - startTime < 0 || pageNum <= 0) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 变为当天23:59
		endTime = endTime + 86399999L;
		List<WarehouseOrder> result = warehouseOrderDao.getWarehouseOrderByTime(startTime, endTime,
				(pageNum - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (pageNum == 1) {
			double count = warehouseOrderDao.getCountByTime(startTime, endTime);
			CommonDTO commonDTO = new CommonDTO();
			int page = (int) Math.ceil(count / PageUtils.NUMBER_PER_PAGE);
			commonDTO.setMsg("" + page);
			commonDTO.setResult(result);
			return commonDTO;
		}
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(result);
		return commonDTO;
	}

	@Override
	public CommonDTO updateWarehouseOrder(WarehouseOrder warehouseOrder) {
		/*
		 * 1.判断参数 2.根据编号获取旧订单. 3.更改配送总量，计算总价. 4.修改单价，计算总价. 5.修改产品 6.修改客户
		 * 7.修改最晚送达时间.
		 */
		if (warehouseOrder.getId() == null || warehouseOrder.getCustomer() == null
				|| warehouseOrder.getCustomer().getCustomerAddress().equals("")
				|| warehouseOrder.getCustomer().getCustomerName().equals("")
				|| warehouseOrder.getCustomer().getCustomerPhone().equals("")
				|| warehouseOrder.getDeliveryQuantityTotal() == null || warehouseOrder.getUnitPrice() == null
				|| warehouseOrder.getProduct() == null || warehouseOrder.getProduct().getProductModelInfo() == null
				|| warehouseOrder.getProduct().getProductShape() == null
				|| warehouseOrder.getProduct().getProductSize() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		//获取旧订单
		WarehouseOrder oldWo = warehouseOrderDao.getWarehouseOrderById(warehouseOrder.getId());
		if (oldWo == null) {
			return new CommonDTO(Result.ORDER_NOT_EXISTED);
		}
		//订单总量和价格都变化
		if (Math.abs(warehouseOrder.getDeliveryQuantityTotal() - oldWo.getDeliveryQuantityTotal()) != 0
				&& Math.abs(warehouseOrder.getUnitPrice()-oldWo.getUnitPrice()) != 0){
//			warehouseOrder.getDeliveryQuantityTotal() != null && warehouseOrder.getUnitPrice() != null
			Float alreadyDelivey = oldWo.getDeliveryQuantityTotal() - oldWo.getDeliveryQuantityNeed();
			warehouseOrder.setDeliveryQuantityNeed(warehouseOrder.getDeliveryQuantityTotal() - alreadyDelivey);
			if (!updateOrderStatus(warehouseOrder)) {
				return new CommonDTO(Result.DELIVERY_NEED_TOO_SMALL);
			}
			if (warehouseOrder.getUnitPrice() < 0) {
				return new CommonDTO(Result.UNITPRICE_TOO_SMALL);
			}
			warehouseOrder.setTotalPrice(warehouseOrder.getDeliveryQuantityTotal() * warehouseOrder.getUnitPrice());
		} else if (Math.abs(warehouseOrder.getDeliveryQuantityTotal()-oldWo.getDeliveryQuantityTotal()) != 0) {
			//只是订单总量发生变化
//			warehouseOrder.getDeliveryQuantityTotal() != null
			Float alreadyDelivey = oldWo.getDeliveryQuantityTotal() - oldWo.getDeliveryQuantityNeed();
			warehouseOrder.setDeliveryQuantityNeed(warehouseOrder.getDeliveryQuantityTotal() - alreadyDelivey);
			if (!updateOrderStatus(warehouseOrder)) {
				return new CommonDTO(Result.DELIVERY_NEED_TOO_SMALL);
			}
			warehouseOrder.setTotalPrice(warehouseOrder.getDeliveryQuantityTotal() * oldWo.getUnitPrice());
		} else if (Math.abs(warehouseOrder.getUnitPrice()-oldWo.getUnitPrice())!=0) {
			//只是订单价格发生变化
//			warehouseOrder.getUnitPrice() != null
			if (warehouseOrder.getUnitPrice() < 0) {
				return new CommonDTO(Result.UNITPRICE_TOO_SMALL);
			}
			warehouseOrder.setTotalPrice(warehouseOrder.getUnitPrice() * oldWo.getDeliveryQuantityTotal());
		}
//		Product product = productDao.getOneProduct(warehouseOrder.getProduct().getProductModelInfo().getProductModel(),
//				warehouseOrder.getProduct().getProductSize(), warehouseOrder.getProduct().getProductShape());
//		if (product == null) {
//			return new CommonDTO(Result.PRODUCT_NOT_EXISTED);
//		} else {
//			// 订单已开始配送，不能修改产品
//			if (!oldWo.getOrderStatus().equals("未配送")
//					&& oldWo.getProduct().getProductId()!=product.getProductId()) {
//				CommonDTO commonDTO = new CommonDTO();
//				commonDTO.setMsg("订单已配送，不能修改");
//				return commonDTO;
//			}
//			warehouseOrder.setProduct(product);
//		}
		//查找客户ID，没有则新增
		Integer customerId = warehouseOrderDao.getCustomerId(warehouseOrder.getCustomer().getCustomerName(),
				warehouseOrder.getCustomer().getCustomerAddress(), warehouseOrder.getCustomer().getCustomerPhone());
		if (customerId == null) {
			warehouseOrderDao.createCustomer(warehouseOrder.getCustomer());
		} else {
			warehouseOrder.getCustomer().setCustomerId(customerId);
		}
		//更新仓库管理员
		if (warehouseOrder.getWarehouseManagerId() == oldWo.getWarehouseManagerId()) {
			warehouseOrder.setWarehouseManagerId(null);
		}else {
			//发送短信通知
			sendSMS(warehouseOrder);
		}
		warehouseOrderDao.updateOrder(warehouseOrder);
		return new CommonDTO(Result.UPDATE_ORDER_SUCCESS);
	}

	@Override
	public CommonDTO getWarehouseOrders(Integer page, String orderNum, Long beginTime, Long endTime, String orderStatus,
			String customerName) {
		// 变为当天23:59
		endTime = endTime + 86399999L;
		List<WarehouseOrder> result = warehouseOrderDao.getWarehouseOrders(orderNum, beginTime, endTime, orderStatus,
				customerName, (page - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		if (result.isEmpty()) {
			return new CommonDTO(Result.ORDER_NOT_EXISTED);
		}
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (page == 1) {
			double count = warehouseOrderDao.getWarehouseOrdersCount(orderNum, beginTime, endTime, orderStatus,
					customerName);
			CommonDTO commonDTO = new CommonDTO();
			int pageNum = (int) Math.ceil(count / PageUtils.NUMBER_PER_PAGE);
			commonDTO.setMsg("" + pageNum);
			commonDTO.setResult(result);
			return commonDTO;
		}
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(result);
		return commonDTO;
	}

	@Transactional
	private boolean constructNum(WarehouseOrder warehouseOrder) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("XS");
		String date = TimeUtils.formatD(warehouseOrder.getOrderDate());
		sBuilder.append(date);
		Long time = warehouseOrder.getOrderDate();
		try {
			time = TimeUtils.parseD(date);
			// warehouseOrder.setOrderDate(time);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new TimeFormatException(e.getMessage());
		}
		int num = warehouseOrderDao.getWarehouseOrderNumSuffix(time, time + 86399999L) + 1;
		if (num > 9999) {
			return false;
		}
		for (int i = 0; i < 4 - new String(num + "").length(); i++) {
			sBuilder.append("0");
		}
		sBuilder.append(num);
		warehouseOrder.setOrderNum(sBuilder.toString());
		return true;
	}
	
	/**
	 * 更新订单状态
	 * @param warehouseOrder
	 * @return 
	 */
	private boolean updateOrderStatus(WarehouseOrder warehouseOrder){
		if (warehouseOrder.getDeliveryQuantityNeed() < 0) {
			return false;
		}else if (warehouseOrder.getDeliveryQuantityNeed() == 0) {
			warehouseOrderDao.updateOrderStatus(warehouseOrder.getId(), "已配送");
			return true;
		}else {
			warehouseOrderDao.updateOrderStatus(warehouseOrder.getId(), "部分配送");
			return true;
		}
	}

	private void sendSMS(WarehouseOrder warehouseOrder) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("已为您自动接单，订单号为：");
		stringBuilder.append(warehouseOrder.getOrderNum());
		stringBuilder.append(";请尽快处理");
		Staff deliveryman = staffDao.getStaffById(warehouseOrder.getWarehouseManagerId());
		try {
			SMSUtils.sendSMS(deliveryman.getStaffTel(), stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendSMSToFactory(WarehouseOrder warehouseOrder) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("产品：");
		stringBuilder.append(warehouseOrder.getProduct().getProductModelInfo().getProductModel()+"、");
		stringBuilder.append(warehouseOrder.getProduct().getProductSize()+"、"+warehouseOrder.getProduct().getProductShape());
		stringBuilder.append(",库存不足，请尽快生产!需要量：");
		stringBuilder.append(warehouseOrder.getDeliveryQuantityTotal());
		List<Staff> list = staffDao.getStaffsByRoleName("工厂管理员");
		if (list==null) {
			return;
		}
		try {
			int fmCount = list.size();
			int index = new Random().nextInt(fmCount-1);
			SMSUtils.sendSMS(list.get(index).getStaffTel(), stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CommonDTO getTotalPrice(Long beginTime, Long endTime) {
		Float totalPrice = warehouseOrderDao.getTotalPrice(beginTime, endTime);
		CommonDTO result = new CommonDTO(Result.SUCCESS);
		result.setResult(totalPrice);
		return result;
	}

}
