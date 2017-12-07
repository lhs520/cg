package scut.legend.cg.service.impl;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Service;

import scut.legend.cg.dao.RoleDao;
import scut.legend.cg.dao.StaffDao;
import scut.legend.cg.exception.StaffOverFlowException;
import scut.legend.cg.exception.TimeFormatException;
import scut.legend.cg.po.Role;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.StaffService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
	@Resource
	private StaffDao staffDao;
	@Resource
	private RoleDao roleDao;

	@Override
	public Staff getStaffByStaffNum(String staffNum) {
		return staffDao.getStaffByStaffNum(staffNum);
	}

	@Override
	public CommonDTO createStaff(Staff staff) {
		if (staff == null || StringUtils.isEmpty(staff.getStaffName()) || StringUtils.isEmpty(staff.getStaffIdNum())
				|| staff.getStaffEntryDate() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		Pattern p = Pattern.compile("[0-9]{17}[0-9xX]");
		Matcher matcher = p.matcher(staff.getStaffIdNum());
		// 可以用正则表达式校验身份证格式
		if (!matcher.matches()) {
			return new CommonDTO(Result.ID_NUM_FORMAT_ERROR);
		}
		// 构造工号
		StringBuilder sb = new StringBuilder();
		String staffNumPrefix = TimeUtils.format(staff.getStaffEntryDate());
		sb.append(staffNumPrefix);
		Long time = null;
		try {
			time = TimeUtils.parse(staffNumPrefix);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new TimeFormatException(e.getMessage());
		}
		int number = staffDao.getStaffNumSuffix(time) + 1;
		if (number > 9999) {
			throw new StaffOverFlowException();
		}
		for (int i = 0; i < 4 - new String(number + "").length(); i++) {
			sb.append("0");
		}
		sb.append(number);
		staff.setStaffNum(sb.toString());

		if(staff.getStaffPassword()!=null){
			if(staff.getStaffPassword().length()<6 || staff.getStaffPassword().length()>30){
				return new CommonDTO(Result.PASSWORD_FORMAT_ERROR);
			}
		}else{
			// 密码加密,身份证后六位
			String idNum = staff.getStaffIdNum();
			String password = idNum.substring(idNum.length() - 6);
			// staff.setStaffPassword(passwordService.encryptPassword(password));
			staff.setStaffPassword(password);
		}
		// 总经理才能添加经理
		if (staff.getRole() == null || staff.getRole().getRoleId() == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		if (staff.getRole().getRoleId() == 2 || staff.getRole().getRoleId() == 1) {
			Staff currentStaff = (Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
			if (currentStaff.getRole().getRoleId() != 1) {
				return new CommonDTO(Result.UNAUTHORIZED);
			}
		}
		staffDao.createStaff(staff);
		CommonDTO result = new CommonDTO(Result.CREATE_STAFF_SUCCESS);
		result.setResult(staff);
		return result;
	}

	@Override
	public CommonDTO updateStaff(Staff staff) {
		// if(staff.getStaffPassword()!=null){
		// staff.setStaffPassword(passwordService.encryptPassword(staff.getStaffPassword()));
		// }
		// 总经理才能更改员工为经理,不允许修改角色
//		if (staff.getRole() == null || staff.getRole().getRoleId() == null) {
//			return new CommonDTO(Result.PARAM_ERROR);
//		}
//		if (staff.getRole().getRoleId() == 2 || staff.getRole().getRoleId() == 1) {
//			Staff currentStaff = (Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
//			if (currentStaff.getRole().getRoleId() != 1) {
//				return new CommonDTO(Result.UNAUTHORIZED);
//			}
//		}
		//不能把经理或总经理降为普通员工
		//密码限制
		if(staff.getStaffPassword().length()<6 || staff.getStaffPassword().length()>30){
			return new CommonDTO(Result.PASSWORD_FORMAT_ERROR);
		}
		if (staffDao.update(staff) == 1) {
			return new CommonDTO(Result.UPDATE_STAFF_SUCCESS);
		} else {
			return new CommonDTO(Result.STAFF_NOT_FOUND);
		}
	}

	@Override
	public CommonDTO getStaffByStaffId(Integer staffId) {
		Staff staff = staffDao.getStaffById(staffId);
		CommonDTO result = null;
		if (staff != null) {
			result = new CommonDTO(Result.SUCCESS);
			result.setResult(staff);
		} else {
			result = new CommonDTO(Result.STAFF_NOT_FOUND);
		}
		return result;
	}

	@Override
	public CommonDTO searchStaff(String param, Integer roleId, String state, String sex, String marriage, Long time,
			Integer page) {
		int count = staffDao.searchCount(param, roleId, state, sex, marriage, time);
		int pageNum = count / PageUtils.NUMBER_PER_PAGE + (count % PageUtils.NUMBER_PER_PAGE == 0 ? 0 : 1);

		List<Staff> staffList = staffDao.search(param, roleId, state, sex, marriage, time,
				(page - 1) * PageUtils.NUMBER_PER_PAGE, PageUtils.NUMBER_PER_PAGE);
		CommonDTO result = new CommonDTO(Result.SUCCESS);
		result.setResult(staffList);
		result.setMsg(pageNum + "");
		return result;
	}

	@Override
	public CommonDTO getRoles() {
		List<Role> roles = roleDao.getRoles();
		CommonDTO result = new CommonDTO(Result.SUCCESS);
		result.setResult(roles);
		return result;
	}

	@Override
	public CommonDTO getStaffsByRoleName(String roleName) {
		if (roleName == null) {
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<Staff> staffs = staffDao.getStaffsByRoleName(roleName);
		CommonDTO result = new CommonDTO(Result.SUCCESS);
		result.setResult(staffs);
		return result;
	}

	@Override
	public Set<String> findPermissions(String staffNum) {
		List<String> permissions = staffDao.findPermissions(staffNum);
		Set<String> set = new HashSet<>();
		for (String permission : permissions) {
			set.add(permission);
		}
		return set;
	}

}
