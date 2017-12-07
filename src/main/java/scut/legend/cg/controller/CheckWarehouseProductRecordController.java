package scut.legend.cg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.CheckWarehouseProductRecord;
import scut.legend.cg.service.CheckWarehouseProductRecordService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class CheckWarehouseProductRecordController {
	@Resource
	private CheckWarehouseProductRecordService checkWarehouseProductRecordService;
	//新增一条产品清仓登记记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/createCheckWarehouseProductRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createMultiCheckWarehouseProductRecord(@RequestBody List<CheckWarehouseProductRecord> list){
		try{
			CommonDTO result=checkWarehouseProductRecordService.createMultiCheckWarehouseProductRecord(list);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			if(e instanceof NullListException){
				return new CommonDTO(Result.NULL_LIST);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	//修改一条产品清仓记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/updateCheckWarehouseProductRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneCheckWarehouseProductRecord(@PathVariable Integer id,
			@RequestBody CheckWarehouseProductRecord checkWarehouseProductRecord){
		try{
			CommonDTO result=checkWarehouseProductRecordService.updateOneCheckWarehouseProductRecord(checkWarehouseProductRecord);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
//	//获取所有产品清仓登记记录
//	@RequestMapping(value="/CheckWarehouseOthersRecord/getAllCheckWarehouseProductRecord",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllCheckWarehouseProductRecord(){
//		try{
//			CommonDTO result=checkWarehouseProductRecordService.getAllCheckWarehouseProductRecord();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//根据起始时间和结束时间筛选出产品清仓登记记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/getCheckWarehouseProductRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getCheckWarehouseProductRecordByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime){
		try{
			CommonDTO result=checkWarehouseProductRecordService.getCheckWarehouseProductRecordByDate(beginTime, endTime,pageNum);
			return result;
		}catch(ParseException e){
			e.printStackTrace();
			return new CommonDTO(Result.TIME_PARSE_ERROR);
		}
		catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
