package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 09:34:12
 */
public interface H62PolicyPushService  extends IService<H62PolicyPushEntity>{
	
	Map<String, Object> sendPush(String pushId);
	
	Map<String, List> queryAllParams();
	
	List<H62PolicyPushEntity> queryPolicyPushs(Pagination page, Map<String, Object> map);
	
	H62PolicyPushEntity queryObject(Integer pushId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PolicyPushEntity h62PolicyPush);
	
	void update(H62PolicyPushEntity h62PolicyPush);
	
	void delete(Integer pushId);
	
	void deleteBatch(Integer[] pushIds);
}
