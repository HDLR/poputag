package com.eastern.jinxin.business.labelReq.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


import com.eastern.jinxin.business.labelReq.entity.TagReqRecordEntity;
/**
 * 标签需求
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 17:01:40
 */
public interface TagReqRecordDao  extends BaseMapper<TagReqRecordEntity> {
	List<TagReqRecordEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
