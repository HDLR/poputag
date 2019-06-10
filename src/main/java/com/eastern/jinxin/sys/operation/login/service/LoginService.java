package com.eastern.jinxin.sys.operation.login.service;

import java.io.IOException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.sys.operation.user.entity.ApiUser;

@Service("loginService")
public class LoginService {

	public R login(String username, String password)throws IOException {
		try{
			Subject subject = ShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			
			ShiroUtils.getSession().setTimeout(24 * 60 * 60 * 1000);//设置session有效时间，一天
			
			ApiUser apiUser = new ApiUser();
			apiUser.setApiId(username);
			ShiroUtils.setSessionAttribute(ApiUser.TOKEN, apiUser);
			
		}catch (UnknownAccountException e) {
			return R.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return R.error(e.getMessage());
		}catch (LockedAccountException e) {
			return R.error(e.getMessage());
		}catch (AuthenticationException e) {
			return R.error("账户验证失败");
		}
	    
		return R.ok();
	}
}
