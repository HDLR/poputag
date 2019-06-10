package com.eastern.jinxin.business.userGroup.service.impl;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.userGroup.dao.H50FlatRuleConfigDao;
import com.eastern.jinxin.business.userGroup.entity.H50FlatRuleConfigEntity;
import com.eastern.jinxin.business.userGroup.service.H50FlatRuleConfigService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("h50FlatRuleConfigService")
 public class H50FlatRuleConfigServiceImpl  extends ServiceImpl<H50FlatRuleConfigDao, H50FlatRuleConfigEntity> implements H50FlatRuleConfigService {
	@Autowired
	private H50FlatRuleConfigDao h50FlatRuleConfigDao;
	
	@Override
	public H50FlatRuleConfigEntity queryObject(String indxCatCd){
		return h50FlatRuleConfigDao.selectById(indxCatCd);
	}
	

	@Override
	public void save(H50FlatRuleConfigEntity h50FlatRuleConfig){
		h50FlatRuleConfigDao.insert(h50FlatRuleConfig);
	}
	
	@Override
	public void update(H50FlatRuleConfigEntity h50FlatRuleConfig){
		h50FlatRuleConfigDao.updateById(h50FlatRuleConfig);
	}
	
	@Override
	public void delete(String indxCatCd){
		h50FlatRuleConfigDao.deleteById(indxCatCd);
	}
	
	@Override
	public void deleteBatch(String[] indxCatCds){
		h50FlatRuleConfigDao.deleteBatchIds(Arrays.asList(indxCatCds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H50FlatRuleConfigEntity> page = new Page<H50FlatRuleConfigEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h50FlatRuleConfigDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h50FlatRuleConfigDao.queryTotal(pageInfo.getCondition()));
	}


	/* (non-Javadoc)
	 * <p>Title : getRuleConfig</p>
	 * <p>Description : </p>	
	 * @param activeInd
	 * @return	 
	 * @see com.eastern.jinxin.business.userGroup.service.H50FlatRuleConfigService#getRuleConfig(java.lang.String)
	 */
	@Override
	public List<H50FlatRuleConfigEntity> getRuleConfig(String activeInd) {
		// TODO Auto-generated method stub
		return h50FlatRuleConfigDao.getRuleConfig(activeInd);
	}
	
	
}
