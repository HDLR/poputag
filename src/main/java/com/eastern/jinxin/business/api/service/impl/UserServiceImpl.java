package com.eastern.jinxin.business.api.service.impl;

import java.util.Arrays;
import java.util.Date;

import com.eastern.jinxin.business.api.dao.UserDao;
import com.eastern.jinxin.business.api.entity.TbUserEntity;
import com.eastern.jinxin.business.api.service.UserService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.RRException;
import com.eastern.jinxin.sys.common.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("userService")
public class UserServiceImpl   extends ServiceImpl<UserDao, TbUserEntity> implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public TbUserEntity queryObject(Long userId){
		return userDao.selectById(userId);
	}
	
	@Override
	public void save(String mobile, String password){
		TbUserEntity user = new TbUserEntity();
		user.setMobile(mobile);
		user.setUsername(mobile);
		user.setPassword(DigestUtils.sha256Hex(password));
		user.setCreateTime(new Date());
		userDao.insert(user);
	}
	
	@Override
	public void update(TbUserEntity user){
		userDao.updateById(user);
	}
	
	@Override
	public void delete(Long userId){
		userDao.deleteById(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		userDao.deleteBatchIds(Arrays.asList(userIds));
	}

	@Override
	public TbUserEntity queryByMobile(String mobile) {
		return userDao.queryByMobile(mobile);
	}

	@Override
	public long login(String mobile, String password) {
		TbUserEntity user = queryByMobile(mobile);
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(password))){
			throw new RRException("手机号或密码错误");
		}

		return user.getUserId();
	}
	

	@Override
	public void queryList(PageInfo pageInfo) {
		Page<TbUserEntity> page = new Page<TbUserEntity>(pageInfo.getNowpage(), pageInfo.getSize());
	    pageInfo.setRows(userDao.queryList(page, pageInfo.getCondition()));
	}

}
