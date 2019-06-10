package com.eastern.jinxin.sys.operation.oss.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.oss.dao.SysOssDao;
import com.eastern.jinxin.sys.operation.oss.entity.SysOssEntity;
import com.eastern.jinxin.sys.operation.oss.service.SysOssService;



@Service("sysOssService")
public class SysOssServiceImpl  extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {
	@Autowired
	private SysOssDao sysOssDao;
	
	@Override
	public SysOssEntity queryObject(Long id){
		return sysOssDao.selectById(id);
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<SysOssEntity> page = new Page<SysOssEntity>(pageInfo.getNowpage(), pageInfo.getSize());
	    pageInfo.setRows(sysOssDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(sysOssDao.queryTotal(pageInfo.getCondition()));
	}

	
	@Override
	public void save(SysOssEntity sysOss){
		sysOssDao.insert(sysOss);
	}
	
	@Override
	public void update(SysOssEntity sysOss){
		sysOssDao.updateById(sysOss);
	}
	
	@Override
	public void delete(Long id){
		sysOssDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysOssDao.deleteBatchIds(Arrays.asList(ids));
	}
}
