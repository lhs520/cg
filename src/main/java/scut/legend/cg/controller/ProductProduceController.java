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

import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.service.ProductProduceService;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class ProductProduceController {
	@Resource
	private ProductProduceService productProduceService;
	//返回产品产出页面
	@RequestMapping(value="/ProductProduce",method=RequestMethod.GET)
	public String productProduce(){
		return "productProduce";
	}
//	//获取所有产品产出记录
//	@RequestMapping(value="/ProductProduce/getAllProductProduce",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllProductProduce(){
//		try{
//			CommonDTO result=productProduceService.getAllProductProduce();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	
	//根据起始时间和结束时间筛选出产品产出记录
	@RequestMapping(value="/ProductProduce/getProductProduce",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getProductProduceByDate(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam Long beginTime,
			@RequestParam Long endTime){
		try{
			CommonDTO result=productProduceService.getProductProduceByDate(beginTime, endTime,pageNum);
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
	
	//添加产品产出登记信息
	//TODO 记得更新工厂库存的数据库表，增加相应产品库存
	@RequestMapping(value="/ProductProduce/createProductProduce",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createOneProductProduce(@RequestBody ProductProduce productProduce){
		try{
			CommonDTO result=productProduceService.createOneProductProduce(productProduce);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	//修改一条产品产出记录
	@RequestMapping(value="/ProductProduce/updateProductProduce/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateOneProductProduce(@PathVariable Integer id,
			@RequestBody ProductProduce productProduce){
		try{
			CommonDTO result=productProduceService.updateOneProductProduce(productProduce);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
