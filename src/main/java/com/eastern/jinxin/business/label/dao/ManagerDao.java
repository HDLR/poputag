package com.eastern.jinxin.business.label.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.ManagerEntity;
import com.eastern.jinxin.business.label.entity.TagInfoEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 09:00:47
 */
public interface ManagerDao  extends BaseMapper<ManagerEntity> {
	List<ManagerEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : queryAllTagExcel</p>
	 * <p>Description : 查询所有的标签进行导出</p>	
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> queryAllTagExcel(Map<String, Object> paramMap);
	/**
	 * <p>Title : queryListExcel2</p>
	 * <p>Description : </p>	
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> queryListExcel2(Map<String, String> paramMap);
	/**
	 * <p>Title : queryTagCtyListExcel2</p>
	 * <p>Description : </p>	
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> queryTagCtyListExcel2(Map<String, String> paramMap);
	/**
	 * <p>Title : queryTagListExcel2</p>
	 * <p>Description : </p>	
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> queryTagListExcel2(Map<String, String> paramMap);
	/**
	 * <p>Title : getAllTagIds</p>
	 * <p>Description : 查询所有标签的ID，导入时判断id是否存在</p>	
	 * @return
	 */
	List<String> getAllTagIds();
	/**
	 * <p>Title : findTagCtgyById</p>
	 * <p>Description : </p>	
	 * @param id
	 * @return
	 */
	TagInfoEntity findTagCtgyById(Integer id);
	/**
	 * <p>Title : findTagById</p>
	 * <p>Description : </p>	
	 * @param id
	 * @return
	 */
	TagInfoEntity findTagById(Integer id);
	/**
	 * <p>Title : updateTag</p>
	 * <p>Description : </p>	
	 * @param param
	 */
	void updateTag(Map<String, Object> param);
	/**
	 * <p>Title : insertTag</p>
	 * <p>Description : </p>	
	 * @param param
	 */
	void insertTag(Map<String, Object> param);
	/**
	 * <p>Title : updateTagCtyg</p>
	 * <p>Description : </p>	
	 * @param param
	 */
	void updateTagCtyg(Map<String, Object> param);
	/**
	 * <p>Title : insertTagCtyg</p>
	 * <p>Description : </p>	
	 * @param param
	 */
	void insertTagCtyg(Map<String, Object> param);
	/**
	 * <p>Title : queryListById</p>
	 * <p>Description : </p>	
	 * @param page 
	 * @param pageParam
	 * @return
	 */
	List<TagInfoEntity> queryListById(Page<TagInfoEntity> page, Map<String, Object> pageParam);
	
	int queryClickNodeListTotal(Map<String, Object> map);
	/**
	 * <p>Title : queryTagCtyList</p>
	 * <p>Description : </p>	
	 * @param page
	 * @param condition
	 * @return
	 */
	List<TagInfoEntity> queryTagCtyList(Page<TagInfoEntity> page, Map<String, Object> condition);
	/**
	 * <p>Title : queryTagCtyListTotal</p>
	 * <p>Description : </p>	
	 * @param condition
	 * @return
	 */
	int queryTagCtyListTotal(Map<String, Object> condition);
	/**
	 * <p>Title : getTagInfoById</p>
	 * <p>Description : </p>	
	 * @param tagId
	 * @return
	 */
	TagInfoEntity getTagInfoById(String tagId);
	/**
	 * <p>Title : listLevelAll</p>
	 * <p>Description : 查询标签的所有层级</p>	
	 * @return
	 */
	List<String> listLevelAll();
	/**
	 * <p>Title : queryTagList</p>
	 * <p>Description : </p>	
	 * @param page
	 * @param condition
	 * @return
	 */
	List<TagInfoEntity> queryTagList(Page<TagInfoEntity> page, Map<String, Object> condition);
	/**
	 * <p>Title : queryTagCounts</p>
	 * <p>Description : </p>	
	 * @param condition
	 * @return
	 */
	int queryTagCounts(Map<String, Object> condition);
}
