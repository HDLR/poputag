package com.eastern.jinxin.business.label.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.label.entity.ManagerEntity;
import com.eastern.jinxin.business.label.entity.TagInfoEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 09:00:47
 */
public interface ManagerService  extends IService<ManagerEntity>{
	
	ManagerEntity queryObject(String tagCtgyId);
	
	void queryList(PageInfo pageInfo);
	
	void save(ManagerEntity manager);
	
	void update(ManagerEntity manager);
	
	void delete(String tagCtgyId);
	
	void deleteBatch(String[] tagCtgyIds);

	/**
	 * <p>Title : queryAllTagExcel</p>
	 * <p>Description : 查询全部标签数据</p>	
	 * @return
	 */
	List<Map<String, Object>> queryAllTagExcel();

	/**
	 * <p>Title : queryTagListExcel</p>
	 * <p>Description : 根据条件查询标签数据</p>	
	 * @param pageParam
	 * @return
	 */
	List<Map<String, Object>> queryTagListExcel(Map<String, String> pageParam);

	/**
	 * <p>Title : insertExcelData</p>
	 * <p>Description : 从excel中导入数据</p>	
	 * @param list
	 * @param userId 
	 * @return
	 * @throws Exception 
	 */
	Map<String,List<String>> insertExcelData(List<Map<String, String>> list, Long userId) throws Exception;

	/**
	 * <p>Title : queryListById</p>
	 * <p>Description : 根据点击树的节点进行查询</p>	
	 * @param pageInfo
	 * @return
	 */
	void queryListById(PageInfo pageInfo);

	/**
	 * <p>Title : queryTagCtyList</p>
	 * <p>Description : 根据点击查询按钮进行查询</p>	
	 * @param pageInfo
	 */
	void queryTagCtyList(PageInfo pageInfo);

	/**
	 * <p>Title : getTagInfoById</p>
	 * <p>Description : 根据id查询标签项和标签分类的信息</p>	
	 * @param tagId
	 * @return
	 */
	TagInfoEntity getTagInfoById(String tagId);

	/**
	 * <p>Title : listLevelAll</p>
	 * <p>Description : 查询标签层级</p>	
	 * @return
	 */
	Map<String, Object> listLevelAll();


}
