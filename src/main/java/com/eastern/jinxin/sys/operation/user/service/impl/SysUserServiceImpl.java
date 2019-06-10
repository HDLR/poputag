package com.eastern.jinxin.sys.operation.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.RRException;
import com.eastern.jinxin.sys.operation.role.service.SysRoleService;
import com.eastern.jinxin.sys.operation.user.dao.SysUserDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;
import com.eastern.jinxin.sys.operation.user.service.SysUserRoleService;
import com.eastern.jinxin.sys.operation.user.service.SysUserService;

/**
 * 系统用户
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		SysUserEntity sy = new SysUserEntity();
		sy.setUsername(username);
		sy = sysUserDao.selectOne(sy);
		sy.setPassword("");
		return sy;
	}
	
	@Override
	public SysUserEntity queryByUserNameAllInfo(String username) {
		SysUserEntity sy = new SysUserEntity();
		sy.setUsername(username);
		sy = sysUserDao.selectOne(sy);
		return sy;
	}

	@Override
	public SysUserEntity queryObject(Long userId) {
		SysUserEntity sy = sysUserDao.selectById(userId);
		sy.setPassword("");
		return sy;
	}

	@Override
	public void queryList(PageInfo pageInfo) {
		Page<SysUserEntity> page = new Page<SysUserEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(sysUserDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(sysUserDao.queryTotal(pageInfo.getCondition()));
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		// sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserDao.insert(user);
		// 检查角色是否越权
		checkRole(user);
		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
			user.setPassword(sysUserDao.selectById(user.getUserId()).getPassword());
		} else {
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserDao.updateById(user);
		// 检查角色是否越权
		checkRole(user);

		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserDao.deleteBatchUser(userId);
		sysUserDao.deleteBatchUserRole(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user) {
		// 如果不是超级管理员，则需要判断用户的角色是否自己创建
		if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
			return;
		}

		// 查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		// 判断是否越权
		if (!roleIdList.containsAll(user.getRoleIdList())) {
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}
}
