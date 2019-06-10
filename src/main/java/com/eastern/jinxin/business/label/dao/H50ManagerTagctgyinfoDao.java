package com.eastern.jinxin.business.label.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.H50ManagerTagctgyinfoEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-28 17:59:34
 */
public interface H50ManagerTagctgyinfoDao  extends BaseMapper<H50ManagerTagctgyinfoEntity> {
	List<H50ManagerTagctgyinfoEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : queryNotShowTagId</p>
	 * <p>Description : </p>	
	 * @param status
	 * @param showFlag
	 * @return
	 */
	List<String> queryNotShowTagId(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo);
}
