package com.eastern.jinxin.business.label.service.impl;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.H50ManagerTagctgyinfoDao;
import com.eastern.jinxin.business.label.entity.H50ManagerTagctgyinfoEntity;
import com.eastern.jinxin.business.label.service.H50ManagerTagctgyinfoService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("h50ManagerTagctgyinfoService")
 public class H50ManagerTagctgyinfoServiceImpl  extends ServiceImpl<H50ManagerTagctgyinfoDao, H50ManagerTagctgyinfoEntity> implements H50ManagerTagctgyinfoService {
	@Autowired
	private H50ManagerTagctgyinfoDao h50ManagerTagctgyinfoDao;
	
	@Override
	public H50ManagerTagctgyinfoEntity queryObject(Integer id){
		return h50ManagerTagctgyinfoDao.selectById(id);
	}
	

	@Override
	public void save(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo){
		h50ManagerTagctgyinfoDao.insert(h50ManagerTagctgyinfo);
	}
	
	@Override
	public void update(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo){
		h50ManagerTagctgyinfoDao.updateById(h50ManagerTagctgyinfo);
	}
	
	@Override
	public void delete(Integer id){
		h50ManagerTagctgyinfoDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		h50ManagerTagctgyinfoDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H50ManagerTagctgyinfoEntity> page = new Page<H50ManagerTagctgyinfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h50ManagerTagctgyinfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h50ManagerTagctgyinfoDao.queryTotal(pageInfo.getCondition()));
	}


	/* (non-Javadoc)
	 * <p>Title : queryNotShowTagId</p>
	 * <p>Description : </p>	
	 * @param status
	 * @param showFlag
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.H50ManagerTagctgyinfoService#queryNotShowTagId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> queryNotShowTagId(H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo) {
		return h50ManagerTagctgyinfoDao.queryNotShowTagId(h50ManagerTagctgyinfo);
	}
	
	
}
