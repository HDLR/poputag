package com.eastern.jinxin.business.labelReq.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.labelReq.entity.TagReqRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 标签需求
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 17:01:40
 */
public interface TagReqRecordService  extends IService<TagReqRecordEntity>{
	
	TagReqRecordEntity queryObject(Long id);
	
	void queryList(PageInfo pageInfo);
	
	void save(TagReqRecordEntity tagReqRecord);
	
	void update(TagReqRecordEntity tagReqRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
