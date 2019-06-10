package com.eastern.jinxin.sys.operation.schedule.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.schedule.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年12月1日 下午10:34:48
 */
public interface ScheduleJobLogService  extends IService<ScheduleJobLogEntity>{

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	void queryList(PageInfo pageInfo);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
