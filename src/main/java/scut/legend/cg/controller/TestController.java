package scut.legend.cg.controller;

import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.po.User;

@Controller
public class TestController {
	@RequestMapping(value="/test1")
	@ResponseBody
	public String test1(@RequestParam("name")String name,HttpServletRequest request){
		System.out.println(name);
		String requestName=request.getParameter("name");
		System.out.println(requestName);
		return "success";
	}
	
	@RequestMapping(value="/test2")
	@ResponseBody
	public User test1(@RequestBody User user){
		return user;
	}
	
	
}
