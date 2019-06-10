package com.eastern.jinxin.business.statis.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.statis.entity.H62UsageItemLogEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-14 09:43:56
 */
public interface H62UsageItemLogDao  extends BaseMapper<H62UsageItemLogEntity> {
	
	List<Map<String, Object>> queryCampUsageLog(Map<String, List<String>> m);
	
	List<H62UsageItemLogEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
