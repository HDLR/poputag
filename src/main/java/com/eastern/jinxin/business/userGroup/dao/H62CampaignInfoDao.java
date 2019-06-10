package com.eastern.jinxin.business.userGroup.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.userGroup.entity.H62CampaignInfoEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:22:25
 */
public interface H62CampaignInfoDao  extends BaseMapper<H62CampaignInfoEntity> {
	
	List<Map<String, String>> queryCampaignTress2();
	
	List<Map<String, String>> queryCampaignTress();
	
	List<H62CampaignInfoEntity> queryCampaignInfoAll();
	
	List<H62CampaignInfoEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	List<Map<String, Object>> getScreeningTags(String campId);
	
	List<Map<String, Object>> getGroupScreeningTags(String campId);
	
	List<Map<String, Object>> getScreeningTagsSms();
}
