package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.dao.H62PolicyTagDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyTagService;



@Service("h62PolicyTagService")
 public class H62PolicyTagServiceImpl  extends ServiceImpl<H62PolicyTagDao, H62PolicyTagEntity> implements H62PolicyTagService {
	@Autowired
	private H62PolicyTagDao h62PolicyTagDao;
	
	public List<Map<String, Object>> queryAllPolicyTags(){
		return h62PolicyTagDao.queryAllPolicyTags();
	}
	
	@Override
	public H62PolicyTagEntity queryObject(Integer tagId){
		return h62PolicyTagDao.selectById(tagId);
	}
	

	@Override
	public void save(H62PolicyTagEntity h62PolicyTag){
		h62PolicyTagDao.insert(h62PolicyTag);
	}
	
	@Override
	public void update(H62PolicyTagEntity h62PolicyTag){
		h62PolicyTagDao.updateById(h62PolicyTag);
	}
	
	@Override
	public void delete(Integer tagId){
		h62PolicyTagDao.deleteById(tagId);
	}
	
	@Override
	public void deleteBatch(Integer[] tagIds){
		h62PolicyTagDao.deleteBatchIds(Arrays.asList(tagIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PolicyTagEntity> page = new Page<H62PolicyTagEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62PolicyTagDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PolicyTagDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
