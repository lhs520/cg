package scut.legend.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import scut.legend.cg.exception.MaterialInventoryException;
import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.service.BlowonRecordService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class BlowonRecordController {
	@Resource
	private BlowonRecordService blowonRecordService;
	//返回开炉记录页面
	@RequestMapping(value="/BlowonRecord",method=RequestMethod.GET)
	public String blowonRecord(){
		return "blowonRecord";
	}
//	@RequestMapping(value="/BlowonRecord/getAllBlowonRecord",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllBlowonRecord(){
//		try{
//			CommonDTO result=blowonRecordService.getAllBlowonRecord();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//添加开炉登记信息
	@RequestMapping(value="/BlowonRecord/createBlowonRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createMultiBlowonRecord(@RequestBody List<BlowonRecord> list){
		try{
			CommonDTO result=blowonRecordService.createMultiBlowonRecord(list);
			return result;
		}catch(NullListException e){
			return new CommonDTO(Result.NULL_LIST);
		}
		catch(MaterialInventoryException e){
			CommonDTO result=new CommonDTO(Result.MATERIAL_NOT_ENOUGH);
			result.setMsg(e.getMessage());
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	// 修改一条开炉记录
	@RequestMapping(value="/BlowonRecord/updateBlowonRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneBlowonRecord(@PathVariable Integer id,
			@RequestBody BlowonRecord blowonRecord){
		try{
			CommonDTO result=blowonRecordService.updateOneBlowonRecord(blowonRecord);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	//根据起始时间和结束时间筛选返回开炉记录信息
	@RequestMapping(value="/BlowonRecord/getBlowonRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getBlowonRecordByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime) {
		try{
			CommonDTO result=blowonRecordService.getBlowonRecordByDate(beginTime,endTime,pageNum);
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
