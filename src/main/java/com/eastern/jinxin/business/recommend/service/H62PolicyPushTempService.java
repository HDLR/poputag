package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushTempEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-16 16:10:34
 */
public interface H62PolicyPushTempService  extends IService<H62PolicyPushTempEntity>{
	
	H62PolicyPushTempEntity queryObject(Integer tempId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PolicyPushTempEntity h62PolicyPushTemp);
	
	void update(H62PolicyPushTempEntity h62PolicyPushTemp);
	
	void delete(Integer tempId);
	
	void deleteBatch(Integer[] tempIds);
}
