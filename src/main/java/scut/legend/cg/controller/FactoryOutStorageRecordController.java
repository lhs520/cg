package scut.legend.cg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.service.FactoryOutStorageRecordService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class FactoryOutStorageRecordController {
	@Resource
	private FactoryOutStorageRecordService factoryOutStorageRecordService;
	//返回工厂出库页面，还需返回数据
	@RequestMapping(value="/FactoryOutStorageRecord",method=RequestMethod.GET)
	public String factoryOutStorageRecord(){
		return "factoryOutStorageRecord";
	}
	//新增一条工厂出库记录
	@RequestMapping(value="/FactoryOutStorageRecord/createFactoryOutStorageRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createOneFactoryOutStorageRecord(@RequestBody FactoryOutStorageRecord factoryOutStorageRecord){
		try{
			CommonDTO result=factoryOutStorageRecordService.createOneFactoryOutStorageRecord(factoryOutStorageRecord);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	//修改一条工厂出库记录
	@RequestMapping(value="/FactoryOutStorageRecord/updateFactoryOutStorageRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneFactoryOutStorageRecord(@PathVariable Integer id,
			@RequestBody FactoryOutStorageRecord factoryOutStorageRecord){
		try{
			CommonDTO result=factoryOutStorageRecordService.updateOneFactoryOutStorageRecord(factoryOutStorageRecord);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
//	//获取所有工厂出库记录
//	@RequestMapping(value="/FactoryOutStorageRecord/getAllFactoryOutStorageRecord",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllFactoryOutStorageRecord(){
//		try{
//			CommonDTO result=factoryOutStorageRecordService.getAllFactoryOutStorageRecord();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//根据起始时间和结束时间筛选出工厂出库记录
	@RequestMapping(value="/FactoryOutStorageRecord/getFactoryOutStorageRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getFactoryOutStorageRecordByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime){
		try{
			CommonDTO result=factoryOutStorageRecordService.getFactoryOutStorageRecordByDate(beginTime, endTime,pageNum);
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
