package com.eastern.jinxin.sys.configuration.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro权限标签(Velocity版)
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年12月3日 下午11:32:47
 */
public class VelocityShiro {

	/**
	 * 是否拥有该权限
	 * @param permission  权限标识
	 * @return   true：是     false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

	public static void crSolrQueryValue(String key, String value) {
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}
	
	public boolean queryBoleanValue(String key) {
		Object o = SecurityUtils.getSubject().getSession().getAttribute(key);
		if(null != o) {
			return true;
		}
		return false;
	}
	
	public String solrQueryValue(String key) {
		String v = (String)SecurityUtils.getSubject().getSession().getAttribute(key);
		return v;
	}
}
