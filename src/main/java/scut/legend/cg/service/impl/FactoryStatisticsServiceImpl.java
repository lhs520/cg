package scut.legend.cg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scut.legend.cg.dao.FactoryStatisticsDao;
import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.po.FactoryStatistics;
import scut.legend.cg.service.FactoryStatisticsService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;
@Service("factoryStatisticsService")
public class FactoryStatisticsServiceImpl implements FactoryStatisticsService{
	@Resource
	private FactoryStatisticsDao factoryStatisticsDao;

	@Override
	public CommonDTO getFactoryStatisticsByDate(Long beginTime, Long endTime, Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<FactoryStatistics> fList=factoryStatisticsDao.getFactoryStatisticsByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		Float ZERO=new Float(0);
		for (int i=0;i<fList.size();i++) {
			FactoryStatistics factoryStatistics=fList.get(i);
			if(factoryStatistics.getAllProduceQuantity()==null){
				factoryStatistics.setAllProduceQuantity(ZERO);
			}
			if(factoryStatistics.getAllOutStorageQuantity()==null){
				factoryStatistics.setAllOutStorageQuantity(ZERO);
			}
//			if(factoryStatistics.getAllProduceQuantity()==null&&
//					factoryStatistics.getAllOutStorageQuantity()==null){
//				fList.remove(i);
//			}
//			else {
//				if(factoryStatistics.getAllProduceQuantity()==null){
//					factoryStatistics.setAllProduceQuantity(ZERO);
//				}
//				if(factoryStatistics.getAllOutStorageQuantity()==null){
//					factoryStatistics.setAllOutStorageQuantity(ZERO);
//				}
//			}
		}
//		System.out.println(fList.size());
		
		if(pageNum==1){
			double count=factoryStatisticsDao.getCountByTime(beginTime, endTime);
			int pageCount=(int)Math.ceil(count/PageUtils.NUMBER_PER_PAGE);//参数为double类型，向上取整，得出最多需要多少页
//			System.out.println(pageCount);
//			System.out.println(count);
			CommonDTO result=new CommonDTO(Result.SUCCESS);
			result.setMsg(pageCount+"");
			result.setResult(fList);
			return result;
		}
		
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(fList);
		return result;
	}

	@Override
	public CommonDTO getFactoryStatisticsOutput(Long beginTime, Long endTime) {
		Float totalOutStorageQuantity=factoryStatisticsDao.getTotalOutStorageQuantity(beginTime,endTime);
		Float totalProductQuantity=factoryStatisticsDao.getTotalProductQuantity(beginTime,endTime);
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		Map<String, Float> map=new HashMap<>();
		map.put("totalOutStorageQuantity", totalOutStorageQuantity);
		map.put("totalProductQuantity", totalProductQuantity);
		result.setResult(map);
		return result;
	}

}
