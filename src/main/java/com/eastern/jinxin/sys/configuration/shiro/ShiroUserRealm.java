package com.eastern.jinxin.sys.configuration.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.eastern.jinxin.sys.operation.menu.dao.SysMenuDao;
import com.eastern.jinxin.sys.operation.menu.entity.SysMenuEntity;
import com.eastern.jinxin.sys.operation.user.dao.SysUserDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;

/**
 * 认证
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年11月10日 上午11:55:49
 */
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
    protected StringRedisTemplate redisTemplate;
	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(user.getPermsSet());
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		// 查询用户信息
		SysUserEntity sy=new SysUserEntity();
		sy.setUsername(username);
		SysUserEntity user = sysUserDao.selectOne(sy);
		
		
						
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		
		
		
		// 密码错误
		if (!password.equals(user.getPassword())) {
			String preKey = "login_User:";
			String misNumStr = redisTemplate.opsForValue().get(preKey + username);
			if(null == misNumStr) {
				misNumStr = "1";
				//设置十分钟失效
				redisTemplate.opsForValue().set(preKey + username, misNumStr, 60*5, TimeUnit.SECONDS);
				throw new IncorrectCredentialsException("账号或密码不正确");
			}else {
				int misNum = Integer.parseInt(misNumStr);
				if(misNum > 4) {
					throw new LockedAccountException("账号已被锁定5分钟,请稍后再登陆");
				}else {
					redisTemplate.opsForValue().set(preKey + username, (misNum+1)+"", 60*5, TimeUnit.SECONDS);  
					throw new IncorrectCredentialsException("账号或密码不正确，剩余"+(5-misNum)+"次，账户将被锁定5分钟");
				}
			}
			
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		Long userId = user.getUserId();
		List<String> permsList = null;
		// 系统管理员，拥有最高权限
		if (userId == 1) {
			EntityWrapper<SysMenuEntity> wrapper=new EntityWrapper<SysMenuEntity>();
			List<SysMenuEntity> menuList = sysMenuDao.selectList(wrapper);
			permsList = new ArrayList<>(menuList.size());
			for (SysMenuEntity menu : menuList) {
				permsList.add(menu.getPerms());
			}
		} else {
			permsList = sysUserDao.queryAllPerms(userId);
		}

		// 用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		user.setPermsSet(permsSet);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}
	
	   @Override
	    public void onLogout(PrincipalCollection principals) {
	        super.clearCachedAuthorizationInfo(principals);
	        SysUserEntity shiroUser = (SysUserEntity) principals.getPrimaryPrincipal();
	        removeUserCache(shiroUser);
	    }

	    /**
	     * 清除用户缓存
	     * @param shiroUser
	     */
	    public void removeUserCache(SysUserEntity user){
	        removeUserCache(user.getUsername());
	    }

	    /**
	     * 清除用户缓存
	     * @param loginName
	     */
	    public void removeUserCache(String userName){
	        SimplePrincipalCollection principals = new SimplePrincipalCollection();
	        principals.add(userName, super.getName());
	        super.clearCachedAuthenticationInfo(principals);
	    }

}
