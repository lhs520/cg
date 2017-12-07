package scut.legend.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import scut.legend.cg.po.FactoryStatistics;

public interface FactoryStatisticsDao {
	public List<FactoryStatistics> getFactoryStatisticsByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
	public Float getTotalOutStorageQuantity(@Param("beginTime")Long beginTime,@Param("endTime") Long endTime);
	public Float getTotalProductQuantity(@Param("beginTime")Long beginTime,@Param("endTime") Long endTime);

}
