package com.eastern.jinxin.sys.operation.menu.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.eastern.jinxin.sys.operation.menu.entity.SysMenuEntity;

/**
 * 菜单管理
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends  BaseMapper<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
