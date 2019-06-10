package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.dao.H62PushHisDao;
import com.eastern.jinxin.business.recommend.entity.H62PushHisEntity;
import com.eastern.jinxin.business.recommend.service.H62PushHisService;



@Service("h62PushHisService")
 public class H62PushHisServiceImpl  extends ServiceImpl<H62PushHisDao, H62PushHisEntity> implements H62PushHisService {
	@Autowired
	private H62PushHisDao h62PushHisDao;
	
	@Override
	public H62PushHisEntity queryObject(Integer hisId){
		return h62PushHisDao.selectById(hisId);
	}
	

	@Override
	public void save(H62PushHisEntity h62PushHis){
		h62PushHisDao.insert(h62PushHis);
	}
	
	@Override
	public void update(H62PushHisEntity h62PushHis){
		h62PushHisDao.updateById(h62PushHis);
	}
	
	@Override
	public void delete(Integer hisId){
		h62PushHisDao.deleteById(hisId);
	}
	
	@Override
	public void deleteBatch(Integer[] hisIds){
		h62PushHisDao.deleteBatchIds(Arrays.asList(hisIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PushHisEntity> page = new Page<H62PushHisEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62PushHisDao.queryListMap(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PushHisDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
