package com.eastern.jinxin.business.labelReq.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.labelReq.entity.LabelReqApplyEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-02-28 10:58:16
 */
public interface LabelReqApplyService  extends IService<LabelReqApplyEntity>{
	
	LabelReqApplyEntity queryObject(Integer applyId);
	
	void queryList(PageInfo pageInfo);
	
	void save(LabelReqApplyEntity labelReqApply);
	
	void update(LabelReqApplyEntity labelReqApply);
	
	void delete(Integer applyId);
	
	void deleteBatch(Integer[] applyIds);
	
	Map<String, Object> showProcess(Integer applyId);
}
