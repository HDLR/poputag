package com.eastern.jinxin.business.labelReq.controller;

import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.business.labelReq.entity.LabelTaskManaEntity;
import com.eastern.jinxin.business.labelReq.service.LabelTaskManaService;
import com.eastern.jinxin.business.labelReq.service.LabelTaskProcessService;
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
 * @date 2019-03-01 17:29:58
 */
@RestController
@RequestMapping("label/taskmana")
public class LabelTaskManaController extends AbstractController {
	@Autowired
	private LabelTaskManaService labelTaskManaService;
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{applyId}")
	public R info(@PathVariable("applyId") Integer applyId){
		LabelTaskManaEntity labelTaskMana = labelTaskManaService.queryObject(applyId);
		return R.ok().put("labelTaskMana", labelTaskMana);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("label:taskmana:save")
	public R save(@RequestBody LabelTaskManaEntity labelTaskMana){
		labelTaskMana.setUserId(getUserId());
		labelTaskManaService.save(labelTaskMana);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("label:taskmana:save")
	public R update(@RequestBody LabelTaskManaEntity labelTaskMana){
		labelTaskMana.setUserId(getUserId());
		labelTaskManaService.update(labelTaskMana);
		return R.ok();
	}
}
