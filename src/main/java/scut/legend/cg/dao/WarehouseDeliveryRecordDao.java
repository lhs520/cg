package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.WarehouseDeliveryRecord;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseDeliveryRecordDao {

	/**
	 * 创建配送记录
	 * @param warehouseDeliveryRecord 配送记录
	 */
	void createWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord);
	
	/**
	 * 根据时间获取配送编号后缀
	 * @param time 开始时间
	 * @param endTime 结束时间
	 * @return 配送编号后缀
	 */
	int getDeliveryNumSuffix(Long time,Long endTime);
	
	/**
	 * 根据时间获取配送记录
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param offset 偏移量
	 * @param pageNum 多少条记录
	 * @return 
	 */
	List<WarehouseDeliveryRecord> getWarehouseDeliveryRecordByTime(Long startTime,Long endTime,Integer offset,Integer pageNum);
	
	/**
	 * 更新配送记录
	 * @param warehouseDeliveryRecord 配送记录
	 * @return
	 */
	Integer updateWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord);
	
//	WarehouseDeliveryRecord getWarehouseDeliveryRecordByNum(String deliveryNum);
	
	/**
	 * 根据ID查询配送记录
	 * @param id 配送记录ID
	 * @return 配送记录
	 */
	WarehouseDeliveryRecord getWarehouseDeliveryRecordById(Integer id);
	
	/**
	 * 获取指定时间段内配送记录数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Integer getCountByTime(Long startTime,Long endTime);
}
