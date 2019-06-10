package com.eastern.jinxin.sys.operation.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.common.common.validator.ValidatorUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.operation.role.entity.SysRoleEntity;
import com.eastern.jinxin.sys.operation.role.service.SysRoleMenuService;
import com.eastern.jinxin.sys.operation.role.service.SysRoleService;

/**
 * 角色管理
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 如果不是超级管理员，则只查询自己创建的角色列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"roleName"});
		sysRoleService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select() {
		Map<String, Object> map = new HashMap<>();
		// 如果不是超级管理员，则只查询自己所拥有的角色列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			map.put("createUserId", getUserId());
		}
		List<SysRoleEntity> list =sysRoleService.selectByMap(map);
		return R.ok().put("list", list);
	}

	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId) {
		SysRoleEntity role = sysRoleService.queryObject(roleId);

		// 查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return R.ok().put("role", role);
	}

	/**
	 * 保存角色
	 */
	@SysLogAnn("保存角色")
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);

		role.setCreateUserId(getUserId());
		sysRoleService.save(role);

		return R.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLogAnn("修改角色")
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);

		role.setCreateUserId(getUserId());
		sysRoleService.update(role);

		return R.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLogAnn("删除角色")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds) {
		sysRoleService.deleteBatch(roleIds);

		return R.ok();
	}
}
