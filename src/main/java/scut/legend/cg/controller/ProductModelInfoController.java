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

import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.service.ProductModelInfoService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class ProductModelInfoController {
	@Resource
	private ProductModelInfoService productModelInfoService;
	//返回产品型号页面
	@RequestMapping(value="/ProductModelInfo",method=RequestMethod.GET)
	public String productModelInfo(){
		return "productMedelInfo";
	}
	
//	//获取所有产品型号记录 
//	@RequestMapping(value="/ProductModelInfo/getAllProductModelInfo",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllProductModelInfo(){
//		try{
//			CommonDTO result=productModelInfoService.getAllProductModelInfo();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//根据起始时间和结束时间筛选出产品型号记录
	@RequestMapping(value="/ProductModelInfo/getProductModelInfo",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getProductModelInfoByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime){
		try{
			CommonDTO result=productModelInfoService.getProductModelInfoByDate(beginTime, endTime,pageNum);
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
	
	//新增一条产品型号添加记录 
	@RequestMapping(value="/ProductModelInfo/createProductModelInfo",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createOneProductModelInfo(@RequestBody ProductModelInfo productModelInfo){
		try{
			CommonDTO result=productModelInfoService.createOneProductModelInfo(productModelInfo);
			return result;
	}catch(Exception e){
		e.printStackTrace();
		return new CommonDTO(Result.SYSTEM_EXCEPTION);
	}
	}
	//修改一条产品型号记录 
	@RequestMapping(value="/ProductModelInfo/updateProductModelInfo/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneProductModelInfo(@PathVariable Integer id,
			@RequestBody ProductModelInfo productModelInfo){
		try{
			CommonDTO result=productModelInfoService.updateOneProductModelInfo(productModelInfo);
			return result;
	}catch(Exception e){
		e.printStackTrace();
		return new CommonDTO(Result.SYSTEM_EXCEPTION);
	}
	}
	
	@RequestMapping(value="/ProductModelInfo/productModels",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getAllProductModels(){
		CommonDTO result=null;
		try{
			result=productModelInfoService.getAllProductModels();
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
		
	}
}
