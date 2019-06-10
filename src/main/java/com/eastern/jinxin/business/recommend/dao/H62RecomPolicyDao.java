package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.recommend.entity.H62RecomPolicyEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-14 15:00:30
 */
public interface H62RecomPolicyDao  extends BaseMapper<H62RecomPolicyEntity> {
	List<H62RecomPolicyEntity> queryRecomPolicyAll();
	List<H62RecomPolicyEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
