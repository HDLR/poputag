package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:21:02
 */
public interface H62PolicyTagDao  extends BaseMapper<H62PolicyTagEntity> {
	
	List<Map<String, Object>> queryAllPolicyTags();
	
	List<H62PolicyTagEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	List<Map<String, Object>> checkTagsName(Map<String, Object> m);
}
