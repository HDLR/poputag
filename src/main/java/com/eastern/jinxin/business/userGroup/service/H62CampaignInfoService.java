package com.eastern.jinxin.business.userGroup.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:22:25
 */
public interface H62CampaignInfoService  extends IService<H62CampaignInfoEntity>{
	
	public List<Map<String, String>> queryCampaignTress2();
	
	List<Map<String, String>> queryCampaignTress();
	
	public List<Map<String, Object>> heatAnalysis(List<String> campIds);
	
	H62CampaignInfoEntity queryCampaign(Integer campId);
	
	H62CampaignInfoEntity queryCampaign2(Integer campId);
	
	H62CampaignInfoEntity queryObject(Integer campId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62CampaignInfoEntity h62CampaignInfo);
	
	void update(H62CampaignInfoEntity h62CampaignInfo);
	
	void delete(Integer campId);
	
	void deleteBatch(Integer[] campIds);
	
	List<Map<String, Object>> queryCampaignTagRelas(Integer campId);
	
	String campRedisKey(Integer campId);
}
