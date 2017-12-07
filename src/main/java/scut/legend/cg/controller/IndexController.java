package scut.legend.cg.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.po.Staff;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class IndexController {
	@RequestMapping(value="/staff/home",method=RequestMethod.GET)
	public String getHome(){
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		String staffRole=staff.getRole().getRoleDescription();
		switch (staffRole) {
		case "总经理":
			return "GM";
		case "经理":
			return "GM";
		case "工厂管理员":
			return "FM";
		case "仓库管理员":
			return "WM";
		default:
			return "home";
		}
	}
	
	@RequestMapping(value="/staff/unauthorized",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO unauthorized(){
		CommonDTO result=new CommonDTO(Result.UNAUTHORIZED);
		return result;
	}
}
