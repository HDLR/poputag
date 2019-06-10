package com.eastern.jinxin.sys.operation.role.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.role.entity.SysRoleEntity;

/**
 * 角色管理
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:33:33
 */
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);


	List<SysRoleEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void deleteBatchRole(Long[] roleId);
	
	void deleteBatchRoleMenu(Long[] roleId);
	
	void deleteBatchUserRole(Long[] roleId);
}
