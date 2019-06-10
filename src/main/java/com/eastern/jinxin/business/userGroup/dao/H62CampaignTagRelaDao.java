package com.eastern.jinxin.business.userGroup.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.userGroup.entity.H62CampaignTagRelaEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 17:36:46
 */
public interface H62CampaignTagRelaDao  extends BaseMapper<H62CampaignTagRelaEntity> {
	
	List<H62CampaignTagRelaEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	List<Map<String, Object>> queryH62CampaignTagRela(Integer campId);
	
	int insertH62CampaignTagRela(H62CampaignTagRelaEntity e);
}
