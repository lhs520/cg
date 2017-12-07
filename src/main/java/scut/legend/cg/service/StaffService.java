package scut.legend.cg.service;

import java.util.Set;

import scut.legend.cg.po.Staff;
import scut.legend.cg.vo.CommonDTO;

public interface StaffService {

	/**
	 * 
	 * @param staffNum
	 * @return
	 */
	Staff getStaffByStaffNum(String staffNum);

	CommonDTO createStaff(Staff staff);

	CommonDTO updateStaff(Staff staff);

	CommonDTO getStaffByStaffId(Integer staffId);

	CommonDTO searchStaff(String param, Integer roleId, String state, String sex, String marriage, Long time, Integer page);

	CommonDTO getRoles();

	CommonDTO getStaffsByRoleName(String roleName);

	Set<String> findPermissions(String staffNum);
	
}
