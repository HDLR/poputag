package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.dao.H62PolicyTagTypeDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagTypeEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyTagTypeService;



@Service("h62PolicyTagTypeService")
 public class H62PolicyTagTypeServiceImpl  extends ServiceImpl<H62PolicyTagTypeDao, H62PolicyTagTypeEntity> implements H62PolicyTagTypeService {
	@Autowired
	private H62PolicyTagTypeDao h62PolicyTagTypeDao;
	
	public List<H62PolicyTagTypeEntity> queryallTagType(){
		return h62PolicyTagTypeDao.queryallTagType();
	}
	
	@Override
	public H62PolicyTagTypeEntity queryObject(Integer tagTypeId){
		return h62PolicyTagTypeDao.selectById(tagTypeId);
	}
	

	@Override
	public void save(H62PolicyTagTypeEntity h62PolicyTagType){
		h62PolicyTagTypeDao.insert(h62PolicyTagType);
	}
	
	@Override
	public void update(H62PolicyTagTypeEntity h62PolicyTagType){
		h62PolicyTagTypeDao.updateById(h62PolicyTagType);
	}
	
	@Override
	public void delete(Integer tagTypeId){
		h62PolicyTagTypeDao.deleteById(tagTypeId);
	}
	
	@Override
	public void deleteBatch(Integer[] tagTypeIds){
		h62PolicyTagTypeDao.deleteBatchIds(Arrays.asList(tagTypeIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PolicyTagTypeEntity> page = new Page<H62PolicyTagTypeEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62PolicyTagTypeDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PolicyTagTypeDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
