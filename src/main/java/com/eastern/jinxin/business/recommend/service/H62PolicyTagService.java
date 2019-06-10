package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:21:02
 */
public interface H62PolicyTagService  extends IService<H62PolicyTagEntity>{
	
	List<Map<String, Object>> queryAllPolicyTags();
	
	H62PolicyTagEntity queryObject(Integer tagId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PolicyTagEntity h62PolicyTag);
	
	void update(H62PolicyTagEntity h62PolicyTag);
	
	void delete(Integer tagId);
	
	void deleteBatch(Integer[] tagIds);
}
