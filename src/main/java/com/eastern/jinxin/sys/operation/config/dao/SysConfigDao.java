package com.eastern.jinxin.sys.operation.config.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.config.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends  BaseMapper<SysConfigEntity> {

	List<SysConfigEntity> queryList(Pagination page, Map<String, Object> map);
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	int queryTotal(Map<String, Object> map);
}
