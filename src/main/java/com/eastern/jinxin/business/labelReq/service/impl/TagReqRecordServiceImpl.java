package com.eastern.jinxin.business.labelReq.service.impl;

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
import com.eastern.jinxin.business.labelReq.dao.TagReqRecordDao;
import com.eastern.jinxin.business.labelReq.entity.TagReqRecordEntity;
import com.eastern.jinxin.business.labelReq.entity.WebserviceConfEntity;
import com.eastern.jinxin.business.labelReq.service.TagReqRecordService;
import com.eastern.jinxin.business.labelReq.service.WebserviceConfService;



@Service("tagReqRecordService")
 public class TagReqRecordServiceImpl  extends ServiceImpl<TagReqRecordDao, TagReqRecordEntity> implements TagReqRecordService {
	@Autowired
	private TagReqRecordDao tagReqRecordDao;
	@Autowired
	private WebserviceConfService webserviceConfService;
	
	@Override
	public TagReqRecordEntity queryObject(Long id){
		TagReqRecordEntity tagReqRecordEntity = tagReqRecordDao.selectById(id);
//		if(null != tagReqRecordEntity.getWebserviceId()) {
//			WebserviceConfEntity webserviceConfEntity = webserviceConfService.selectById(tagReqRecordEntity.getWebserviceId());
//			tagReqRecordEntity.setWebserviceConfEntity(webserviceConfEntity);
//		}
		return tagReqRecordEntity;
	}
	

	@Override
	public void save(TagReqRecordEntity tagReqRecord){
		Date dt = new Date();
		tagReqRecord.setCreateDate(dt);
		tagReqRecord.setUpdateDate(dt);
		tagReqRecord.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		tagReqRecordDao.insert(tagReqRecord);
	}
	
	@Override
	public void update(TagReqRecordEntity tagReqRecord){
		tagReqRecord.setUpdateDate(new Date());
		tagReqRecordDao.updateById(tagReqRecord);
	}
	
	@Override
	public void delete(Long id){
		tagReqRecordDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tagReqRecordDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<TagReqRecordEntity> page = new Page<TagReqRecordEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(tagReqRecordDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(tagReqRecordDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
