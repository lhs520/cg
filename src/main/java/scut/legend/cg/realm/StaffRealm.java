package scut.legend.cg.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import scut.legend.cg.po.Staff;
import scut.legend.cg.po.User;
import scut.legend.cg.service.StaffService;

public class StaffRealm extends AuthorizingRealm {

	StaffService staffService;

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String staffNum = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		authorizationInfo.setRoles(staffService.findRoleByStaffNum(staffNum));
		authorizationInfo.setStringPermissions(staffService.findPermissions(staffNum));

		return authorizationInfo;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String staffNum = (String) token.getPrincipal();
		Staff staff = staffService.getStaffByStaffNum(staffNum);

		if (staff == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (!"在职".equals(staff.getStaffState())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 登录成功,存入session
		UsernamePasswordToken token2 = (UsernamePasswordToken) token;
		String credentials = new String(token2.getPassword());
		if (staff.getStaffPassword().equals(credentials)) {
			SecurityUtils.getSubject().getSession().setAttribute("staff", staff);
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(staff.getStaffNum(), // 工号
				staff.getStaffPassword(), getName() // realm name
		);
		return authenticationInfo;
	}

}
