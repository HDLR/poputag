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
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyPushService;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-17 09:34:12
 */
@RestController
@RequestMapping("recommend/h62policypush")
public class H62PolicyPushController extends AbstractController {
	@Autowired
	private H62PolicyPushService h62PolicyPushService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"pushNm"});
		h62PolicyPushService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@SysLogAnn("政策推送明细查询")
	@RequestMapping("/info/{pushId}")
	public R info(@PathVariable("pushId") Integer pushId){
		H62PolicyPushEntity h62PolicyPush = h62PolicyPushService.queryObject(pushId);
		return R.ok().put("h62PolicyPush", h62PolicyPush);
	}
	
	@SysLogAnn("政策推送明细查询")
	@RequestMapping("/info2/{pushId}")
	public R info2(@PathVariable("pushId") Integer pushId){
		R returnR = R.ok();
		
		H62PolicyPushEntity h62PolicyPush = h62PolicyPushService.queryObject(pushId);
		
		Map<String, List> m = h62PolicyPushService.queryAllParams();
		h62PolicyPush.setPolicys(m.get("policys"));
		h62PolicyPush.setCamps(m.get("camps"));
		h62PolicyPush.setTemps(m.get("temps"));
		
		returnR.put("h62PolicyPush", h62PolicyPush);
		
		return returnR;
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("政策推送创建")
	@RequestMapping("/save")
	@RequiresPermissions("recommend:h62policypush:save")
	public R save(@RequestBody H62PolicyPushEntity h62PolicyPush){
		h62PolicyPushService.save(h62PolicyPush);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("政策推送更新")
	@RequestMapping("/update")
	@RequiresPermissions("recommend:h62policypush:update")
	public R update(@RequestBody H62PolicyPushEntity h62PolicyPush){
		h62PolicyPushService.update(h62PolicyPush);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("政策推送删除")
	@RequestMapping("/delete")
	@RequiresPermissions("recommend:h62policypush:delete")
	public R delete(@RequestBody Integer[] pushIds){
		h62PolicyPushService.deleteBatch(pushIds);
		
		return R.ok();
	}
	
	@RequestMapping("/queryAllParams")
	public R queryAllParams() {
		R returnR = R.ok();
		returnR.put("params", h62PolicyPushService.queryAllParams());
		return returnR;
	}
	
	//推送
	@SysLogAnn("政策推送操作")
	@RequestMapping("/sendPush")
	@RequiresPermissions("recommend:h62policypush:push")
	public R sendPush(String pushId) {
		R returnR = R.ok();
		
		h62PolicyPushService.sendPush(pushId);
		
		return returnR;
	}
	
}
