package com.eastern.jinxin.business.label.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.label.entity.H50TaggroupInfoEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-22 10:12:49
 */
public interface H50TaggroupInfoService  extends IService<H50TaggroupInfoEntity>{
	
	List<Map<String, Object>> queryTagsData(List<String> checkTags);
	
	H50TaggroupInfoEntity queryObject(Integer tagId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H50TaggroupInfoEntity h50TaggroupInfo);
	
	void update(H50TaggroupInfoEntity h50TaggroupInfo);
	
	void delete(Integer tagId);
	
	void deleteBatch(Integer[] tagIds);
	
	/**
	 * 
	 * <p>Title : getAllgroupTag</p>
	 * <p>Description : 取得组合标签中 具体内容的所有数据</p>	
	 * @return
	 */
	List<String> getAllgroupTagContent();
	/**
	 * 
	 * <p>Title : getQueryGroupTagKey</p>
	 * <p>Description : 通过组合标签的内容 得到 查询该标签人数的key</p>	
	 * @param tagGroupContent
	 * @return
	 */
	String getQueryGroupTagKey(String tagGroupContent);

	/**
	 * <p>Title : isRightFormat</p>
	 * <p>Description : 验证表达式</p>	
	 * @param tagGroupContent
	 * @return
	 */
	String isRightFormat(String tagGroupContent);
}
