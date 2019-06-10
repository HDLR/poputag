package com.eastern.jinxin.sys.operation.login.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.code.kaptcha.Constants;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.configuration.shiro.VelocityShiro;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.sys.operation.login.service.LoginService;

/**
 * 登录相关
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
public class SysLoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@RequestMapping("/")
	public String home() {
		return "index.html";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index.html";
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@SysLogAnn("登录系统")
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public R login(String username, String password, String captcha)throws IOException {
		return loginService.login(username, password);
	}
	
	@SysLogAnn("单点登录")
	@RequestMapping(value = "loginSso")
	public String loginSso(String username, String password) {
		try{
			Subject subject = ShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (Exception e) {
			return "redirect:login";
		}
		return "redirect:index";
	}
	
	@SysLogAnn("来自法人")
	@RequestMapping(value = "loginFromLegal")
	public String loginFromLegal(String username, String password, String cardId, String uId, String name, HttpServletRequest request) {
		try{
			Subject subject = ShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			VelocityShiro.crSolrQueryValue("queryValue", "value" + cardId);
			VelocityShiro.crSolrQueryValue("uId", "value" + uId);
			VelocityShiro.crSolrQueryValue("name", name);
		}catch (Exception e) {
			return "redirect:/login.html";
		}
		return "redirect:/index.html#label/basic_popu_info.html";
	}
	
	/**
	 * 退出
	 */
	@SysLogAnn("退出系统")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:login.html";
	}
	
}
