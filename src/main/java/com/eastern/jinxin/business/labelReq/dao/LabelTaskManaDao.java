package com.eastern.jinxin.business.labelReq.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.labelReq.entity.LabelTaskManaEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-03-01 17:29:58
 */
public interface LabelTaskManaDao  extends BaseMapper<LabelTaskManaEntity> {
	List<LabelTaskManaEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
