package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.WarehouseStatistics;

/**
 * 仓库统计DAO
 * @author yaoyou
 *
 */
public interface WarehouseStatisticsDao {
//	List<WarehouseStatistics> getInStorageStatistics(Long startTime,Long endTime,Integer offset,Integer pageNum);
	
	/**
	 * 获取指定时间段内的入库统计
	 * @param startTime  开始时间
	 * @param endTime 结束时间
	 * @param offset 偏移量
	 * @param pageNum 返回多少条记录
	 * @return 
	 */
	List<WarehouseStatistics> getAllStatistics(Long startTime,Long endTime,Integer offset,Integer pageNum);
	
	/**
	 * 获取所有的统计数
	 * @return
	 */
	Integer getAllStatisticsCount();
	
	Float getTotalInstorage(Long startTime,Long endTime);
	
	Float getTotalDelivery(Long startTime,Long endTime);
}
