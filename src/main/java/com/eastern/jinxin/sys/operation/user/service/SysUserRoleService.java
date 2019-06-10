package com.eastern.jinxin.sys.operation.user.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.operation.user.entity.SysUserRoleEntity;



/**
 * 用户与角色对应关系
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleService  extends IService<SysUserRoleEntity>{
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void delete(Long userId);
}
