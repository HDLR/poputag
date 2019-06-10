package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62RecomPolicyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-14 15:00:30
 */
public interface H62RecomPolicyService  extends IService<H62RecomPolicyEntity>{
	
	List<Map<String, Object>> queryRelaTag(int policyId);
	
	H62RecomPolicyEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62RecomPolicyEntity h62RecomPolicy);
	
	void update(H62RecomPolicyEntity h62RecomPolicy);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	public List<Map<String, Object>> checkTagsName(List<String> checks);
}
