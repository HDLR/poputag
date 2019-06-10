package com.eastern.jinxin.sys.operation.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.operation.user.dao.SysUserRoleDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserRoleEntity;
import com.eastern.jinxin.sys.operation.user.service.SysUserRoleService;

/**
 * 用户与角色对应关系
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if (roleIdList.size() == 0) {
			return;
		}

		// 先删除用户与角色关系
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		sysUserRoleDao.deleteByMap(map);

		// 保存用户与角色关系
		SysUserRoleEntity ur = new SysUserRoleEntity();
		for (Long rid : roleIdList) {
			ur = new SysUserRoleEntity();
			ur.setUserId(userId);
			ur.setRoleId(rid);
			sysUserRoleDao.insert(ur);
		}
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(Long userId) {
		sysUserRoleDao.deleteById(userId);
	}
}
