package com.eastern.jinxin.sys.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.sys.aop.log.LogManager;
import com.eastern.jinxin.sys.aop.log.LogTaskFactory;
import com.eastern.jinxin.sys.common.common.utils.HttpContextUtils;
import com.eastern.jinxin.sys.common.common.utils.IPUtils;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.sys.operation.log.entity.SysLogEntity;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;
import com.eastern.jinxin.sys.operation.user.service.SysUserService;

/**
 * 系统日志，切面处理类
 * 
 * @author zdl_gis
 * @date 2017年3月8日 上午11:07:35
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysUserService sysUserService;
	@Pointcut("@annotation(com.eastern.jinxin.sys.aop.annotation.SysLogAnn)")
	public void logPointCut() {}

	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLogAnn syslog = method.getAnnotation(SysLogAnn.class);
		if (syslog != null) {
			// 注解上的描述
			sysLog.setOperation(syslog.value());
		}

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		String  params="";
		if(args.length>0){
			sysLog.setParams(JSON.toJSONString(args[0]));
		}else{
			sysLog.setParams(params);
		}
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		// 用户名
		String username = "";
		String password = "";
		// 特殊处理,保存登入登出信息
		if (sysLog.getMethod().indexOf("com.eastern.jinxin.sys.operation.login.controller.SysLoginController.login")>=0) {
			try {
				username = (String) request.getParameter("username");
				password = (String) request.getParameter("password");
				password = new Sha256Hash(password).toHex();
				SysUserEntity  user = sysUserService.queryByUserNameAllInfo(username);
				if(user!=null && user.getPassword().equals(password)){
					sysLog.setOperation("<span style='color:#ff270'>登录成功</span>");
				}else{
					sysLog.setOperation("<span style='color:#ff270'>登录失败</span>");
				}
				// 请求的参数
				//sysLog.setParams(getAllparams(request));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		} else {
			username = ShiroUtils.getUserEntity().getUsername();
		}
		sysLog.setUsername(username);
		sysLog.setCreateDate(new Date());
		
		// 保存系统日志
		//sysLogService.insert(sysLog);
		LogManager.me().executeLog(LogTaskFactory.operatorLog(sysLog));
	}

	/*
	private String getAllparams(HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();
		StringBuffer sb = new StringBuffer();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String val = request.getParameter(paraName);
			if (paraName.equals("password")) {
				val = "";
			}
			if (sb.length() > 0) {
				sb.append(",").append('"').append(paraName).append('"').append(":").append('"').append(val).append('"');
			} else {
				sb.append('"').append(paraName).append('"').append(":").append('"').append(val).append('"');
			}
		}
		return "{" + sb.toString() + "}";
	}
	*/
}
