package scut.legend.cg.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.StaffDao;
import scut.legend.cg.dao.WarehouseDeliveryRecordDao;
import scut.legend.cg.dao.WarehouseOrderDao;
import scut.legend.cg.dao.WarehouseProductInventoryDao;
import scut.legend.cg.exception.DeliveryQuantityException;
import scut.legend.cg.exception.InventoryShortageException;
import scut.legend.cg.exception.TimeFormatException;
import scut.legend.cg.po.Staff;
import scut.legend.cg.po.WarehouseDeliveryRecord;
import scut.legend.cg.po.WarehouseOrder;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.service.WarehouseDeliveryRecordService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.SMSUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service
public class WarehouseDeliveryRecordServiceImpl implements WarehouseDeliveryRecordService {

	private static Lock lock = new ReentrantLock();
	
	@Resource
	WarehouseDeliveryRecordDao warehouseDeliveryDao;
	@Resource
	WarehouseOrderDao warehouseOrderDao;
	@Resource
	WarehouseProductInventoryDao warehouseProductInventoryDao;
	@Resource
	StaffDao staffDao;

	@Override
	@Transactional
	public CommonDTO createWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord, String orderNum) throws Exception {
		if (warehouseDeliveryRecord.getDeliveryDate() == null
				|| warehouseDeliveryRecord.getDeliveryQuantity() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		Staff staff=(Staff)SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(staff==null){
			return new CommonDTO(Result.USER_NOT_LOGIN);
		}
		warehouseDeliveryRecord.setStaff(staff);
		warehouseDeliveryRecord.setStaffName(staff.getStaffName());
		
		lock.lock();
		try {
			// 构造配送编号
			if (!constructNum(warehouseDeliveryRecord)) {
				return new CommonDTO(Result.SYSTEM_EXCEPTION);
			}
			// 设置订单
			warehouseDeliveryRecord.setWarehouseOrder(warehouseOrderDao.getWarehouseOrderByOrderNum(orderNum));

			// 更新订单需配送量,然后根据配送量设置订单状态
			Integer orderId = warehouseDeliveryRecord.getWarehouseOrder().getId();
			Float newDeliveryQuantityNeed = warehouseDeliveryRecord.getWarehouseOrder().getDeliveryQuantityNeed()
					- warehouseDeliveryRecord.getDeliveryQuantity();
			// 配送量大于订单需配送量，返回失败的代码
			if (newDeliveryQuantityNeed < 0) {
				throw new DeliveryQuantityException();
			}
			warehouseOrderDao.updateOrderDeliveryNeed(orderId, newDeliveryQuantityNeed);
			if (newDeliveryQuantityNeed == 0) {
				warehouseOrderDao.updateOrderStatus(orderId, "已配送");
			} else {
				warehouseOrderDao.updateOrderStatus(orderId, "部分配送");
			}
			// 更新产品库存
			updateInventory(warehouseDeliveryRecord);

			warehouseDeliveryDao.createWarehouseDeliveryRecord(warehouseDeliveryRecord);
			CommonDTO commonDTO = new CommonDTO(Result.CREATE_DELIVERY_SUCCESS);
			commonDTO.setResult(warehouseDeliveryRecord.getId());
			return commonDTO;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public CommonDTO getWarehouseDeliveryRecordByTime(Long startTime, Long endTime, Integer pageNum) {
		if (startTime < 0 || endTime < 0 || endTime - startTime < 0 || pageNum <= 0) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 变为当天23:59
		endTime = endTime + 86399999L;
		List<WarehouseDeliveryRecord> result = warehouseDeliveryDao.getWarehouseDeliveryRecordByTime(startTime, endTime,
				(pageNum - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (pageNum == 1) {
			double count = warehouseDeliveryDao.getCountByTime(startTime, endTime);
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
	@Transactional
	public CommonDTO updateWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord) throws Exception {
		if (warehouseDeliveryRecord.getDeliveryQuantity() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		if (warehouseDeliveryRecord.getDeliveryQuantity() != null) {
			// 纠正订单需配送量
//			WarehouseDeliveryRecord oldWdr = warehouseDeliveryDao
//					.getWarehouseDeliveryRecordByNum(warehouseDeliveryRecord.getDeliveryNum());
			WarehouseDeliveryRecord oldWdr = warehouseDeliveryDao
					.getWarehouseDeliveryRecordById(warehouseDeliveryRecord.getId());
			if (oldWdr == null) {
				return new CommonDTO(Result.DELIVERYRECORD_NOT_EXISTED);
			}
			Float var = warehouseDeliveryRecord.getDeliveryQuantity() - oldWdr.getDeliveryQuantity();
			Float newDeliveryQuantityNeed = oldWdr.getWarehouseOrder().getDeliveryQuantityNeed() - var;
			// 更新订单状态
			if (newDeliveryQuantityNeed < 0) {
				// 出错回滚
				throw new DeliveryQuantityException();
			} else if (newDeliveryQuantityNeed == 0) {
				warehouseOrderDao.updateOrderStatus(oldWdr.getWarehouseOrder().getId(), "已配送");
			} else if (newDeliveryQuantityNeed > oldWdr.getWarehouseOrder().getDeliveryQuantityTotal()) {
				return new CommonDTO(Result.NEED_BIG_TOTAL);
			} else if (Math.abs(newDeliveryQuantityNeed - oldWdr.getWarehouseOrder().getDeliveryQuantityTotal()) == 0) {
				warehouseOrderDao.updateOrderStatus(oldWdr.getWarehouseOrder().getId(), "未配送");
			} else {
				warehouseOrderDao.updateOrderStatus(oldWdr.getWarehouseOrder().getId(), "部分配送");
			}
			warehouseOrderDao.updateOrderDeliveryNeed(oldWdr.getWarehouseOrder().getId(), newDeliveryQuantityNeed);

			// 纠正产品库存
			WarehouseProductInventory warehouseProductInventory = warehouseProductInventoryDao
					.getproductInventoryByProduct(oldWdr.getWarehouseOrder().getProduct());
			warehouseProductInventory.setProduct(oldWdr.getWarehouseOrder().getProduct());
			Float newProductInventory = warehouseProductInventory.getProductInventory() - var;
			warehouseProductInventory.setProductInventory(newProductInventory);
			warehouseProductInventoryDao.updateproductInventory(warehouseProductInventory);
			// 出错回滚
			if (newProductInventory < 0) {
				throw new InventoryShortageException();
			}
//			updateInventory(oldWdr);
		}
		warehouseDeliveryDao.updateWarehouseDeliveryRecord(warehouseDeliveryRecord);
		return new CommonDTO(Result.UPDATE_DELIVERY_SUCCESS);
	}
	
	// 更新产品库存
	@Transactional
	public void updateInventory(WarehouseDeliveryRecord warehouseDeliveryRecord) throws Exception {
		WarehouseProductInventory warehouseProductInventory = warehouseProductInventoryDao
				.getproductInventoryByProduct(warehouseDeliveryRecord.getWarehouseOrder().getProduct());
		if (warehouseProductInventory == null) {
			sendSMSToFactory(warehouseDeliveryRecord.getWarehouseOrder());
			throw new InventoryShortageException();
		}
		warehouseProductInventory.setProduct(warehouseDeliveryRecord.getWarehouseOrder().getProduct());
		Float newInventory = warehouseProductInventory.getProductInventory()
				- warehouseDeliveryRecord.getDeliveryQuantity();
		if (newInventory < 0) {
			sendSMSToFactory(warehouseDeliveryRecord.getWarehouseOrder());
			throw new InventoryShortageException();
		}
		warehouseProductInventory.setProductInventory(newInventory);
		warehouseProductInventoryDao.updateproductInventory(warehouseProductInventory);
	}

	//构造入库编号
	@Transactional
	private boolean constructNum(WarehouseDeliveryRecord warehouseDeliveryRecord) {
		StringBuilder sBuilder = new StringBuilder();
		String date = TimeUtils.formatD(warehouseDeliveryRecord.getDeliveryDate());
		sBuilder.append("KP");
		sBuilder.append(date);
		Long time = warehouseDeliveryRecord.getDeliveryDate();
		try {
			time = TimeUtils.parseD(date);
//			warehouseDeliveryRecord.setDeliveryDate(time);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new TimeFormatException(e.getMessage());
		} 
		int num = warehouseDeliveryDao.getDeliveryNumSuffix(time,time+86399999L) + 1;
		if (num > 9999) {
			return false;
		}
		for (int i = 0; i < 4 - new String(num + "").length(); i++) {
			sBuilder.append("0");
		}
		sBuilder.append(num);
		warehouseDeliveryRecord.setDeliveryNum(sBuilder.toString());
		return true;
	}
	
	private void sendSMSToFactory(WarehouseOrder warehouseOrder) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("产品：");
		stringBuilder.append(warehouseOrder.getProduct().getProductModelInfo().getProductModel()+"、");
		stringBuilder.append(warehouseOrder.getProduct().getProductSize()+"、"+warehouseOrder.getProduct().getProductShape());
		stringBuilder.append(",库存不足，请尽快生产!需要量：");
		stringBuilder.append(warehouseOrder.getDeliveryQuantityNeed());
		List<Staff> list = staffDao.getStaffsByRoleName("工厂管理员");
		if (list==null) {
			return;
		}
		try {
			int index = new Random().nextInt(list.size()-1);
			SMSUtils.sendSMS(list.get(index).getStaffTel(), stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
