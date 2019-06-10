package com.eastern.jinxin.business.labelReq.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.labelReq.entity.WebserviceConfEntity;

import java.util.List;
import java.util.Map;

/**
 * webservice的API
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-19 10:52:54
 */
public interface WebserviceConfService  extends IService<WebserviceConfEntity>{
	
	List<WebserviceConfEntity> queryAll();
	
	WebserviceConfEntity queryObject(Long id);
	
	void queryList(PageInfo pageInfo);
	
	void save(WebserviceConfEntity webserviceConf);
	
	void update(WebserviceConfEntity webserviceConf);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
