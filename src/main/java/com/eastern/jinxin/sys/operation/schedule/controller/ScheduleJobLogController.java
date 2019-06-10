package com.eastern.jinxin.sys.operation.schedule.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.schedule.entity.ScheduleJobLogEntity;
import com.eastern.jinxin.sys.operation.schedule.service.ScheduleJobLogService;

/**
 * 定时任务日志
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年12月1日 下午10:39:52
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"jobId"});
		scheduleJobLogService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
		return R.ok().put("log", log);
	}
}
