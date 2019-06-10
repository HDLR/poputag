package com.eastern.jinxin.sys.operation.role.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.RRException;
import com.eastern.jinxin.sys.operation.role.dao.SysRoleDao;
import com.eastern.jinxin.sys.operation.role.entity.SysRoleEntity;
import com.eastern.jinxin.sys.operation.role.service.SysRoleMenuService;
import com.eastern.jinxin.sys.operation.role.service.SysRoleService;
import com.eastern.jinxin.sys.operation.user.service.SysUserService;



/**
 * 角色
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl  extends ServiceImpl<SysRoleDao, SysRoleEntity>  implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	//@Autowired
	//private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.selectById(roleId);
	}

	@Override
	public void queryList(PageInfo pageInfo) {
		Page<SysRoleEntity> page = new Page<SysRoleEntity>(pageInfo.getNowpage(), pageInfo.getSize());
	    pageInfo.setRows(sysRoleDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(sysRoleDao.queryTotal(pageInfo.getCondition()));
	}


	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.insert(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.updateById(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatchRole(roleIds);
		sysRoleDao.deleteBatchRoleMenu(roleIds);
		sysRoleDao.deleteBatchUserRole(roleIds);
	}
	
	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleDao.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
