package scut.legend.cg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.service.FactoryStatisticsService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class FactoryStatisticsController {
	@Resource
	private FactoryStatisticsService factoryStatisticsService;
	
	//TODO 返回工厂统计页面
	@RequestMapping(value="/FactoryStatistics",method=RequestMethod.GET)
	public String FactoryStatistics(){
		return "factoryStatistics";
	}
	
	@RequestMapping(value="/FactoryStatistics/getFactoryStatistics",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getFactoryStatisticsByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam(value="beginTime") Long beginTime,
			@RequestParam(value="endTime") Long endTime){
		try{
			CommonDTO result=factoryStatisticsService.getFactoryStatisticsByDate(beginTime, endTime, pageNum);
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/FactoryStatistics/outputs",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getFactoryStatisticsOutput(@RequestParam(value="beginTime",required=false) Long beginTime,
			@RequestParam(value="endTime",required=false) Long endTime){
		try{
			CommonDTO result=factoryStatisticsService.getFactoryStatisticsOutput(beginTime, endTime);
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}

}
