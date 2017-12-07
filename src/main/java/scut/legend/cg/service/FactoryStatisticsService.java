package scut.legend.cg.service;

import scut.legend.cg.vo.CommonDTO;

public interface FactoryStatisticsService {
	public CommonDTO getFactoryStatisticsByDate(Long beginTime,Long endTime,Integer pageNum);

	public CommonDTO getFactoryStatisticsOutput(Long beginTime, Long endTime);
}
