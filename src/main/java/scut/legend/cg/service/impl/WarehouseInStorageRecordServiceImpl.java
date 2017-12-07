package scut.legend.cg.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.WarehouseInStorageRecordDao;
import scut.legend.cg.dao.WarehouseProductInventoryDao;
import scut.legend.cg.exception.InventoryShortageException;
import scut.legend.cg.exception.TimeFormatException;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.service.WarehouseInStorageRecordService;
import scut.legend.cg.service.WarehouseProductInventoryService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;
import sun.tools.jar.resources.jar;

@Service
public class WarehouseInStorageRecordServiceImpl implements WarehouseInStorageRecordService {

	private static Lock lock = new ReentrantLock();

	@Resource
	private WarehouseInStorageRecordDao warehouseInStorageRecordDao;

	@Resource
	private WarehouseProductInventoryDao warehouseProductInventoryDao;

	@Override
	@Transactional
	public CommonDTO createWarehouseInStorageRecord(WarehouseInStorageRecord warehouseInStorageRecord)
			throws Exception {
		if (warehouseInStorageRecord.getInStorageDate() == null
				|| warehouseInStorageRecord.getInStorageQuantity() == null
				|| warehouseInStorageRecord.getInStorageQuantity() < 0
				|| warehouseInStorageRecord.getProduct() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 如果产品ID为空，则要根据产品型号，规格，形态查找产品ID
		if (warehouseInStorageRecord.getProduct().getProductId() == null) {
			if (warehouseInStorageRecord.getProduct().getProductShape() == null
					|| warehouseInStorageRecord.getProduct().getProductSize() == null
					|| warehouseInStorageRecord.getProduct().getProductModelInfo() == null
					|| warehouseInStorageRecord.getProduct().getProductModelInfo().getProductModel() == null) {
				return new CommonDTO(Result.PARAM_ERROR);
			}
			Integer productId = warehouseInStorageRecordDao.getProductId(warehouseInStorageRecord);
			if (productId == null)
				return new CommonDTO(Result.PRODUCT_NOT_EXISTED);
			warehouseInStorageRecord.getProduct().setProductId(productId);
		}
		Staff staff = (Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if (staff == null) {
			return new CommonDTO(Result.USER_NOT_LOGIN);
		}
		warehouseInStorageRecord.setStaff(staff);
		warehouseInStorageRecord.setStaffName(staff.getStaffName());

		lock.lock();
		try {
			// 构造入库编号
			if (!constructNum(warehouseInStorageRecord)) {
				return new CommonDTO(Result.SYSTEM_EXCEPTION);
			}
			// 每次入库后都要更新产品库存
			updateProductInventory(warehouseInStorageRecord);

			warehouseInStorageRecordDao.createWarehouseInStorageRecord(warehouseInStorageRecord);
			CommonDTO commonDTO = new CommonDTO(Result.CREATE_WAREHOUSEINSTORAGE_SUCCESS);
			commonDTO.setResult(warehouseInStorageRecord.getInStorageNum());
			return commonDTO;
		} finally {
			lock.unlock();
		}

	}

	// 根据时间范围查找入库记录
	@Override
	public CommonDTO getWarehouseInStorageRecordByTime(Long startTime, Long endTime, Integer pageNum) {
		if (startTime < 0 || endTime < 0 || endTime - startTime < 0 || pageNum <= 0) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 变为当天23:59
		endTime = endTime + 86399999L;
		List<WarehouseInStorageRecord> result = warehouseInStorageRecordDao.getWarehouseInStorageRecordByTime(startTime,
				endTime, (pageNum - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (pageNum == 1) {
			double count = warehouseInStorageRecordDao.getCountByTime(startTime, endTime);
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

	// 修改入库记录
	@Override
	@Transactional
	public CommonDTO updateWarehouseInStorageRecord(WarehouseInStorageRecord warehouseInStorageRecord)
			throws Exception {
		if (warehouseInStorageRecord.getId() == null || warehouseInStorageRecord.getInStorageQuantity() == null
				|| warehouseInStorageRecord.getInStorageQuantity() < 0 || warehouseInStorageRecord.getProduct() == null
				|| warehouseInStorageRecord.getProduct().getProductModelInfo() == null
				|| warehouseInStorageRecord.getProduct().getProductShape() == null
				|| warehouseInStorageRecord.getProduct().getProductSize() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 根据入库编号找出旧入库记录
		WarehouseInStorageRecord OldWisr = warehouseInStorageRecordDao
				.getWarehouseInStorageRecordById(warehouseInStorageRecord.getId());
		if (OldWisr == null) {
			return new CommonDTO(Result.RECORD_NOT_EXISTED);
		}
		// 根据产品信息设置产品ID
		Integer productId = warehouseInStorageRecordDao.getProductId(warehouseInStorageRecord);
		if (productId == null) {
			return new CommonDTO(Result.PRODUCT_NOT_EXISTED);
		} else {
			warehouseInStorageRecord.getProduct().setProductId(productId);
		}

		if (warehouseInStorageRecordDao.updateWarehouseInStorageRecord(warehouseInStorageRecord) == 1) {
			Float oldInStorageQuantity = OldWisr.getInStorageQuantity();
			if (warehouseInStorageRecord.getInStorageQuantity() != null
					&& !(Math.abs(warehouseInStorageRecord.getInStorageQuantity() - oldInStorageQuantity) == 0)) {
				if (warehouseInStorageRecord.getProduct().getProductId() == OldWisr.getProduct().getProductId()) {
					// 入库产品不变，入库量变化，只需改变单一产品的库存
					Float var = warehouseInStorageRecord.getInStorageQuantity() - oldInStorageQuantity;
					warehouseInStorageRecord.setInStorageQuantity(var);
					lock.lock();
					try {
						updateProductInventory(warehouseInStorageRecord);
					} finally {
						lock.unlock();
					}
				} else {
					// 入库产品变化，入库量也变化，两种产品的库存都要更新
					// 新产品的库存要增加修改后的入库量
					updateProductInventory(warehouseInStorageRecord);
					// 旧产品的入库量要减去旧的入库量
					OldWisr.setInStorageQuantity(-OldWisr.getInStorageQuantity());
					updateProductInventory(OldWisr);
				}
			} else {
				if (warehouseInStorageRecord.getProduct().getProductId() != OldWisr.getProduct().getProductId()) {
					// 入库量没有变化，入库产品被修改，两种产品的库存都要修改
					// 修改产品的库存要相应增加oldInStorageQuantity
					warehouseInStorageRecord.setInStorageQuantity(OldWisr.getInStorageQuantity());
					lock.lock();
					try {
						updateProductInventory(warehouseInStorageRecord);
						// 原产品的库存要相应减少oldInStorageQuantity
						OldWisr.setInStorageQuantity(-OldWisr.getInStorageQuantity());
						updateProductInventory(OldWisr);
					} finally {
						lock.unlock();
					}

				}
			}
			return new CommonDTO(Result.UPDATE_WAREHOUSEINSTORAGE_SUCCESS);
		} else {
			return new CommonDTO();
		}
	}

	// 更新产品库存
	@Transactional
	private void updateProductInventory(WarehouseInStorageRecord warehouseInStorageRecord) throws Exception {
		WarehouseProductInventory warehouseProductInventory = warehouseProductInventoryDao
				.getproductInventoryByProduct(warehouseInStorageRecord.getProduct());
		// 如果该产品没有库存记录，则新增
		if (warehouseProductInventory == null) {
			warehouseProductInventory = new WarehouseProductInventory();
			warehouseProductInventory.setProduct(warehouseInStorageRecord.getProduct());
			warehouseProductInventory.setProductInventory(warehouseInStorageRecord.getInStorageQuantity());
			warehouseProductInventoryDao.createProductInventory(warehouseProductInventory);
		} else {
			Float newProductInventory = warehouseProductInventory.getProductInventory()
					+ warehouseInStorageRecord.getInStorageQuantity();
			if (newProductInventory < 0) {
				throw new InventoryShortageException();
			}
			warehouseProductInventory.setProduct(warehouseInStorageRecord.getProduct());
			warehouseProductInventory.setProductInventory(newProductInventory);
			warehouseProductInventoryDao.updateproductInventory(warehouseProductInventory);
		}

	}

	// 构造入库编号
	@Transactional
	private boolean constructNum(WarehouseInStorageRecord warehouseInStorageRecord) {
		StringBuilder sBuilder = new StringBuilder();
		String date = TimeUtils.formatD(warehouseInStorageRecord.getInStorageDate());
		sBuilder.append("KR");
		sBuilder.append(date);
		Long time = warehouseInStorageRecord.getInStorageDate();
		try {
			time = TimeUtils.parseD(date);
			// warehouseInStorageRecord.setInStorageDate(time);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new TimeFormatException(e.getMessage());
		}
		int num = warehouseInStorageRecordDao.getinStorageNumSuffix(time, time + 86399999L) + 1;
		if (num > 9999) {
			return false;
		}
		for (int i = 0; i < 4 - new String(num + "").length(); i++) {
			sBuilder.append("0");
		}
		sBuilder.append(num);
		String inStorageNum = sBuilder.toString();
		warehouseInStorageRecord.setInStorageNum(inStorageNum);
		return true;
	}
}
