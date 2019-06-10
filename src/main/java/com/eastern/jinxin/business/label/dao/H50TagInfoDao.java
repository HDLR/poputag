package com.eastern.jinxin.business.label.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.H50TagInfoEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-10 11:30:11
 */
public interface H50TagInfoDao  extends BaseMapper<H50TagInfoEntity> {
	
	List<Map<String, Object>> queryTagsAndGroupTagsData(Map<String, Object> maps);
	
	List<Map<String, Object>> queryTagsAndGroupTagsDataByCtgyId(Map<String, Object> maps);
	
	int tagUsageByTagId(Map<String, Object> map);
	
	List<H50TagInfoEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	//查询所有标签包含组合标签
	List<Map<String, Object>> queryAllTagsAndGroupTags();
	
	List<Map<String, Object>> queryAllTags();
	
	List<Map<String, Object>> queryTagsData(Map<String, Object> maps);
	
	List<Map<String, Object>> getTagInfoByTaList(Map<String, Object> m);
	
	List<Map<String, Object>> queryTagsUsage(Map<String, Object> m);
	
	/**
	 * 
	 * <p>Title : findCampaignTagRela</p>
	 * <p>Description : 查询标签是否被引用</p>	
	 */
	int findCampaignTagRela(Map<String, Object> map);
	/**
	 * 
	 * <p>Title : downLine</p>
	 * <p>Description : 下线标签项</p>	
	 * @param h50TagCategoryInfo
	 */
	void downLine(H50TagInfoEntity h50TagInfoEntity);

	/**
	 * <p>Title : upLine</p>
	 * <p>Description :上线标签 </p>	
	 * @param h50TagInfo
	 */
	void upLine(H50TagInfoEntity h50TagInfo);

	List<String> queryNotTagId(String tagId);
	
}
