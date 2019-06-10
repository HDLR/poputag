package com.eastern.jinxin.sys.operation.role.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.operation.role.dao.SysRoleMenuDao;
import com.eastern.jinxin.sys.operation.role.entity.SysRoleMenuEntity;
import com.eastern.jinxin.sys.operation.role.service.SysRoleMenuService;



/**
 * 角色与菜单对应关系
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl  extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity>  implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		if(menuIdList.size() == 0){
			return ;
		}
		//先删除角色与菜单关系
		Map<String, Object> map = new HashMap<>();
		map.put("role_id", roleId);
		sysRoleMenuDao.deleteByMap(map);
		sysRoleMenuDao.deleteById(roleId);
		
		//保存角色与菜单关系
		SysRoleMenuEntity rm = new SysRoleMenuEntity();
		for (Long mid : menuIdList) {
			rm = new SysRoleMenuEntity();
			rm.setRoleId(roleId);
			rm.setMenuId(mid);
			sysRoleMenuDao.insert(rm);
		}
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

}
