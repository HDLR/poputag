package com.eastern.jinxin.business.recommend.controller;

import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.common.common.utils.Constant;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagEntity;
import com.eastern.jinxin.business.recommend.entity.H62PolicyTagTypeEntity;
import com.eastern.jinxin.business.recommend.entity.H62RecomPolicyEntity;
import com.eastern.jinxin.business.recommend.entity.PolicyParam;
import com.eastern.jinxin.business.recommend.service.H62PolicyTagService;
import com.eastern.jinxin.business.recommend.service.H62PolicyTagTypeService;
import com.eastern.jinxin.business.recommend.service.H62RecomPolicyService;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-14 15:00:30
 */
@RestController
@RequestMapping("recommend/h62recompolicy")
public class H62RecomPolicyController extends AbstractController {
	@Autowired
	private H62RecomPolicyService h62RecomPolicyService;
	@Autowired
	private H62PolicyTagService h62PolicyTagService;
	@Autowired
	private H62PolicyTagTypeService h62PolicyTagTypeService;
	
	@RequestMapping("/checkTagsName")
	public R checkTagsName(@RequestParam("checkTags") List<String> checkTags) {
		R returnR = R.ok();
		List<Map<String, Object>> tagsMap = h62RecomPolicyService.checkTagsName(checkTags);
		returnR.put("tags", tagsMap);
		returnR.put("tagMap", TagsUtils.combinationPolicyTags(tagsMap));
		return returnR;
	}
	
	@RequestMapping("/queryallTagType")
	public R queryallTagType() {
		List<H62PolicyTagTypeEntity> tagTypes = h62PolicyTagTypeService.queryallTagType();
		return R.ok().put("tagTypes", tagTypes);
	}
	
	@RequestMapping("/queryAllPolicyTags")
	public R queryAllPolicyTags(String id) {
		R returnR = R.ok();
		
		List<Map<String, Object>> tags = h62PolicyTagService.queryAllPolicyTags();
		returnR.put("tags", tags);
		
		if(!StringUtils.isBlank(id)) {
			List<Map<String, Object>> checkTags = h62RecomPolicyService.queryRelaTag(Integer.parseInt(id));
			returnR.put("checkTags", checkTags);
		}
		
		return returnR;
	}
	
	@RequestMapping("/queryAllPolicyTags2")
	public List<Map<String, Object>> queryAllPolicyTags2() {
		
		List<Map<String, Object>> tags = h62PolicyTagService.queryAllPolicyTags();
		
		return tags;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("recom:recompolicy:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"policyNm"});
		h62RecomPolicyService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		H62RecomPolicyEntity h62RecomPolicy = h62RecomPolicyService.queryObject(id);
		
		return R.ok().put("h62RecomPolicy", h62RecomPolicy);
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("政策配置创建")
	@RequestMapping("/save")
	@RequiresPermissions("recom:recompolicy:save")
	public R save(@RequestBody H62RecomPolicyEntity h62RecomPolicy){
		h62RecomPolicyService.save(h62RecomPolicy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("政策配置修改")
	@RequestMapping("/update")
	@RequiresPermissions("recom:recompolicy:update")
	public R update(@RequestBody H62RecomPolicyEntity h62RecomPolicy){
		h62RecomPolicyService.update(h62RecomPolicy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("政策配置删除")
	@RequestMapping("/delete")
	@RequiresPermissions("recom:recompolicy:delete")
	public R delete(@RequestBody Integer[] ids){
		h62RecomPolicyService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@RequestMapping("/queryAllPolicyTagType")
	public R queryAllPolicyTagType() {
		List<H62PolicyTagTypeEntity> tagTypes = h62PolicyTagTypeService.queryallTagType();
		return R.ok().put("tagTypes", tagTypes);
	}
	
	@SysLogAnn("政策配置创建")
	@RequestMapping("/savePolicyTag")
	public R savePolicyTag(@RequestBody PolicyParam policyParam) {
		//政策标签类别
		if(1 == policyParam.getType()) {
			H62PolicyTagTypeEntity h62PolicyTagType = new H62PolicyTagTypeEntity();
			h62PolicyTagType.setTagTypeNm(policyParam.getName());
			h62PolicyTagTypeService.save(h62PolicyTagType);
		}
		
		//政策标签
		if(2 == policyParam.getType()) {
			H62PolicyTagEntity h62PolicyTag = new H62PolicyTagEntity();
			h62PolicyTag.setTagNm(policyParam.getName());
			h62PolicyTag.setTagTypeId(policyParam.getParent_id());
			h62PolicyTagService.save(h62PolicyTag);
		}
		
		return R.ok();
	}
	
}
