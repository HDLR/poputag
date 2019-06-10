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
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushTempEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyPushTempService;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-16 16:10:34
 */
@RestController
@RequestMapping("recommend/h62policypushtemp")
public class H62PolicyPushTempController extends AbstractController {
	@Autowired
	private H62PolicyPushTempService h62PolicyPushTempService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"tempNm"});
		h62PolicyPushTempService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@SysLogAnn("推送模板明细查询")
	@RequestMapping("/info/{tempId}")
	public R info(@PathVariable("tempId") Integer tempId){
		H62PolicyPushTempEntity h62PolicyPushTemp = h62PolicyPushTempService.queryObject(tempId);
		
		return R.ok().put("h62PolicyPushTemp", h62PolicyPushTemp);
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("推送模板创建")
	@RequestMapping("/save")
	@RequiresPermissions("recommend:h62policypushtemp:save")
	public R save(@RequestBody H62PolicyPushTempEntity h62PolicyPushTemp){
		h62PolicyPushTempService.save(h62PolicyPushTemp);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("推送模板更新")
	@RequestMapping("/update")
	@RequiresPermissions("recommend:h62policypushtemp:update")
	public R update(@RequestBody H62PolicyPushTempEntity h62PolicyPushTemp){
		h62PolicyPushTempService.update(h62PolicyPushTemp);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("推送模板删除")
	@RequestMapping("/delete")
	@RequiresPermissions("recommend:h62policypushtemp:delete")
	public R delete(@RequestBody Integer[] tempIds){
		h62PolicyPushTempService.deleteBatch(tempIds);
		
		return R.ok();
	}
	
}
