package com.eastern.jinxin.business.userGroup.controller;


import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.business.userGroup.entity.H50FlatRuleConfigEntity;
import com.eastern.jinxin.business.userGroup.service.H50FlatRuleConfigService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 扁平化规则配置表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-04-25 10:05:15
 */
@RestController
@RequestMapping("h50flatruleconfig")
public class H50FlatRuleConfigController extends AbstractController {
	@Autowired
	private H50FlatRuleConfigService h50FlatRuleConfigService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("h50flatruleconfig:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		h50FlatRuleConfigService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{indxCatCd}")
	@RequiresPermissions("h50flatruleconfig:info")
	public R info(@PathVariable("indxCatCd") String indxCatCd){
		H50FlatRuleConfigEntity h50FlatRuleConfig = h50FlatRuleConfigService.queryObject(indxCatCd);
		
		return R.ok().put("h50FlatRuleConfig", h50FlatRuleConfig);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("h50flatruleconfig:save")
	public R save(@RequestBody H50FlatRuleConfigEntity h50FlatRuleConfig){
		h50FlatRuleConfigService.save(h50FlatRuleConfig);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("h50flatruleconfig:update")
	public R update(@RequestBody H50FlatRuleConfigEntity h50FlatRuleConfig){
		h50FlatRuleConfigService.update(h50FlatRuleConfig);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("h50flatruleconfig:delete")
	public R delete(@RequestBody String[] indxCatCds){
		h50FlatRuleConfigService.deleteBatch(indxCatCds);
		
		return R.ok();
	}
	
}
