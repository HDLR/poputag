package com.eastern.jinxin.business.recommend.controller;

import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
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

import com.eastern.jinxin.business.recommend.entity.H62PushHisEntity;
import com.eastern.jinxin.business.recommend.service.H62PushHisService;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 15:45:44
 */
@RestController
@RequestMapping("recommend/h62pushhis")
public class H62PushHisController extends AbstractController {
	@Autowired
	private H62PushHisService h62PushHisService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		h62PushHisService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{hisId}")
	@RequiresPermissions("recommend:h62pushhis:info")
	public R info(@PathVariable("hisId") Integer hisId){
		H62PushHisEntity h62PushHis = h62PushHisService.queryObject(hisId);
		
		return R.ok().put("h62PushHis", h62PushHis);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("recommend:h62pushhis:save")
	public R save(@RequestBody H62PushHisEntity h62PushHis){
		h62PushHisService.save(h62PushHis);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("recommend:h62pushhis:update")
	public R update(@RequestBody H62PushHisEntity h62PushHis){
		h62PushHisService.update(h62PushHis);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("recommend:h62pushhis:delete")
	public R delete(@RequestBody Integer[] hisIds){
		h62PushHisService.deleteBatch(hisIds);
		
		return R.ok();
	}
	
}
