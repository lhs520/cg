package scut.legend.cg.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.service.ProductService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class ProductController {
	@Resource
	private ProductService productService;
	
	@RequestMapping(value="/getAllProduct",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO getAllProduct(@RequestBody HashMap<String, String> params) {
		try{
		CommonDTO result=productService.getAllProduct(params);
		return result;
	}catch(Exception e){
		e.printStackTrace();
		return new CommonDTO(Result.SYSTEM_EXCEPTION);
	}
	}
	
	
}
