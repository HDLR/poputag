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
public interface MicroscopicPictureDao  extends BaseMapper<H62CampaignInfoEntity> {
	
	
	List<Map<String, Object>> getScreeningTagsSms();
}
