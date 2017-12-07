package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.vo.CommonDTO;

public interface WarehouseInStorageRecordService {
	
	/**
	 * 创建入库记录
	 * @param warehouseInStorageRecord
	 * @return
	 * @throws Exception
	 */
	CommonDTO createWarehouseInStorageRecord(WarehouseInStorageRecord warehouseInStorageRecord) throws Exception;
	
	/**
	 * 获取指定时间段内的入库记录
	 * @param startTime 起始时间
	 * @param endTime 终止时间
	 * @param pageNum 也是
	 * @return
	 */
	CommonDTO getWarehouseInStorageRecordByTime(Long startTime,Long endTime,Integer pageNum);
	
	/**
	 * 更新入库记录
	 * @param warehouseInStorageRecord 入库记录
	 * @return
	 * @throws Exception
	 */
	CommonDTO updateWarehouseInStorageRecord(WarehouseInStorageRecord warehouseInStorageRecord) throws Exception;
}
