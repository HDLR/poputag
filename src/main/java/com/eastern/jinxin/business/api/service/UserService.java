package com.eastern.jinxin.business.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.eastern.jinxin.business.api.entity.TbUserEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 用户
 * 
 * @author looyoo
 * @email service@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserService  extends IService<TbUserEntity>{

	TbUserEntity queryObject(Long userId);
	

	/**
	 * 查询定时任务列表
	 */
	void queryList(PageInfo pageInfo);
	
	void save(String mobile, String password);
	
	void update(TbUserEntity user);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

	TbUserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	long login(String mobile, String password);
}
