package com.eastern.jinxin.business.labelReq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.labelReq.dao.LabelTaskProcessDao;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskProcessEntity;
import com.eastern.jinxin.business.labelReq.service.LabelTaskProcessService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("labelTaskProcessService")
 public class LabelTaskProcessServiceImpl  extends ServiceImpl<LabelTaskProcessDao, LabelTaskProcessEntity> implements LabelTaskProcessService {
	@Autowired
	private LabelTaskProcessDao labelTaskProcessDao;
	
	@Override
	public LabelTaskProcessEntity queryObject(Integer id){
		return labelTaskProcessDao.selectById(id);
	}
	

	@Override
	public void save(LabelTaskProcessEntity labelTaskProcess){
		labelTaskProcessDao.insert(labelTaskProcess);
	}
	
	@Override
	public void update(LabelTaskProcessEntity labelTaskProcess){
		labelTaskProcessDao.updateById(labelTaskProcess);
	}
	
	@Override
	public void delete(Integer id){
		labelTaskProcessDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		labelTaskProcessDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<LabelTaskProcessEntity> page = new Page<LabelTaskProcessEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(labelTaskProcessDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(labelTaskProcessDao.queryTotal(pageInfo.getCondition()));
	}
	
	@Override
	public List<LabelTaskProcessEntity> queryProcess(Integer process_id) {
		return labelTaskProcessDao.queryProcess(process_id);
	}
	
	@Override
	public void deleteByTaskManaId(Integer taskManaId) {
		labelTaskProcessDao.deleteByTaskManaId(taskManaId);
	}
}
