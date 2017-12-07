package scut.legend.cg.dao;

import java.util.HashMap;
import java.util.List;

import com.alibaba.druid.support.logging.Log;

import scut.legend.cg.po.WarehouseInStorageRecord;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseInStorageRecordDao {
	
	/**
	 * 创建入库记录
	 * @param warehouseInStorageRecord 入库记录
	 */
	void createWarehouseInStorageRecord(WarehouseInStorageRecord warehouseInStorageRecord);
	
	/**
	 * 获取入库记录后缀
	 * @param time 开始时间
	 * @param endTime 结束时间
	 * @return 入库记录后缀
	 */
	int getinStorageNumSuffix(Long time,Long endTime);
	
	Integer getProductId(WarehouseInStorageRecord wisr);
	
	/**
	 * 获取指定时间段内的入库记录
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param offset 偏移量
	 * @param numPerPage 多少条记录
	 * @return 
	 */
	List<WarehouseInStorageRecord> getWarehouseInStorageRecordByTime(Long startTime,Long endTime,Integer offset,Integer numPerPage);

	/**
	 * 更新入库记录
	 * @param wisr 入库记录
	 * @return
	 */
	int updateWarehouseInStorageRecord(WarehouseInStorageRecord wisr);
	
//	WarehouseInStorageRecord getWarehouseInStorageRecordByInStorageNum(String inStorageNum);
	
	/**
	 * 根据Id获取入库记录
	 * @param id 入库记录ID
	 * @return 入库记录
	 */
	WarehouseInStorageRecord getWarehouseInStorageRecordById(Integer id);
	
	/**
	 * 获取指定时间段内的入库记录数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 记录数
	 */
	Integer getCountByTime(Long startTime,Long endTime);
}
