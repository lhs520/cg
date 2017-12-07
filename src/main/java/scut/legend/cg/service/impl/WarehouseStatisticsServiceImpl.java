package scut.legend.cg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import scut.legend.cg.dao.WarehouseStatisticsDao;
import scut.legend.cg.po.WarehouseStatistics;
import scut.legend.cg.service.WarehouseStatisticsService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service
public class WarehouseStatisticsServiceImpl implements WarehouseStatisticsService {

	@Resource
	WarehouseStatisticsDao warehouseStatisticsDao;

	@Override
	public CommonDTO getStatistics(Long startTime, Long endTime, Integer pageNum) {
		if (startTime < 0 || endTime < 0 || endTime - startTime < 0 || pageNum <= 0) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		// 变为当天23:59
		endTime = endTime + 86399999L;
		List<WarehouseStatistics> result = warehouseStatisticsDao.getAllStatistics(startTime, endTime,
				(pageNum - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		for (WarehouseStatistics warehouseStatistics : result) {
			if (warehouseStatistics.getAllDeliveryQuantity() == null) {
				warehouseStatistics.setAllDeliveryQuantity(0F);
			}
			if (warehouseStatistics.getAllInStorageQuantity() == null) {
				warehouseStatistics.setAllInStorageQuantity(0F);
			}
		}
		 //如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (pageNum == 1) {
			double count = warehouseStatisticsDao.getAllStatisticsCount();
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

	@Override
	public CommonDTO getTotal(Long startTime, Long endTime) {
		Float totalInstorage = warehouseStatisticsDao.getTotalInstorage(startTime, endTime);
		Float totalDelivery  = warehouseStatisticsDao.getTotalDelivery(startTime, endTime);
		HashMap<String, Float> result = new HashMap<String,Float>();
		result.put("totalInstorage", totalInstorage);
		result.put("totalDelivery", totalDelivery);
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(result);
		return commonDTO;
	}

}
