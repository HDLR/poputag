package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:20:31
 */
public interface H62PolicyTagTypeService  extends IService<H62PolicyTagTypeEntity>{
	
	List<H62PolicyTagTypeEntity> queryallTagType();
	
	H62PolicyTagTypeEntity queryObject(Integer tagTypeId);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PolicyTagTypeEntity h62PolicyTagType);
	
	void update(H62PolicyTagTypeEntity h62PolicyTagType);
	
	void delete(Integer tagTypeId);
	
	void deleteBatch(Integer[] tagTypeIds);
}
