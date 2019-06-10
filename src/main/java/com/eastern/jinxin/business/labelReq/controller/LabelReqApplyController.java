package com.eastern.jinxin.business.labelReq.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.operation.user.dao.SysUserDao;
import com.eastern.jinxin.sys.operation.user.entity.SysUserEntity;
import com.eastern.jinxin.sys.utils.DateTools;
import com.eastern.jinxin.business.api.entity.TbUserEntity;
import com.eastern.jinxin.business.api.service.UserService;
import com.eastern.jinxin.business.labelReq.entity.LabelReqApplyEntity;
import com.eastern.jinxin.business.labelReq.service.LabelReqApplyService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-02-28 10:58:16
 */
@RestController
@RequestMapping("labelreq/apply")
public class LabelReqApplyController extends AbstractController {
	@Autowired
	private LabelReqApplyService labelReqApplyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"name"});
		labelReqApplyService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{applyId}")
	public R info(@PathVariable("applyId") Integer applyId){
		LabelReqApplyEntity labelReqApply = labelReqApplyService.queryObject(applyId);
		
		return R.ok().put("labelReqApply", labelReqApply);
	}
	
	/*
	 * 展示审核过程信息
	 */
	@RequestMapping("/showProcess/{applyId}")
	public R showProcess(@PathVariable("applyId") Integer applyId){
		Map<String, Object> processReturn = labelReqApplyService.showProcess(applyId);
		return R.ok().put("labelReqApplyProcess", processReturn);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LabelReqApplyEntity labelReqApply){
		labelReqApply.setApplyUserid(getUserId().intValue());
		labelReqApplyService.save(labelReqApply);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LabelReqApplyEntity labelReqApply){
		LabelReqApplyEntity queryApply = labelReqApplyService.queryObject(labelReqApply.getApplyId());
		if(getUserId() != queryApply.getApplyUserid().longValue()) {
			return R.error().put("code", "2").put("msg", "【" + queryApply.getName() + "】" +"标签需求非您申请，不允许修改。");
		}
		//如果审核人员操作审核时，就不再允许修改
		if(LabelReqApplyEntity.checkProcess(queryApply.getCheck())) {
			return R.error().put("code", "2").put("msg", "【" + queryApply.getName() + "】" +"标签需求申请已被系统管理员审核，不允许修改。");
		}
		labelReqApplyService.update(labelReqApply);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] applyIds){
		
		for(Integer id : applyIds) {
			LabelReqApplyEntity queryApply = labelReqApplyService.queryObject(id);
			if(getUserId() != queryApply.getApplyUserid().longValue()) {
				return R.error().put("code", "2").put("msg", "【" + queryApply.getName() + "】" +"标签需求非您申请，不允许删除。");
			}
			//如果审核人员操作审核时，就不再允许删除
			if(LabelReqApplyEntity.checkProcess(queryApply.getCheck())) {
				return R.error().put("code", "2").put("msg", "标签【"+queryApply.getName()+"】需求申请已被系统管理员审核，不允许删除。");
			}
		}
		
		labelReqApplyService.deleteBatch(applyIds);
		return R.ok();
	}
	
}
