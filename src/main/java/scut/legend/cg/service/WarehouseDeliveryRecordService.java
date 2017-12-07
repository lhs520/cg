package scut.legend.cg.service;

import scut.legend.cg.po.WarehouseDeliveryRecord;
import scut.legend.cg.vo.CommonDTO;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseDeliveryRecordService {

	/**
	 * 创建配送记录
	 * @param warehouseDeliveryRecord 配送记录
	 * @param orderNum 订单编号
	 * @return
	 * @throws Exception
	 */
	CommonDTO createWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord,String orderNum) throws Exception;
	
    /**
     * 获取指定时间段内的配送记录
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @param pageNum 页数
     * @return
     */
	CommonDTO getWarehouseDeliveryRecordByTime(Long startTime,Long endTime,Integer pageNum);
	
	/**
	 * 更新配送记录
	 * @param warehouseDeliveryRecord 配送记录
	 * @return
	 * @throws Exception
	 */
	CommonDTO updateWarehouseDeliveryRecord(WarehouseDeliveryRecord warehouseDeliveryRecord) throws Exception;
}
