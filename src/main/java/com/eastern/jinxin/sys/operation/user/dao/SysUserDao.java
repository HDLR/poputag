package com.eastern.jinxin.sys.operation.user.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;

/**
 * 系统用户
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId
	 *            用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

	List<SysUserEntity> queryList(Pagination page, Map<String, Object> map);

	void deleteBatchUser(Long[] userId);
	void deleteBatchUserRole(Long[] userId);

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId
	 *            用户ID
	 */
	SysUserEntity queryByUserName(SysUserEntity sy);
	
	int queryTotal(Map<String, Object> map);
}
