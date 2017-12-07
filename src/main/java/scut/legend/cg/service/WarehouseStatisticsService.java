package scut.legend.cg.service;

import scut.legend.cg.vo.CommonDTO;

public interface WarehouseStatisticsService {
	
	/**
	 * 获取指定时间段内的仓库统计
	 * @param startTime 起始时间
	 * @param endTime 终止时间
	 * @param pageNum 页数
	 * @return
	 */
	CommonDTO getStatistics(Long startTime,Long endTime,Integer pageNum);
	
	CommonDTO getTotal(Long startTime,Long endTime);
}
