package com.eastern.jinxin.business.statis.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.statis.entity.H62UsageItemLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-14 09:43:56
 */
public interface H62UsageItemLogService  extends IService<H62UsageItemLogEntity>{
	
	List<Map<String, Object>> queryCampUsageLog(List<String> campIds);
	
	void insertH62UsageItemLog(int itemId, String itemType, String operateType, long operateUser);
	
	H62UsageItemLogEntity queryObject(Integer id);
	
	void queryList(PageInfo pageInfo);
	
	void save(H62UsageItemLogEntity h62UsageItemLog);
	
	void update(H62UsageItemLogEntity h62UsageItemLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
