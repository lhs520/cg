package scut.legend.cg.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.service.BlankRecordService;
import scut.legend.cg.service.BlowonRecordService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class BlankRecordController {
//	@Resource
//	private BlankRecordService blankRecordService;
//	
//	//胚料登记时根据产品型号和开炉日期获取原料消耗总量
//	@RequestMapping(value="/BlowonRecord/blankRecord/{productModel}/{blowonDate}",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getMaterialConsumeFromBlowonRecordByProductModelAndBlowonDate(@PathVariable String productModel,
//																				   @PathVariable Long blowonDate){
//		try{
//			CommonDTO result=blankRecordService.getMaterialConsumeFromBlowonRecordByProductModelAndBlowonDate(productModel, blowonDate);
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
//	//添加胚料登记信息
//	@RequestMapping(value="/BlowonRecord/createBlankRecord",method=RequestMethod.POST)
//	@ResponseBody
//	public CommonDTO createMultiBlankRecord(@RequestBody List<BlankRecord> list){
//		try{
//			CommonDTO result=blankRecordService.createMultiBlankRecord(list);
//			return result;
//		}catch(NullListException e){
//			e.printStackTrace();
//			return new CommonDTO(Result.NULL_LIST);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
//	//修改一条胚料记录
//	@RequestMapping(value="/BlowonRecord/updateBlankRecord/{id}",method=RequestMethod.PUT)
//	@ResponseBody
//	public CommonDTO updateOneBlankRecord(@PathVariable Integer id,
//			@RequestBody BlankRecord blankRecord){
//		try{
//			CommonDTO result=blankRecordService.updateOneBlankRecord(blankRecord);
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
//	
////	//获取所有开炉和对应的胚料记录，用于损耗比查询
////	@RequestMapping(value="/BlowonRecord/getAllBlankRecord",method=RequestMethod.GET)
////	@ResponseBody
////	public CommonDTO getAllBlankRecord(){
////		try{
////			CommonDTO result=blankRecordService.getAllBlankRecord();
////			return result;
////		}catch(Exception e){
////			e.printStackTrace();
////			return new CommonDTO(Result.SYSTEM_EXCEPTION);
////		}
////	}
//	
//	//根据起始时间和结束时间筛选出胚料记录
//	@RequestMapping(value="/BlowonRecord/getBlankRecord",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getBlankRecordByDate(@RequestParam(defaultValue="1") Integer pageNum,
//			@RequestParam Long beginTime,
//			@RequestParam Long endTime){
//		try{
//			CommonDTO result=blankRecordService.getBlankRecordByDate(beginTime, endTime,pageNum);
//			return result;
//		}catch(ParseException e){
//			e.printStackTrace();
//			return new CommonDTO(Result.TIME_PARSE_ERROR);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
}
