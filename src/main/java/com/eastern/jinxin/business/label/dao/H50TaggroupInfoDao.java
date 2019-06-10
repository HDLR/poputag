package com.eastern.jinxin.business.label.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.H50TaggroupInfoEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-22 10:12:49
 */
public interface H50TaggroupInfoDao  extends BaseMapper<H50TaggroupInfoEntity> {
	List<H50TaggroupInfoEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : getAllgroupTagContent</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<String> getAllgroupTagContent();
	
	List<Map<String, Object>> queryTagsData(Map<String, Object> maps);
}
