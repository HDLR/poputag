package com.eastern.jinxin.sys.operation.homepage.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.homepage.entity.SysHomepageManagerEntity;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-08-13 17:14:45
 */
public interface SysHomepageManagerService  extends IService<SysHomepageManagerEntity>{
	
	SysHomepageManagerEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(SysHomepageManagerEntity sysHomepageManager);
	
	void update(SysHomepageManagerEntity sysHomepageManager);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	/**
	 * <p>Title : getHomepageByUserRole</p>
	 * <p>Description : </p>	
	 * @param long1
	 * @return
	 */
	String getHomepageByUserRole(Long roleId);
}
