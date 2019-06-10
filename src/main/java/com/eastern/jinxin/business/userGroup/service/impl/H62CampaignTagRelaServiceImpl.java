package com.eastern.jinxin.business.userGroup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignTagRelaDao;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignTagRelaEntity;
import com.eastern.jinxin.business.userGroup.service.H62CampaignTagRelaService;



@Service("h62CampaignTagRelaService")
 public class H62CampaignTagRelaServiceImpl  extends ServiceImpl<H62CampaignTagRelaDao, H62CampaignTagRelaEntity> implements H62CampaignTagRelaService {
	@Autowired
	private H62CampaignTagRelaDao h62CampaignTagRelaDao;
	
	@Override
	public H62CampaignTagRelaEntity queryObject(Integer campId){
		return h62CampaignTagRelaDao.selectById(campId);
	}
	

	@Override
	public void save(H62CampaignTagRelaEntity h62CampaignTagRela){
		h62CampaignTagRelaDao.insert(h62CampaignTagRela);
	}
	
	@Override
	public void update(H62CampaignTagRelaEntity h62CampaignTagRela){
		h62CampaignTagRelaDao.updateById(h62CampaignTagRela);
	}
	
	@Override
	public void delete(Integer campId){
		h62CampaignTagRelaDao.deleteById(campId);
	}
	
	@Override
	public void deleteBatch(Integer[] campIds){
		h62CampaignTagRelaDao.deleteBatchIds(Arrays.asList(campIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62CampaignTagRelaEntity> page = new Page<H62CampaignTagRelaEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62CampaignTagRelaDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62CampaignTagRelaDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
