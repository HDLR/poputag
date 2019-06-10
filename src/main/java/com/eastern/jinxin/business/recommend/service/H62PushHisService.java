package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PushHisEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 15:45:44
 */
public interface H62PushHisService  extends IService<H62PushHisEntity>{
	
	H62PushHisEntity queryObject(Integer hisId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PushHisEntity h62PushHis);
	
	void update(H62PushHisEntity h62PushHis);
	
	void delete(Integer hisId);
	
	void deleteBatch(Integer[] hisIds);
}
