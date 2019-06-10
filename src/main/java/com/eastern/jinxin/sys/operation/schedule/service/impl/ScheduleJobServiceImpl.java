package com.eastern.jinxin.sys.operation.schedule.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.Constant.ScheduleStatus;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.schedule.dao.ScheduleJobDao;
import com.eastern.jinxin.sys.operation.schedule.entity.ScheduleJobEntity;
import com.eastern.jinxin.sys.operation.schedule.service.ScheduleJobService;
import com.eastern.jinxin.sys.operation.schedule.util.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private ScheduleJobDao scheduleJobDao;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init() {
		EntityWrapper<ScheduleJobEntity> wrapper=new EntityWrapper<ScheduleJobEntity> ();
		List<ScheduleJobEntity> scheduleJobList = scheduleJobDao.selectList(wrapper);
		for (ScheduleJobEntity scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	@Override
	public ScheduleJobEntity queryObject(Long jobId) {
		return scheduleJobDao.selectById(jobId);
	}

	@Override
	public void queryList(PageInfo pageInfo) {
		Page<ScheduleJobEntity> page = new Page<ScheduleJobEntity>(pageInfo.getNowpage(), pageInfo.getSize());
	    pageInfo.setRows(scheduleJobDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(scheduleJobDao.queryTotal(pageInfo.getCondition()));
	}


	@Override
	@Transactional
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		scheduleJobDao.insert(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	@Transactional
	public void update(ScheduleJobEntity scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		scheduleJobDao.updateById(scheduleJob);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		}
		// 删除数据
		scheduleJobDao.deleteBatch(jobIds);
	}

	@Override
	public int updateBatch(Long[] jobIds, int status) {
		Map<String, Object> map = new HashMap<>();
		map.put("list", jobIds);
		map.put("status", status);
		return scheduleJobDao.updateBatch(map);
	}

	@Override
	@Transactional
	public void run(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, queryObject(jobId));
		}
	}

	@Override
	@Transactional
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}

	@Override
	@Transactional
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}

		updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}


}
