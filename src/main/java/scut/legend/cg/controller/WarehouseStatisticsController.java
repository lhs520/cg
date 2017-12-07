package scut.legend.cg.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import scut.legend.cg.service.WarehouseStatisticsService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class WarehouseStatisticsController {
	
	@Resource
	WarehouseStatisticsService warehouseStatisticsService;
	
	@RequestMapping(value="/warehouseStatistics",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getwarehouseStatistics(@RequestParam(defaultValue="1") Integer pageNum,
											@RequestParam(defaultValue="0") Long beginTime,
											@RequestParam(defaultValue="253370736000000") Long endTime){
		try {
			CommonDTO commonDTO = warehouseStatisticsService.getStatistics(beginTime, endTime, pageNum);
			return commonDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/warehouseStatistics/total",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getTotal(@RequestParam(defaultValue="0") Long beginTime,
							 @RequestParam(defaultValue="253370736000000") Long endTime){
		try {
			CommonDTO commonDTO = warehouseStatisticsService.getTotal(beginTime, endTime);
			return commonDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
