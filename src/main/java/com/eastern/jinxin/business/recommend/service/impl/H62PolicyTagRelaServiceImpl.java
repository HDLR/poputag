package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.dao.H62PolicyTagRelaDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagRelaEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyTagRelaService;



@Service("h62PolicyTagRelaService")
 public class H62PolicyTagRelaServiceImpl  extends ServiceImpl<H62PolicyTagRelaDao, H62PolicyTagRelaEntity> implements H62PolicyTagRelaService {
	@Autowired
	private H62PolicyTagRelaDao h62PolicyTagRelaDao;
	
	@Override
	public H62PolicyTagRelaEntity queryObject(Integer id){
		return h62PolicyTagRelaDao.selectById(id);
	}
	

	@Override
	public void save(H62PolicyTagRelaEntity h62PolicyTagRela){
		h62PolicyTagRelaDao.insert(h62PolicyTagRela);
	}
	
	@Override
	public void update(H62PolicyTagRelaEntity h62PolicyTagRela){
		h62PolicyTagRelaDao.updateById(h62PolicyTagRela);
	}
	
	@Override
	public void delete(Integer id){
		h62PolicyTagRelaDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		h62PolicyTagRelaDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PolicyTagRelaEntity> page = new Page<H62PolicyTagRelaEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62PolicyTagRelaDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PolicyTagRelaDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
