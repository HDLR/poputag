package com.eastern.jinxin.business.labelReq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.labelReq.dao.LabelReqApplyDao;
import com.eastern.jinxin.business.labelReq.entity.LabelReqApplyEntity;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskManaEntity;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskProcessEntity;
import com.eastern.jinxin.business.labelReq.service.LabelReqApplyService;
import com.eastern.jinxin.business.labelReq.service.LabelTaskManaService;
import com.eastern.jinxin.business.labelReq.service.LabelTaskProcessService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.user.dao.SysUserDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;
import com.eastern.jinxin.sys.utils.DateTools;



@Service("labelReqApplyService")
 public class LabelReqApplyServiceImpl  extends ServiceImpl<LabelReqApplyDao, LabelReqApplyEntity> implements LabelReqApplyService {
	@Autowired
	private LabelReqApplyDao labelReqApplyDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private LabelTaskManaService labelTaskManaService;
	@Autowired
	private LabelTaskProcessService labelTaskProcessService;
	
	@Override
	public LabelReqApplyEntity queryObject(Integer applyId){
		return labelReqApplyDao.selectById(applyId);
	}
	

	@Override
	public void save(LabelReqApplyEntity labelReqApply){
		labelReqApply.setApplyTime(new Date());
		labelReqApplyDao.insert(labelReqApply);
	}
	
	@Override
	public void update(LabelReqApplyEntity labelReqApply){
		labelReqApplyDao.updateById(labelReqApply);
	}
	
	@Override
	public void delete(Integer applyId){
		labelReqApplyDao.deleteById(applyId);
	}
	
	@Override
	public void deleteBatch(Integer[] applyIds){
		labelReqApplyDao.deleteBatchIds(Arrays.asList(applyIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<LabelReqApplyEntity> page = new Page<LabelReqApplyEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(labelReqApplyDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(labelReqApplyDao.queryTotal(pageInfo.getCondition()));
	}
	
	@Override
	public Map<String, Object> showProcess(Integer applyId){
		LabelReqApplyEntity queryApply = this.queryObject(applyId);
		Map<String, Object> processReturn = new HashMap<String, Object>();
		
		//申请者
		String applyUser = "";
		// 查询用户信息
		SysUserEntity sy = new SysUserEntity();
		sy.setUserId(queryApply.getApplyUserid().longValue());
		SysUserEntity user = sysUserDao.selectOne(sy);
		if(null != user) {
			applyUser = "【"+ user.getUsername() +"】";
		}
		
		//申请apply
		//审核check
		//生产production
		//结束finish
		
		//初始化
		processReturn.put("apply", this.createMapMsg(queryApply.getApplyTime(), applyUser +"提交标签《"+ queryApply.getName() +"》需求申请"));
		processReturn.put("check", this.createMapMsg(null, "系统管理员未审核"));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(this.createMapMsg(null, "系统管理员未启动生产过程"));
		processReturn.put("production", list);
		processReturn.put("finish", this.createMapMsg(null, "标签需求申请未完成"));
		processReturn.put("now", "apply");
		
		if(LabelReqApplyEntity.checkProcess(queryApply.getCheck())) {
			LabelTaskManaEntity labelTaskMana = labelTaskManaService.queryManaByApplyId(applyId);
			List<LabelTaskProcessEntity> processs = labelTaskProcessService.queryProcess(labelTaskMana.getId());
			if(LabelReqApplyEntity.CHECK_PASS.equals(queryApply.getCheck())) {//1审核结果：通过
				processReturn.put("now", "production");
				String msg = "审核通过<br/>" + labelTaskMana.getCheckReason();
				if(null == processs || processs.isEmpty()) {
					processReturn.put("now", "check");
					msg += "<br/>但未启动生产过程";
				}else {
					processReturn.put("now", "production");
					list.clear();
					for(LabelTaskProcessEntity pItem : processs) {
						list.add(this.createMapMsgStr(pItem.getTime(), pItem.getProcess()));
					}
				}
				processReturn.put("check", this.createMapMsg(labelTaskMana.getCztime(),msg));
				processReturn.put("production", list);
				
			}else if(LabelReqApplyEntity.CHECK_NO_PASS.equals(queryApply.getCheck())) {//2审核结果：不通过
				processReturn.put("now", "check");
				processReturn.put("check", this.createMapMsg(
							labelTaskMana.getCztime(), 
							"审核未通过<br/>" + labelTaskMana.getCheckReason()
						));
				
			}else if(LabelReqApplyEntity.CHECK_PROCESS_SECCESS.equals(queryApply.getCheck())) {//3生产结束
				processReturn.put("now", "finish");
				String msg = "审核通过<br/>" + labelTaskMana.getCheckReason();
				processReturn.put("check", this.createMapMsg(labelTaskMana.getCztime(),msg));
				if(null != processs) {
					list.clear();
					for(LabelTaskProcessEntity pItem : processs) {
						list.add(this.createMapMsgStr(pItem.getTime(), pItem.getProcess()));
					}
				}
				processReturn.put("finish", this.createMapMsg(null, "标签需求申请结束，标签已生成"));
			}
		}
		return processReturn;
	}
	
	private Map<String, Object> createMapMsg(Date date, String msg) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("date", date == null? null : DateTools.toString(date, "yyyy-MM-dd"));
		m.put("msg", msg);
		return m;
	}
	private Map<String, Object> createMapMsgStr(String date, String msg) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("date", date);
		m.put("msg", msg);
		return m;
	}
}
