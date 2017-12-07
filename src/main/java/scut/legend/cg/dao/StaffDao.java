package scut.legend.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import scut.legend.cg.po.Staff;

public interface StaffDao {

	/**
	 * 根据员工工号获取员工
	 * @param staffNum员工工号
	 * @return 员工
	 */
	Staff getStaffByStaffNum(String staffNum);

	/**
	 * 创建员工
	 * @param staff员工
	 */
	void createStaff(Staff staff);
	
	/**
	 * 根据时间获取员工工号后缀
	 * @param time
	 * @return 员工工号后缀
	 */
	int getStaffNumSuffix(Long time);

	/**
	 * 更新员工信息
	 * @param staff
	 * @return 更新的记录条数
	 */
	int update(Staff staff);

	/**
	 * 根据员工id获取员工
	 * @param staffId
	 * @return
	 */
	Staff getStaffById(Integer staffId);

	/**
	 * 搜索员工
	 * @param param 员工id或员工工号
	 * @param roleId 员工职位id
	 * @param state 员工状态
	 * @param sex 员工性别
	 * @param marriage 员工婚姻状态
	 * @param time 员工入职时间
	 * @param begin 数据偏移量
	 * @param number 要返回的条数
	 * @return
	 */
	List<Staff> search(@Param("param")String param,
					@Param("roleId")Integer roleId,
					@Param("state")String state,
					@Param("sex")String sex,
					@Param("marriage")String marriage,
					@Param("time")Long time,
					@Param("begin")Integer begin,
					@Param("number")Integer number);

	/**
	 * 根据职位名称获取员工列表
	 * @param roleName 职位名称
	 * @return 
	 */
	List<Staff> getStaffsByRoleName(String roleName);

	/**
	 * 根据条件获取符合条件的员工数目
	 * @param param
	 * @param roleId
	 * @param state
	 * @param sex
	 * @param marriage
	 * @param time
	 * @return 符合条件的员工数目
	 */
	int searchCount(@Param("param")String param,
					@Param("roleId")Integer roleId,
					@Param("state")String state,
					@Param("sex")String sex,
					@Param("marriage")String marriage,
					@Param("time")Long time);

	/**
	 * 根据员工工号获取权限
	 * @param staffNum 员工工号
	 * @return 权限列表
	 */
	List<String> findPermissions(@Param("staffNum")String staffNum);
	
	
	
	

}
