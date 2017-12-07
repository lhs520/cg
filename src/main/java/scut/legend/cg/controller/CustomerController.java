package scut.legend.cg.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.service.CustomerService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class CustomerController {
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomerController.class);
	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getCustomers(@RequestParam(value="page",defaultValue="1")int page,
			@RequestParam(value="name",required=false)String name){
		LOGGER.info("[opt:getCustomers...]");
		CommonDTO result=null;
		try{
			result=customerService.getCustomers(name,page);
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		
		return result;
	}
}
