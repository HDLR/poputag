package com.eastern.jinxin.business.labelReq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.business.labelReq.dao.WebserviceConfDao;
import com.eastern.jinxin.business.labelReq.entity.WebserviceConfEntity;
import com.eastern.jinxin.business.labelReq.service.WebserviceConfService;



@Service("webserviceConfService")
 public class WebserviceConfServiceImpl  extends ServiceImpl<WebserviceConfDao, WebserviceConfEntity> implements WebserviceConfService {
	@Autowired
	private WebserviceConfDao webserviceConfDao;
	
	public List<WebserviceConfEntity> queryAll(){
		return webserviceConfDao.queryAll();
	}
	
	@Override
	public WebserviceConfEntity queryObject(Long id){
		return webserviceConfDao.selectById(id);
	}
	

	@Override
	public void save(WebserviceConfEntity webserviceConf){
		Date dt = new Date();
		webserviceConf.setCreateDate(dt);
		webserviceConf.setUpdateDate(dt);
		webserviceConf.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		webserviceConfDao.insert(webserviceConf);
	}
	
	@Override
	public void update(WebserviceConfEntity webserviceConf){
		webserviceConf.setUpdateDate(new Date());
		webserviceConfDao.updateById(webserviceConf);
	}
	
	@Override
	public void delete(Long id){
		webserviceConfDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		webserviceConfDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<WebserviceConfEntity> page = new Page<WebserviceConfEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(webserviceConfDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(webserviceConfDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
