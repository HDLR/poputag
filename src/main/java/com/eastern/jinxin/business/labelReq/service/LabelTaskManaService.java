package com.eastern.jinxin.business.labelReq.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.labelReq.entity.LabelTaskManaEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-03-01 17:29:58
 */
public interface LabelTaskManaService  extends IService<LabelTaskManaEntity>{
	
	LabelTaskManaEntity queryManaByApplyId(Integer applyId);
	
	LabelTaskManaEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(LabelTaskManaEntity labelTaskMana);
	
	void update(LabelTaskManaEntity labelTaskMana);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
