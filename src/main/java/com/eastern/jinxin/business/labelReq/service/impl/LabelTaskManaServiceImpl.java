package com.eastern.jinxin.business.labelReq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.labelReq.dao.LabelTaskManaDao;
import com.eastern.jinxin.business.labelReq.entity.LabelReqApplyEntity;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskManaEntity;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskProcessEntity;
import com.eastern.jinxin.business.labelReq.service.LabelReqApplyService;
import com.eastern.jinxin.business.labelReq.service.LabelTaskManaService;
import com.eastern.jinxin.business.labelReq.service.LabelTaskProcessService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.user.dao.SysUserDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;



@Service("labelTaskManaService")
 public class LabelTaskManaServiceImpl  extends ServiceImpl<LabelTaskManaDao, LabelTaskManaEntity> implements LabelTaskManaService {
	@Autowired
	private LabelTaskManaDao labelTaskManaDao;
	@Autowired
	private LabelTaskProcessService labelTaskProcessService;
	@Autowired
	private LabelReqApplyService labelReqApplyService;
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public LabelTaskManaEntity queryManaByApplyId(Integer applyId) {
		LabelTaskManaEntity qe = new LabelTaskManaEntity();
		qe.setApplyId(applyId);
		LabelTaskManaEntity lbelTaskMana = labelTaskManaDao.selectOne(qe);
		return lbelTaskMana;
	}
	
	@Override
	public LabelTaskManaEntity queryObject(Integer applyId){
		
		LabelTaskManaEntity lbelTaskMana = this.queryManaByApplyId(applyId);
		
		if(null == lbelTaskMana) {
			lbelTaskMana = new LabelTaskManaEntity();
			List<LabelTaskProcessEntity> processs = new ArrayList<LabelTaskProcessEntity>();
			processs.add(new LabelTaskProcessEntity());
			lbelTaskMana.setProcesss(processs);
		}else {
			//生产过程
			List<LabelTaskProcessEntity> processs = labelTaskProcessService.queryProcess(lbelTaskMana.getId());
			lbelTaskMana.setProcesss(processs);
			
			//生产者
			SysUserEntity sy = new SysUserEntity();
			sy.setUserId(lbelTaskMana.getUserId().longValue());
			SysUserEntity user = sysUserDao.selectOne(sy);
			if(null != user) {
				lbelTaskMana.setUsername(user.getUsername());
			}
		}
		
		//需求名称
		LabelReqApplyEntity labelReqApply = labelReqApplyService.queryObject(applyId);
		lbelTaskMana.setApplyName(labelReqApply.getName());
		lbelTaskMana.setApplyId(applyId);
		
		return lbelTaskMana;
	}
	

	@Transactional
	@Override
	public void save(LabelTaskManaEntity labelTaskMana){
		
		labelTaskMana.setCztime(new Date());
		labelTaskManaDao.insert(labelTaskMana);
		
		//更新审核状态
		LabelReqApplyEntity labelReqApply = labelReqApplyService.queryObject(labelTaskMana.getApplyId());
		labelReqApply.setCheck(labelTaskMana.getCheck());
		labelReqApplyService.update(labelReqApply);
		
		//更新生产过程
		for(LabelTaskProcessEntity e : labelTaskMana.getProcesss()) {
			e.setTaskManaId(labelTaskMana.getId());
			e.setCreateTime(new Date());
			labelTaskProcessService.insert(e);
		}
	}
	
	@Transactional
	@Override
	public void update(LabelTaskManaEntity labelTaskMana){
		
		labelTaskMana.setCztime(new Date());
		labelTaskManaDao.updateById(labelTaskMana);
		
		//更新审核状态
		LabelReqApplyEntity labelReqApply = labelReqApplyService.queryObject(labelTaskMana.getApplyId());
		labelReqApply.setCheck(labelTaskMana.getCheck());
		labelReqApplyService.update(labelReqApply);
		
		//删除老的生产过程
		labelTaskProcessService.deleteByTaskManaId(labelTaskMana.getId());
		//更新生产过程
		for(LabelTaskProcessEntity e : labelTaskMana.getProcesss()) {
			e.setTaskManaId(labelTaskMana.getId());
			e.setCreateTime(new Date());
			labelTaskProcessService.insert(e);
		}
	}
	
	@Override
	public void delete(Integer id){
		labelTaskManaDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		labelTaskManaDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<LabelTaskManaEntity> page = new Page<LabelTaskManaEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(labelTaskManaDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(labelTaskManaDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
