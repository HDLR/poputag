package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagTypeEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:20:31
 */
public interface H62PolicyTagTypeDao  extends BaseMapper<H62PolicyTagTypeEntity> {
	
	List<H62PolicyTagTypeEntity> queryallTagType();
	
	List<H62PolicyTagTypeEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
