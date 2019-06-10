package com.eastern.jinxin.sys.operation.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;

/**
 * Controller公共组件
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
