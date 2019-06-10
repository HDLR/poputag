package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.business.recommend.dao.H62PolicyPushTempDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushTempEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyPushTempService;



@Service("h62PolicyPushTempService")
 public class H62PolicyPushTempServiceImpl  extends ServiceImpl<H62PolicyPushTempDao, H62PolicyPushTempEntity> implements H62PolicyPushTempService {
	@Autowired
	private H62PolicyPushTempDao h62PolicyPushTempDao;
	
	@Override
	public H62PolicyPushTempEntity queryObject(Integer tempId){
		return h62PolicyPushTempDao.selectById(tempId);
	}
	

	@Override
	public void save(H62PolicyPushTempEntity h62PolicyPushTemp){
		h62PolicyPushTemp.setCreateDt(new Date());
		h62PolicyPushTemp.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		h62PolicyPushTempDao.insert(h62PolicyPushTemp);
	}
	
	@Override
	public void update(H62PolicyPushTempEntity h62PolicyPushTemp){
		h62PolicyPushTempDao.updateById(h62PolicyPushTemp);
	}
	
	@Override
	public void delete(Integer tempId){
		h62PolicyPushTempDao.deleteById(tempId);
	}
	
	@Override
	public void deleteBatch(Integer[] tempIds){
		h62PolicyPushTempDao.deleteBatchIds(Arrays.asList(tempIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PolicyPushTempEntity> page = new Page<H62PolicyPushTempEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62PolicyPushTempDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PolicyPushTempDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
