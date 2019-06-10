package com.eastern.jinxin.sys.operation.role.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.role.entity.SysRoleEntity;


/**
 * 角色
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService extends IService<SysRoleEntity>{
	
	SysRoleEntity queryObject(Long roleId);
	
	void queryList(PageInfo pageInfo);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
