package com.eastern.jinxin.business.label.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.label.entity.H50TagInfoEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-10 11:30:11
 */
public interface H50TagInfoService  extends IService<H50TagInfoEntity>{
	
	public List<Map<String, Object>> queryTagsAndGroupTagsData(List<String> checkTags);
	
	public List<Map<String, Object>> queryTagsAndGroupTagsDataByCtgyId(List<String> checkTags);
	
	public int tagUsageByTagId(String tag_id);
	
	public List<Map<String, Object>> queryTagsUsage(List<String> tagIds, String countDesc, String limit);
	
	H50TagInfoEntity queryObject(BigInteger tagId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H50TagInfoEntity h50TagInfo);
	
	void update(H50TagInfoEntity h50TagInfo);
	
	void delete(Integer tagId);
	
	void deleteBatch(Integer[] tagIds);
	
	List<Map<String, Object>> queryAllTagsAndGroupTags();
	
	List<Map<String, Object>> queryAllTags();
	
	List<Map<String, Object>> queryTagsData(List<String> checkTags);
	/**
	 * 
	 * <p>Title : del</p>
	 * <p>Description :标签 删除</p>	
	 * @param tagCtgyId
	 * @return
	 */
	int del(BigInteger tagCtgyId);
	/**
	 * 
	 * <p>Title : downLine</p>
	 * <p>Description : 标签下线</p>	
	 * @param tagId
	 * @return
	 */
	int downLine(BigInteger tagId);

	/**
	 * <p>Title : upLine</p>
	 * <p>Description : </p>	
	 * @param h50TagInfo
	 */
	public void upLine(H50TagInfoEntity h50TagInfo);
	/**
	 * 
	 * <p>Title : queryNotTagId</p>
	 * <p>Description : 查询非此tag_id的其他剩余</p>	
	 * @param tagId
	 * @return
	 */
	public List<String> queryNotTagId(String tagId);
}
