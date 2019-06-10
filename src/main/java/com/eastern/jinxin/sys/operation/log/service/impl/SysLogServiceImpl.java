package com.eastern.jinxin.sys.operation.log.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.log.dao.SysLogDao;
import com.eastern.jinxin.sys.operation.log.entity.SysLogEntity;
import com.eastern.jinxin.sys.operation.log.service.SysLogService;
import com.eastern.jinxin.sys.operation.oss.entity.SysOssEntity;



@Service("sysLogService")
public class SysLogServiceImpl  extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public SysLogEntity queryObject(Long id){
		return sysLogDao.selectById(id);
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<SysOssEntity> page = new Page<SysOssEntity>(pageInfo.getNowpage(), pageInfo.getSize());
	    pageInfo.setRows(sysLogDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(sysLogDao.queryTotal(pageInfo.getCondition()));
	}

	@Override
	public void save(SysLogEntity sysLog){
		sysLogDao.insert(sysLog);
	}
	
	@Override
	public void update(SysLogEntity sysLog){
		sysLogDao.updateById(sysLog);
	}
	
	@Override
	public void delete(Long id){
		sysLogDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysLogDao.deleteBatchIds(Arrays.asList((ids)));
	}
	
}
