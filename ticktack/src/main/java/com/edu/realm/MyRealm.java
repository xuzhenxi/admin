package com.edu.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.dao.IAuthorityDao;
import com.edu.dao.IRoleDao;
import com.edu.dao.IUserDao;
import com.edu.entity.User;


public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAuthorityDao authDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取输入的用户名
		String no = (String) principals.getPrimaryPrincipal();
		
		List<String> roleList = roleDao.findRoleByNo(no);
		List<String> permitList = authDao.findAuthByNo(no);
		
		Set<String> roleSet = new HashSet<>(roleList);
		Set<String> perSet = new HashSet<>(permitList);
		
		// 授权信息的对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 设置用户对应的角色
		info.setRoles(roleSet);
		// 设置对应的权限
		info.setStringPermissions(perSet);
		
		return info;
	}
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取token中的用户名
		String no = (String) token.getPrincipal();
		
		User user = userDao.findByNo(no);
		if (user == null) {
			throw new UnknownAccountException();
		}
		String password = user.getPassword();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(no, password, this.getName());
		
		
		return info;
	}
	
}
