package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


import com.eastern.jinxin.business.recommend.entity.H62PolicyPushEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 09:34:12
 */
public interface H62PolicyPushDao  extends BaseMapper<H62PolicyPushEntity> {
	
	H62PolicyPushEntity getPolicyPush(Integer pushId);
	
	List<H62PolicyPushEntity> queryPolicyPushs(Pagination page, Map<String, Object> map);
	
	List<H62PolicyPushEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
