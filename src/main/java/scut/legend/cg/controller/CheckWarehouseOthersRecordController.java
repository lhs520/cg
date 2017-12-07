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
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.service.CheckWarehouseOthersRecordService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class CheckWarehouseOthersRecordController {
	@Resource
	private CheckWarehouseOthersRecordService checkWarehouseOthersRecordService;
	//TODO 返回清仓记录页面，还需要返回清仓记录的所有数据初始化页面
	@RequestMapping(value="/CheckWarehouseOthersRecord",method=RequestMethod.GET)
	private String checkWarehouseOthersRecord(){
		return "checkWarehouseOthersRecord";
	}
	
//	//获取所有其他清仓登记记录
//	@RequestMapping(value="/CheckWarehouseOthersRecord/getAllCheckWarehouseOthersRecord",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllCheckWarehouseOthersRecord() {
//		try{
//			CommonDTO result=checkWarehouseOthersRecordService.getAllCheckWarehouseOthersRecord();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//根据起始时间和结束时间筛选出其他清仓记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/getCheckWarehouseOthersRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getCheckWarehouseOthersRecordByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime){
		try{
			CommonDTO result=checkWarehouseOthersRecordService.getCheckWarehouseOthersRecordByDate(beginTime, endTime,pageNum);
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
	
	//新增一条其他清仓登记记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/createCheckWarehouseOthersRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createMultiCheckWarehouseOthersRecord(@RequestBody List<CheckWarehouseOthersRecord> list){
		try{
			CommonDTO result=checkWarehouseOthersRecordService.createMultiCheckWarehouseOthersRecord(list);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			if(e instanceof NullListException){
				return new CommonDTO(Result.NULL_LIST);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	//修改一条其他清仓记录
	@RequestMapping(value="/CheckWarehouseOthersRecord/updateCheckWarehouseOthersRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneCheckWarehouseOthersRecord(@PathVariable Integer id,
			@RequestBody CheckWarehouseOthersRecord checkWarehouseOthersRecord){
		try{
			CommonDTO result=checkWarehouseOthersRecordService.updateOneCheckWarehouseOthersRecord(checkWarehouseOthersRecord);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
