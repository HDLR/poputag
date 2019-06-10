package com.eastern.jinxin.business.userGroup.dao;



import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.userGroup.entity.H50FlatRuleConfigEntity;

/**
 * 扁平化规则配置表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-04-25 10:05:15
 */
public interface H50FlatRuleConfigDao  extends BaseMapper<H50FlatRuleConfigEntity> {
	List<H50FlatRuleConfigEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : getRuleConfig</p>
	 * <p>Description : </p>	
	 * @param activeInd
	 * @return
	 */
	List<H50FlatRuleConfigEntity> getRuleConfig(String activeInd);
}
