package scut.legend.cg.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import scut.legend.cg.po.Staff;
import scut.legend.cg.service.StaffService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class StaffController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(StaffController.class);
	
	@Resource
	private StaffService staffService;
	
	@RequestMapping(value="/staff/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/staff/login",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO doLogin(@RequestBody Staff staff){
		if(staff.getStaffNum()==null || staff.getStaffPassword()==null){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		UsernamePasswordToken token=new UsernamePasswordToken(staff.getStaffNum(),staff.getStaffPassword());
		try{
			SecurityUtils.getSubject().login(token);
			return new CommonDTO(Result.SUCCESS);
		}catch(UnknownAccountException e){
			return new CommonDTO(Result.UNKNOWN_ACCOUNT_ERROR);
		}catch(IncorrectCredentialsException e){
			return new CommonDTO(Result.INCORRECT_CREDENTIALS_ERROR);
		}catch(LockedAccountException e){
			return new CommonDTO(Result.INCORRECT_STATE_ERROR);
		}catch (Exception e) {
			return new CommonDTO(Result.OTHER_LOGIN_ERRROR);
		}
	}
	
	@RequestMapping(value="/staff/logout")
	public ModelAndView userLogout(){
		SecurityUtils.getSubject().logout();
		return new ModelAndView("redirect:/staff/login");
	}
	
	@RequestMapping(value="/staffs/staff",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createStaff(@RequestBody Staff staff){
		try{
			CommonDTO result=staffService.createStaff(staff);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/staff/{staffId}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateStaff(@PathVariable("staffId")Integer staffId,
								@RequestBody Staff staff){
		LOGGER.info("[opt:updateStaff,staffId:{}]",staffId);
		try {
			CommonDTO result=staffService.updateStaff(staff);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		
	}
	
	
	
	
	@RequestMapping(value="/staff/{staffId}",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getStaff(@PathVariable("staffId")Integer staffId){
		try{
			return staffService.getStaffByStaffId(staffId);
		}catch(Exception e){
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/staffs/searchResult",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO searchStaffs(@RequestParam(value="q",required=false)String param,
								@RequestParam(value="role",required=false)Integer roleId,
								@RequestParam(value="state",required=false)String state,
								@RequestParam(value="sex",required=false)String sex,
								@RequestParam(value="marriage",required=false)String marriage,
								@RequestParam(value="entryDate",required=false)Long time,
								@RequestParam(value="pageNum",required=false,defaultValue="1")Integer page){
		LOGGER.info("[opt:searchStaffs,q:{},roleId:{},state{},sex:{},marriage:{},entryDate{}]",param,roleId,state,sex,marriage,time);
		CommonDTO result=null;
		try{
			result=staffService.searchStaff(param,roleId,state,sex,marriage,time,page);
		}catch(Exception e){
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	@RequestMapping(value="/staff/userInfo",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getUserInfo(){
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		CommonDTO result;
		if(staff!=null){
			result=new CommonDTO(Result.SUCCESS);
			result.setResult(staff.getStaffName());
		}else{
			result=new CommonDTO(Result.USER_NOT_LOGIN);
		}
		return result;
	}
	
	@RequestMapping(value="/staff/roles",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getRoles(){
		CommonDTO result=null;
		try{
			result=staffService.getRoles();
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	@RequestMapping(value="/staffs/role/{roleName}",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getStaffsByRoleName(@PathVariable("roleName")String roleName){
		CommonDTO result=null;
		try{
			result=staffService.getStaffsByRoleName(roleName);
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
}
