package com.eastern.jinxin.business.label.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.entity.H50TagInfoEntity;
import com.eastern.jinxin.business.label.service.H50TagInfoService;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-10 11:30:11
 */
@RestController
@RequestMapping("label/h50taginfo")
public class H50TagInfoController extends AbstractController {
	@Autowired
	private H50TagInfoService h50TagInfoService;
	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	
	/**
	 * 列表
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-列表查询")
	@RequestMapping("/list")
	@RequiresPermissions("h50taginfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"tagId","tagCtgyId","tagNm","activeInd"});
		h50TagInfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/*
	 * 查询h50_tag_category_info和h50_tag_info的和表数据
	 */
	@RequestMapping("/queryAllTags")
	public R queryAllTags(String campId) {
		R returnR = R.ok();
		
		List<Map<String, Object>> tags = h50TagInfoService.queryAllTags();
		returnR.put("tags", tags);
		
		if(!StringUtils.isBlank(campId)) {
			List<Map<String, Object>> checkTags = h62CampaignInfoService.queryCampaignTagRelas(Integer.parseInt(campId));
			returnR.put("checkTags", checkTags);
		}
		
		return returnR;
	}
	
	//查询所有标签包含组合标签
	@RequestMapping("/queryAllTagsAndGroupTags")
	public R queryAllTagsAndGroupTags(String campId) {
		R returnR = R.ok();
		
		List<Map<String, Object>> tags = h50TagInfoService.queryAllTagsAndGroupTags();
		returnR.put("tags", tags);
		
		if(!StringUtils.isBlank(campId)) {
			List<Map<String, Object>> checkTags = h62CampaignInfoService.queryCampaignTagRelas(Integer.parseInt(campId));
			returnR.put("checkTags", checkTags);
		}
		
		return returnR;
	}
	
	/**
	 * 获取符合勾选标签属性条件的人员人数
	 */
	@RequestMapping("/queryPersonCountSum")
	public R queryPersonCountSum(@RequestParam("checkTags") List<String> checkTags) {
		R returnR = R.ok();
		
		returnR.put("tags", null);
		returnR.put("sumPersionCount", 0);
		
		if(null != checkTags && checkTags.size()>0) {
			List<Map<String, Object>> mAll = new ArrayList<Map<String,Object>>();
			
			List<Map<String, Object>> maps = h50TagInfoService.queryTagsData(checkTags);
			if(null != maps && maps.size() > 0) {
				mAll.addAll(maps);
			}
			
			//需要通过tag_id查询出公式，通过计算返回可以获取计算后值的key
			List<Map<String, Object>> mapsGroup = h50TaggroupInfoService.queryTagsData(checkTags);
			if(null != mapsGroup && mapsGroup.size() > 0) {
				mAll.addAll(mapsGroup);
			}
			
			returnR.put("tags", mAll);
			
			//符合的人员数量
			long sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsMany(mAll));
			returnR.put("sumPersionCount", sumPersionCount);
			
			returnR.put("tagMap", TagsUtils.combinationTags((List<Map<String, Object>>)mAll));
		}
		
		return returnR;
	}
	
	
	/**
	 * 信息
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-查看")
	@RequestMapping("/info/{tagId}")
	@RequiresPermissions("h50taginfo:info")
	public R info(@PathVariable("tagId") BigInteger tagId){
		H50TagInfoEntity h50TagInfo = h50TagInfoService.queryObject(tagId);
		
		return R.ok().put("h50TagInfo", h50TagInfo);
	}
	
	/**
	 * 保存
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-保存")
	@RequestMapping("/save")
	@RequiresPermissions("h50taginfo:save")
	public R save(@RequestBody H50TagInfoEntity h50TagInfo){
		h50TagInfo.setCreateUser(getUserId().intValue());
		h50TagInfo.setCreatedTs(new Date());
		h50TagInfo.setUpdatedTs(new Date());
		h50TagInfoService.save(h50TagInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-修改")
	@RequestMapping("/update")
	@RequiresPermissions("h50taginfo:update")
	public R update(@RequestBody H50TagInfoEntity h50TagInfo){
		h50TagInfoService.update(h50TagInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	
	@RequestMapping("/delete")
	@RequiresPermissions("h50taginfo:delete")
	public R delete(@RequestBody Integer[] tagIds){
		h50TagInfoService.deleteBatch(tagIds);
		return R.ok();
	}
	
	/**
	 * 
	 * <p>Title : del</p>
	 * <p>Description : 删除标签项</p>	
	 * @param id
	 * @param type
	 * @return
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-删除")
	@RequestMapping("/deleteTag/{tagId}")
	@RequiresPermissions("h50taginfo:delete")
	public Map<String, Object> del(@PathVariable("tagId")BigInteger tagId) {
		int isok = this.h50TagInfoService.del(tagId);
		String msg = "";
		if (isok == 0) {
			msg = "标签已被引用，不可删除";
		} else if (isok == 1) {
			msg = "删除成功";
		}
		return new R().put("msg",msg);
	}
	/**
	 * 标签上线,根据ID修改状态
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-上线")
	@RequestMapping("/upline/{tagId}")
	@RequiresPermissions("h50taginfo:up")
	public R upline(@PathVariable("tagId")BigInteger tagId,H50TagInfoEntity h50TagInfo){
		h50TagInfo.setTagId(tagId);
		h50TagInfo.setUpdateUser(getUserId());
		h50TagInfoService.upLine(h50TagInfo);
		return R.ok();
	}
	
	/**
	 * 
	 * <p>Title : downLine</p>
	 * <p>Description : 下线标签项</p>	
	 * @param id
	 * @param type
	 * @return
	 */
	//标签工厂-标签规则
	@SysLogAnn("标签规则-下线")
	@RequestMapping("/downTag/{tagId}")
	@RequiresPermissions("h50taginfo:down")
	public Map<String, Object> downLine(@PathVariable("tagId")BigInteger tagId) {
		int isok = this.h50TagInfoService.downLine(tagId);
		String msg = "";
		if (isok == 0) {
			msg = "标签已被引用，不可下线";
		} else if (isok == 1) {
			msg = "标签下线成功";
		}
		return R.ok().put("msg",msg);
	}
	
	
}
