package com.eastern.jinxin.business.label.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.label.entity.H50ManagerTagctgyinfoEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-28 17:59:34
 */
public interface H50ManagerTagctgyinfoService  extends IService<H50ManagerTagctgyinfoEntity>{
	
	H50ManagerTagctgyinfoEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo);
	
	void update(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 
	 * <p>Title : queryNotShowTagId</p>
	 * <p>Description : 查询标签为启用且是不可见的id</p>	
	 * @param status 0:禁用、2:启用
	 * @param showFlag 0:不可见  2:可见
	 * @return
	 */
	public List<String> queryNotShowTagId(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo);
}
