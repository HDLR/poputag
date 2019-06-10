package com.eastern.jinxin.business.userGroup.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignTagRelaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 17:36:46
 */
public interface H62CampaignTagRelaService  extends IService<H62CampaignTagRelaEntity>{
	
	H62CampaignTagRelaEntity queryObject(Integer campId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62CampaignTagRelaEntity h62CampaignTagRela);
	
	void update(H62CampaignTagRelaEntity h62CampaignTagRela);
	
	void delete(Integer campId);
	
	void deleteBatch(Integer[] campIds);
}
