package com.eastern.jinxin.business.labelReq.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


import com.eastern.jinxin.business.labelReq.entity.WebserviceConfEntity;
/**
 * webservice的API
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-19 10:52:54
 */
public interface WebserviceConfDao  extends BaseMapper<WebserviceConfEntity> {
	
	List<WebserviceConfEntity> queryAll();
	
	List<WebserviceConfEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
