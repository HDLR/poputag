package com.eastern.jinxin.business.recommend.dao;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushTempEntity;
/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-16 16:10:34
 */
public interface H62PolicyPushTempDao  extends BaseMapper<H62PolicyPushTempEntity> {
	
	List<H62PolicyPushTempEntity> queryPolicyPushTempAll();
	
	List<H62PolicyPushTempEntity> queryList(Pagination page, Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
