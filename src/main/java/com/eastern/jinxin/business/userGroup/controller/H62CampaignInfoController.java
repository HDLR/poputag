package com.eastern.jinxin.business.userGroup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignInfoEntity;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;
import com.eastern.jinxin.business.userGroup.service.MacroscopicPictureService;
import com.eastern.jinxin.sys.common.common.utils.Constant;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.statis.service.H62UsageItemLogService;
import com.eastern.jinxin.business.statis.utils.StatisUtils;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:22:25
 */
@RestController
@RequestMapping("userGroup/h62campaigninfo")
public class H62CampaignInfoController extends AbstractController {
	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;
	@Autowired
	private H62UsageItemLogService h62UsageItemLogService;
	@Autowired
	private MacroscopicPictureService macroscopicPictureService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userGroup:h62campaigninfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"campNm"});
		h62CampaignInfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@SysLogAnn("查询群组明细")
	@RequestMapping("/info/{campId}")
	public R info(@PathVariable("campId") Integer campId){
		H62CampaignInfoEntity h62CampaignInfo = h62CampaignInfoService.queryObject(campId);
		return R.ok().put("h62CampaignInfo", h62CampaignInfo);
	}
	
	/**
	 * 查询用户群组包含的标签和符合条件的人员数量
	 */
	@SysLogAnn("查询群组明细")
	@RequestMapping(value = "/queryCampaign")
	public R queryCampaign(Integer campId){
		H62CampaignInfoEntity h62CampaignInfo = h62CampaignInfoService.queryCampaign(campId);
		
		h62UsageItemLogService.insertH62UsageItemLog(campId, StatisUtils.ITEM_TYPE_CAMP, StatisUtils.OPERATE_TYPE_PEOPLE, getUserId());
		
		return R.ok().put("h62CampaignInfo", h62CampaignInfo);
	}
	
	@SysLogAnn("查询群组明细2")
	@RequestMapping(value = "/queryCampaign2")
	public R queryCampaign2(Integer campId){
		
		H62CampaignInfoEntity h62CampaignInfo = h62CampaignInfoService.queryCampaign2(campId);
		h62UsageItemLogService.insertH62UsageItemLog(campId, StatisUtils.ITEM_TYPE_CAMP, StatisUtils.OPERATE_TYPE_PEOPLE, getUserId());
		
		return R.ok().put("h62CampaignInfo", h62CampaignInfo);
	}
	
	private boolean containValue(List<Map<String, Object>> listMap, String key, String value) {
		for(Map<String, Object> m : listMap) {
			if(value.equals(m.get(key))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("创建群组")
	@RequestMapping("/save")
	@RequiresPermissions("userGroup:h62campaigninfo:save")
	public R save(@RequestBody H62CampaignInfoEntity h62CampaignInfo){
		h62CampaignInfoService.save(h62CampaignInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("更新群组")
	@RequestMapping("/update")
	@RequiresPermissions("userGroup:h62campaigninfo:update")
	public R update(@RequestBody H62CampaignInfoEntity h62CampaignInfo){
		h62CampaignInfoService.update(h62CampaignInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("删除群组")
	@RequestMapping("/delete")
	@RequiresPermissions("userGroup:h62campaigninfo:delete")
	public R delete(@RequestBody Integer[] campIds){
		h62CampaignInfoService.deleteBatch(campIds);
		return R.ok();
	}
	
	@SysLogAnn("查询群组特征报告")
	@RequestMapping("/queryCampFeature")
	public R queryCampAllTagDetail(Integer campId) {
		R returnR = R.ok();
		Map<String, Object> campFature = macroscopicPictureService.queryCampFature(campId);
		returnR.put("campId", campId);
		returnR.put("campFature", campFature);
		h62UsageItemLogService.insertH62UsageItemLog(campId, StatisUtils.ITEM_TYPE_CAMP, StatisUtils.OPERATE_TYPE_FEATURE, getUserId());
		return returnR;
	}
	
}
