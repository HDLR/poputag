package com.eastern.jinxin.sys.operation.schedule.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.schedule.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年12月1日 下午10:30:02
 */
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

	List<ScheduleJobLogEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
