package com.eastern.jinxin.sys.operation.homepage.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.homepage.entity.SysHomepageManagerEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-08-13 17:14:45
 */
public interface SysHomepageManagerDao  extends BaseMapper<SysHomepageManagerEntity> {
	List<SysHomepageManagerEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : getHomepageByUserRole</p>
	 * <p>Description : </p>	
	 * @param roleId
	 * @return
	 */
	String getHomepageByUserRole(Long roleId);
}
