package scut.legend.cg.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.service.FactoryProductInventoryService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class FactoryProductInventoryController {
	@Resource
	private FactoryProductInventoryService factoryProductInventoryService;
	//TODO 返回开炉记录页面，还需返回开炉记录数据
	@RequestMapping(value="/FactoryProductInventory",method=RequestMethod.GET)
	public String factoryProductInventory(){
		return "factoryProductInventory";
	}
	
	//获得工厂 中所有产品库存 
	//TODO 需要添加返回库存查询页面的功能，用ModelAndView将页面和数据一起发回客户端
//	@RequestMapping(value="/FactoryProductInventory/getAllProductInventory",method=RequestMethod.GET)
//	@ResponseBody
//	public CommonDTO getAllProductInventory(){
//		try{
//			CommonDTO result=factoryProductInventoryService.getAllProductInventory();
//			return result;
//		}catch(Exception e){
//			e.printStackTrace();
//			return new CommonDTO(Result.SYSTEM_EXCEPTION);
//		}
//	}
	//组合条件(产品型号、产品规格、产品形态)获取工厂产品库存
	@RequestMapping(value="/FactoryProductInventory/getFactoryProductInventory",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO getProductInventory(@RequestParam(defaultValue="1") Integer pageNum,
			@RequestBody HashMap<String, String> params){
		try{
			params.put("page", pageNum+"");
			params.put("pageNum", PageUtils.NUMBER_PER_PAGE+"");
			params.put("offset", (pageNum-1)*PageUtils.NUMBER_PER_PAGE+"");
			CommonDTO result=factoryProductInventoryService.getProductInventory(params);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
