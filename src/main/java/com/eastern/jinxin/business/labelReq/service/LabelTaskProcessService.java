package com.eastern.jinxin.business.labelReq.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.labelReq.entity.LabelTaskProcessEntity;
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
public interface LabelTaskProcessService  extends IService<LabelTaskProcessEntity>{
	
	LabelTaskProcessEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(LabelTaskProcessEntity labelTaskProcess);
	
	void update(LabelTaskProcessEntity labelTaskProcess);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<LabelTaskProcessEntity> queryProcess(Integer process_id);
	
	void deleteByTaskManaId(Integer taskManaId);
}
