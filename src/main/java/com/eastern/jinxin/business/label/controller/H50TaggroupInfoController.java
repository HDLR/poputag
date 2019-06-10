package com.eastern.jinxin.business.label.controller;

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
import com.eastern.jinxin.business.label.entity.H50TaggroupInfoEntity;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-22 10:12:49
 */
@RestController
@RequestMapping("label/h50taggroupinfo")
public class H50TaggroupInfoController extends AbstractController {
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private CacheService cacheService;
	
	private String prefix = RedisUtils.KEY;
	/**
	 * 列表
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-列表查询")
	@RequestMapping("/list")
	@RequiresPermissions("h50taggroupinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		h50TaggroupInfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 信息
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-查看信息")
	@RequestMapping("/info/{tagId}")
	@RequiresPermissions("h50taggroupinfo:info")
	public R info(@PathVariable("tagId") Integer tagId){
		H50TaggroupInfoEntity h50TaggroupInfo = h50TaggroupInfoService.queryObject(tagId);
		String tagGroupContent=h50TaggroupInfo.getTagGroupContent();
		String key=h50TaggroupInfoService.getQueryGroupTagKey(tagGroupContent);
		if(key!=null){
			long sumPersionCount = cacheService.queryCountByKey(prefix+key);
			h50TaggroupInfo.setTagHasPersonCount(sumPersionCount);
		}else{
			return R.error().put("msg", "组合标签无法计算,请您重新组合！").put("h50TaggroupInfo", h50TaggroupInfo); 
		}
		return R.ok().put("h50TaggroupInfo", h50TaggroupInfo);
	}
	
	
	/**
	 * 保存
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-保存")
	@RequestMapping("/save")
	@RequiresPermissions("h50taggroupinfo:save")
	public R save(@RequestBody H50TaggroupInfoEntity h50TaggroupInfo){
      h50TaggroupInfo.setCreatedTs(new Date());
      h50TaggroupInfo.setUpdatedTs(new Date());
      h50TaggroupInfoService.save(h50TaggroupInfo);
      return R.ok();
	}
	
	/**
	 * 修改
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-修改")
	@RequestMapping("/update")
	@RequiresPermissions("h50taggroupinfo:update")
	public R update(@RequestBody H50TaggroupInfoEntity h50TaggroupInfo){
		h50TaggroupInfoService.update(h50TaggroupInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-删除")
	@RequestMapping("/delete")
	@RequiresPermissions("h50taggroupinfo:delete")
	public R delete(@RequestBody Integer[] tagIds){
		h50TaggroupInfoService.deleteBatch(tagIds);
		
		return R.ok();
	}
	
	/**
	 * 用组合标签的内容去计算是否合法
	 */
	//标签工厂-组合标签
	@SysLogAnn("组合标签-验证组合标签")
	@RequestMapping("/isLegal/{tagGroupContent}")
	public R info(@PathVariable("tagGroupContent") String tagGroupContent){
		String key=h50TaggroupInfoService.isRightFormat(tagGroupContent);
		if(key!=null){
			return R.ok();
		}else{
			return R.error(); 
		}
	}
}
