package com.eastern.jinxin.business.label.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.label.entity.H50TagCategoryInfoEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:16:19
 */
public interface H50TagCategoryInfoService  extends IService<H50TagCategoryInfoEntity>{
	
	H50TagCategoryInfoEntity queryObject(String tagCtgyId);
	void queryList(PageInfo pageInfo);
	
	/**
	 * 
	 * <p>Title : getTagCategoryTree</p>
	 * <p>Description : 获得tagCategory树形信息进行treeGrid的填充的</p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getTagCategoryTree(Map<String, Object> params);
	
	/**
	 * 
	 * <p>Title : getTagCategoryTreeLeaf</p>
	 * <p>Description : 获得是否存在叶子节点的标签</p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getTag(String haveTagInd);
	
	void save(H50TagCategoryInfoEntity h50TagCategoryInfo);
	
	void update(H50TagCategoryInfoEntity h50TagCategoryInfo);
	
	void delete(String tagCtgyId);
	
	void deleteBatch(String[] tagCtgyIds);
	/**
	 * <p>Title : upLine</p>
	 * <p>Description : 标签的上线</p>	
	 * @param tagCtgyId
	 */
	void upLine(H50TagCategoryInfoEntity h50TagCategoryInfo);
	/**
	 * 
	 * <p>Title : del</p>
	 * <p>Description : 标签类别删除</p>	
	 * @param tagCtgyId
	 * @return
	 */
	int del(String tagCtgyId);
	/**
	 * 
	 * <p>Title : downLine</p>
	 * <p>Description : 标签类别下线</p>	
	 * @param tagCtgyId
	 * @return
	 */
	int downLine(String tagCtgyId);
	/**
	 * <p>Title : getCtgyList</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getCtgyList();
	
	 List<H50TagCategoryInfoEntity> findAllRecursion();
}
