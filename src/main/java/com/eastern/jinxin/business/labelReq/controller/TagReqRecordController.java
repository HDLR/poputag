package com.eastern.jinxin.business.labelReq.controller;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.labelReq.entity.TagReqRecordEntity;
import com.eastern.jinxin.business.labelReq.service.TagReqRecordService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 标签需求
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 17:01:40
 */
@RestController
@RequestMapping("tagreq/record")
public class TagReqRecordController extends AbstractController {
	@Autowired
	private TagReqRecordService tagReqRecordService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"reqName"});
		tagReqRecordService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@SysLogAnn("标签需求明细查询")
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		TagReqRecordEntity tagReqRecord = tagReqRecordService.queryObject(id);
		
		return R.ok().put("tagReqRecord", tagReqRecord);
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("标签需求创建")
	@RequestMapping("/save")
	@RequiresPermissions("tagreq:record:save")
	public R save(@RequestBody TagReqRecordEntity tagReqRecord){
		tagReqRecordService.save(tagReqRecord);
		
		return R.ok();
	}
	
	/**
	 * changeApprovalStatus
	 */
	@SysLogAnn("标签需求修改审批状态")
	@RequestMapping("/changeApprovalStatus")
	@RequiresPermissions("tagreq:record:save")
	public R changeApprovalStatus(@RequestBody TagReqRecordEntity tagReqRecord){
		tagReqRecord.setApprovalDate(new Date());
		tagReqRecord.setApprovalUser(getUserId()+"");
		tagReqRecordService.update(tagReqRecord);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("标签需求更新")
	@RequestMapping("/update")
	@RequiresPermissions("tagreq:record:update")
	public R update(@RequestBody TagReqRecordEntity tagReqRecord){
		tagReqRecordService.update(tagReqRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("标签需求删除")
	@RequestMapping("/delete")
	@RequiresPermissions("tagreq:record:delete")
	public R delete(@RequestBody Long[] ids){
		tagReqRecordService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
