package com.eastern.jinxin.business.label.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.H50TagCategoryInfoEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:16:19
 */
public interface H50TagCategoryInfoDao  extends BaseMapper<H50TagCategoryInfoEntity> {
	List<H50TagCategoryInfoEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : getTagCategoryTree</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getTagCategoryTree(Map<String, Object> params);
	/**
	 * 
	 * <p>Title : insertTagCategoryInfo</p>
	 * <p>Description : 添加标签类别</p>	
	 * @param h50TagCategoryInfoEntity
	 */
	void insertTagCategoryInfo(H50TagCategoryInfoEntity h50TagCategoryInfoEntity);
	/**
	 * <p>Title : getTag</p>
	 * <p>Description :是否存在叶子节点的标签 </p>	
	 * @param hasLeaf
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getTag(String haveTagInd);
	/**
	 * 
	 * <p>Title : upLine</p>
	 * <p>Description : 标签类别的上线</p>	
	 * @param h50TagCategoryInfo
	 */
	void upLine(H50TagCategoryInfoEntity h50TagCategoryInfo);
    
	/**
	 * 
	 * <p>Title : downLine</p>
	 * <p>Description : 标签分类下线</p>	
	 * @param H50TagCategoryInfoEntity
	 */
	void downLine(H50TagCategoryInfoEntity H50TagCategoryInfoEntity);
	/**
	 * <p>Title : getChildrens</p>
	 * <p>Description :取标签类别下的子标签 </p>	
	 * @param tagCtgyId
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getChildrens(String tagCtgyId);
	/**
	 * <p>Title : getCtgyList</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> getCtgyList();
	/**
	 * <p>Title : findAllRecursion</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<H50TagCategoryInfoEntity> findAllRecursion();
	
}
