package com.eastern.jinxin.business.recommend.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagRelaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-15 09:18:51
 */
public interface H62PolicyTagRelaService  extends IService<H62PolicyTagRelaEntity>{
	
	H62PolicyTagRelaEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62PolicyTagRelaEntity h62PolicyTagRela);
	
	void update(H62PolicyTagRelaEntity h62PolicyTagRela);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
