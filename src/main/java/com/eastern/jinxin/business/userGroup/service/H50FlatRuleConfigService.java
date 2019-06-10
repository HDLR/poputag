package com.eastern.jinxin.business.userGroup.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.userGroup.entity.H50FlatRuleConfigEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 扁平化规则配置表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-04-25 10:05:15
 */
public interface H50FlatRuleConfigService  extends IService<H50FlatRuleConfigEntity>{
	
	H50FlatRuleConfigEntity queryObject(String indxCatCd);
	
	void queryList(PageInfo pageInfo);
	
	void save(H50FlatRuleConfigEntity h50FlatRuleConfig);
	
	void update(H50FlatRuleConfigEntity h50FlatRuleConfig);
	
	void delete(String indxCatCd);
	
	void deleteBatch(String[] indxCatCds);
	
	public List<H50FlatRuleConfigEntity> getRuleConfig(String activeInd);
}
