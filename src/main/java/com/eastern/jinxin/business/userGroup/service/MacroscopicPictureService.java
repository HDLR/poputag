package com.eastern.jinxin.business.userGroup.service;

import java.util.List;
import java.util.Map;

import com.eastern.jinxin.hbase.entity.TagHelperBean;
import com.eastern.jinxin.hbase.entity.TagHelperUtil;

public interface MacroscopicPictureService {
	
	public List<TagHelperBean> getAllTags(TagHelperUtil.TagOrder order);
	
	List<Map<String, Object>> queryCampAllTagDetail(int campId);
	
	Map<String, Map<String, List<Map<String, Object>>>> queryCampAllTagDetail2(int campId);
	
	public long personCnt();
	
	public Map<String, Object> queryPersonDistribute(String campId);
	
	public Map<String, Object> queryCampFature(int campId);
	
	public List<Map<String, Object>> queryTagsDistribute(String redisKey);
}
