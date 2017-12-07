package scut.legend.cg.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import scut.legend.cg.po.Product;
import scut.legend.cg.service.WarehouseProductInventoryService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class WarehouseProductInventoryController {
	
	@Resource
	private WarehouseProductInventoryService wpis;
	
	@RequestMapping(value="/WarehouseProductInventory",method=RequestMethod.GET)
	public String getPage(){
		return "WarehouseProductInventory";
	}
	
	@RequestMapping(value="/WarehouseProductInventory",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO getWarehouseProductInventory(@RequestBody HashMap<String, String> param,
			                                      @RequestParam(value="pageNum",defaultValue="1") Integer pageNum){

		
		param.put("page", pageNum+"");
		param.put("pageNum", PageUtils.NUMBER_PER_PAGE+"");
		param.put("offset", (pageNum-1)*PageUtils.NUMBER_PER_PAGE+"");
		try {
			CommonDTO result = wpis.getProductInventory(param);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
