package com.eastern.jinxin.business.recommend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.business.recommend.dao.H62PolicyTagDao;
import com.eastern.jinxin.business.recommend.dao.H62PolicyTagRelaDao;
import com.eastern.jinxin.business.recommend.dao.H62RecomPolicyDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagRelaEntity;
import com.eastern.jinxin.business.recommend.entity.H62RecomPolicyEntity;
import com.eastern.jinxin.business.recommend.service.H62RecomPolicyService;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;



@Service("h62RecomPolicyService")
 public class H62RecomPolicyServiceImpl  extends ServiceImpl<H62RecomPolicyDao, H62RecomPolicyEntity> implements H62RecomPolicyService {
	@Autowired
	private H62RecomPolicyDao h62RecomPolicyDao;
	@Autowired
	private H62PolicyTagRelaDao h62PolicyTagRelaDao;
	@Autowired
	private H62PolicyTagDao h62PolicyTagDao;
	
	public List<Map<String, Object>> checkTagsName(List<String> checks){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("checkTags", checks);
		return h62PolicyTagDao.checkTagsName(m);
	}
	
	public List<Map<String, Object>> queryRelaTag(int policyId) {
		return h62PolicyTagRelaDao.queryRelaTag(policyId);
	}
	
	@Override
	public H62RecomPolicyEntity queryObject(Integer id){
		H62RecomPolicyEntity e = h62RecomPolicyDao.selectById(id);
		
		List<Map<String, Object>> tagRelas = h62PolicyTagRelaDao.queryRelaTag(id);
		
		e.setTags(tagRelas);
		
		//组合同类标签
		e.setTagMap(TagsUtils.combinationPolicyTags((List<Map<String, Object>>)e.getTags()));
		
		return e;
	}

	@Override
	public void save(H62RecomPolicyEntity h62RecomPolicy){
		
		h62RecomPolicy.setCreateDt(new Date());
		h62RecomPolicy.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		
		h62RecomPolicyDao.insert(h62RecomPolicy);
		
		for(Object o : h62RecomPolicy.getTags()) {
			Map<String, Object> m = (Map<String, Object>)o;
			H62PolicyTagRelaEntity trE = new H62PolicyTagRelaEntity();
			trE.setPolicyId(h62RecomPolicy.getId());
			trE.setPolicyTagId(Integer.parseInt("" + m.get("tag_id")));
			h62PolicyTagRelaDao.insert(trE);
		}
	}
	
	@Override
	public void update(H62RecomPolicyEntity h62RecomPolicy){
		h62RecomPolicyDao.updateById(h62RecomPolicy);
		
		//删除原有的
		EntityWrapper<H62PolicyTagRelaEntity> entityWrapper = new EntityWrapper<H62PolicyTagRelaEntity>();
		H62PolicyTagRelaEntity policyTagRelaEntity = new H62PolicyTagRelaEntity();
		policyTagRelaEntity.setPolicyId(h62RecomPolicy.getId());
		entityWrapper.setEntity(policyTagRelaEntity);
		h62PolicyTagRelaDao.delete(entityWrapper);
		
		for(Object o : h62RecomPolicy.getTags()) {
			Map<String, Object> m = (Map<String, Object>)o;
			H62PolicyTagRelaEntity trE = new H62PolicyTagRelaEntity();
			trE.setPolicyId(h62RecomPolicy.getId());
			trE.setPolicyTagId(Integer.parseInt("" + m.get("tag_id")));
			h62PolicyTagRelaDao.insert(trE);
		}
	}
	
	@Override
	public void delete(Integer id){
		h62RecomPolicyDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		h62RecomPolicyDao.deleteBatchIds(Arrays.asList(ids));
		
		for(Integer id : ids) {
			EntityWrapper<H62PolicyTagRelaEntity> entityWrapper = new EntityWrapper<H62PolicyTagRelaEntity>();
			H62PolicyTagRelaEntity pe = new H62PolicyTagRelaEntity();
			pe.setPolicyId(id);
			entityWrapper.setEntity(pe);
			h62PolicyTagRelaDao.delete(entityWrapper);
		}
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62RecomPolicyEntity> page = new Page<H62RecomPolicyEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62RecomPolicyDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62RecomPolicyDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
