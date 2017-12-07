package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Staff implements Serializable{

	private Integer staffId;// 员工id
	private String staffNum;// 员工工号
	private String staffName;// 姓名
	private Role role; // 角色
	private String staffState;// 就职状态:在职/离职
	private String staffSex;// 性别:男/女
	private String staffNative;// 籍贯
	private String staffNation;// 民族
	private String staffMarriage;// 婚姻状况:未婚、已婚、离异
	private String staffAddress;// 家庭住址
	private Long staffEntryDate;// 入职日期
	private String staffIdNum;// 身份证号
	private String staffTel;// 手机号码
	private String staffPassword;// 密码

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getStaffState() {
		return staffState;
	}

	public void setStaffState(String staffState) {
		this.staffState = staffState;
	}

	public String getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(String staffSex) {
		this.staffSex = staffSex;
	}

	public String getStaffNative() {
		return staffNative;
	}

	public void setStaffNative(String staffNative) {
		this.staffNative = staffNative;
	}

	public String getStaffNation() {
		return staffNation;
	}

	public void setStaffNation(String staffNation) {
		this.staffNation = staffNation;
	}

	public String getStaffMarriage() {
		return staffMarriage;
	}

	public void setStaffMarriage(String staffMarriage) {
		this.staffMarriage = staffMarriage;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public Long getStaffEntryDate() {
		return staffEntryDate;
	}

	public void setStaffEntryDate(Long staffEntryDate) {
		this.staffEntryDate = staffEntryDate;
	}

	public String getStaffIdNum() {
		return staffIdNum;
	}

	public void setStaffIdNum(String staffIdNum) {
		this.staffIdNum = staffIdNum;
	}

	public String getStaffTel() {
		return staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffNum=" + staffNum + ", staffName=" + staffName + ", role=" + role
				+ ", staffState=" + staffState + ", staffSex=" + staffSex + ", staffNative=" + staffNative
				+ ", staffNation=" + staffNation + ", staffMarriage=" + staffMarriage + ", staffAddress=" + staffAddress
				+ ", staffEntryDate=" + staffEntryDate + ", staffIdNum=" + staffIdNum + ", staffTel=" + staffTel
				+ ", staffPassword=" + staffPassword + "]";
	}

}
