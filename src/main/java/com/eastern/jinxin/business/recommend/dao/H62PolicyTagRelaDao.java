package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.recommend.entity.H62PolicyTagRelaEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:18:51
 */
public interface H62PolicyTagRelaDao  extends BaseMapper<H62PolicyTagRelaEntity> {
	
	List<Map<String, Object>> queryRelaTag(int policyId);
	
	List<H62PolicyTagRelaEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
