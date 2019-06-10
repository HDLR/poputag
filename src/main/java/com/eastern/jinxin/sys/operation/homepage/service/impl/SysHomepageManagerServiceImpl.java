package com.eastern.jinxin.sys.operation.homepage.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.homepage.dao.SysHomepageManagerDao;
import com.eastern.jinxin.sys.operation.homepage.entity.SysHomepageManagerEntity;
import com.eastern.jinxin.sys.operation.homepage.service.SysHomepageManagerService;



@Service("sysHomepageManagerService")
 public class SysHomepageManagerServiceImpl  extends ServiceImpl<SysHomepageManagerDao, SysHomepageManagerEntity> implements SysHomepageManagerService {
	@Autowired
	private SysHomepageManagerDao sysHomepageManagerDao;
	
	@Override
	public SysHomepageManagerEntity queryObject(Integer id){
		return sysHomepageManagerDao.selectById(id);
	}
	

	@Override
	public void save(SysHomepageManagerEntity sysHomepageManager){
		sysHomepageManagerDao.insert(sysHomepageManager);
	}
	
	@Override
	public void update(SysHomepageManagerEntity sysHomepageManager){
		sysHomepageManagerDao.updateById(sysHomepageManager);
	}
	
	@Override
	public void delete(Integer id){
		sysHomepageManagerDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysHomepageManagerDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<SysHomepageManagerEntity> page = new Page<SysHomepageManagerEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(sysHomepageManagerDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(sysHomepageManagerDao.queryTotal(pageInfo.getCondition()));
	}


	/* (non-Javadoc)
	 * <p>Title : getHomepageByUserRole</p>
	 * <p>Description : </p>	
	 * @param roleId
	 * @return	 
	 * @see com.eastern.jinxin.sys.operation.homepage.service.SysHomepageManagerService#getHomepageByUserRole(java.lang.Long)
	 */
	@Override
	public String getHomepageByUserRole(Long roleId) {
		return sysHomepageManagerDao.getHomepageByUserRole(roleId);
	}
	
	
}
