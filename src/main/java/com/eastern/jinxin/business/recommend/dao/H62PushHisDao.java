package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


import com.eastern.jinxin.business.recommend.entity.H62PushHisEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 15:45:44
 */
public interface H62PushHisDao  extends BaseMapper<H62PushHisEntity> {
	
	List<Map<String, Object>> queryListMap(Pagination page, Map<String, Object> map);
	
	List<Map<String, Object>> getTest();
	
	List<H62PushHisEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
