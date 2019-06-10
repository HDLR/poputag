package com.eastern.jinxin.business.statis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.service.H50TagInfoService;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.business.statis.service.H62UsageItemLogService;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;

@RestController
@RequestMapping("statis")
public class StatisController {
	
	@Autowired
	private H50TagInfoService h50TagInfoService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private H62UsageItemLogService h62UsageItemLogService;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;

	//统计分析-标签概况
	@SysLogAnn("标签概况")
	@RequestMapping("/tags/profile")
	public R tagsProfile(@RequestParam("checkTags") List<String> checkTags) {
		R returnR = R.ok();
		if(null != checkTags && checkTags.size()>0) {
			List<Map<String, Object>> maps = h50TagInfoService.queryTagsAndGroupTagsData(checkTags);
			for(Map<String, Object> m : maps) {
				long sumCount = 0;
				Object tag_group_content = m.get("tag_group_content");
				//公式不为空时为组合标签
				if(null != tag_group_content) {
					String key = h50TaggroupInfoService.getQueryGroupTagKey((String)tag_group_content);
					sumCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne(key, key));
				}else {
					sumCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne("" + m.get("tag_ctgy_id"), "" + m.get("tag_id")));
				}
				m.put("sumCount", sumCount);
			}
			
			/*
			 	c.tag_ctgy_id showparent_id,
				c.tag_ctgy_nm showparent_nm,
				b.tag_ctgy_id,
				b.tag_ctgy_nm,
				a.tag_id,
				a.tag_nm,
			 */
			Map<String, Map<String, List<Map<String, Object>>>> tagMap = new LinkedMap();
			for(Map<String, Object> m : maps) {
				
				String showparent_nm = (String)m.get("showparent_nm");
				String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
				String tag_nm = (String)m.get("tag_nm");
				long sumCount = (long)m.get("sumCount");
				
				if(tagMap.containsKey(showparent_nm)) {
					Map<String, List<Map<String, Object>>> cMap = tagMap.get(showparent_nm);
					if(cMap.containsKey(tag_ctgy_nm)) {
						Map<String, Object> nm = new HashMap<String, Object>();
						nm.put("name", tag_nm);
						nm.put("count", sumCount);
						cMap.get(tag_ctgy_nm).add(nm);
						
					}else {
						Map<String, Object> nm = new HashMap<String, Object>();
						nm.put("name", tag_nm);
						nm.put("count", sumCount);
						
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						list.add(nm);
						
						cMap.put(tag_ctgy_nm, list);
					}
				}else {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumCount);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list.add(nm);
					
					Map<String, List<Map<String, Object>>> cMap = new LinkedMap();
					cMap.put(tag_ctgy_nm, list);
					
					tagMap.put(showparent_nm, cMap);
				}
			}
			returnR.put("tags", tagMap);
		}
		return returnR;
	}
	
	//统计分析-标签应用分析
	@SysLogAnn("标签应用分析")
	@RequestMapping("/tags/usage")
	public R tagsUsage(@RequestParam("checkTags") List<String> checkTags, String countDesc, String limit) {
		R returnR = R.ok();
		
		//List<Map<String, Object>> tags = h50TagInfoService.queryTagsUsage(checkTags, countDesc, limit);
		//Collections.reverse(tags);//逆序
		//returnR.put("tags", tags);
		
		List<Map<String, Object>> maps = h50TagInfoService.queryTagsAndGroupTagsDataByCtgyId(checkTags);
		
		/*
	 	c.tag_ctgy_id showparent_id,
		c.tag_ctgy_nm showparent_nm,
		b.tag_ctgy_id,
		b.tag_ctgy_nm,
		a.tag_id,
		a.tag_nm,
	 */
		Map<String, Map<String, List<Map<String, Object>>>> tagMap = new LinkedMap();
		for(Map<String, Object> m : maps) {
			
			String showparent_nm = (String)m.get("showparent_nm");
			String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
			String tag_nm = (String)m.get("tag_nm");
			
			int sumCount = h50TagInfoService.tagUsageByTagId("" + m.get("tag_id"));
			
			if(tagMap.containsKey(showparent_nm)) {
				Map<String, List<Map<String, Object>>> cMap = tagMap.get(showparent_nm);
				if(cMap.containsKey(tag_ctgy_nm)) {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumCount);
					cMap.get(tag_ctgy_nm).add(nm);
					
				}else {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumCount);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list.add(nm);
					
					cMap.put(tag_ctgy_nm, list);
				}
			}else {
				Map<String, Object> nm = new HashMap<String, Object>();
				nm.put("name", tag_nm);
				nm.put("count", sumCount);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list.add(nm);
				
				Map<String, List<Map<String, Object>>> cMap = new LinkedMap();
				cMap.put(tag_ctgy_nm, list);
				
				tagMap.put(showparent_nm, cMap);
			}
		}
		
		returnR.put("usages", tagMap);
		
		return returnR;
	}
	
	//统计分析-群应用分析
	@SysLogAnn("群应用分析")
	@RequestMapping("/usage/campUsageLog")
	public R queryCampUsageLog(@RequestParam("campIds") List<String> campIds) {
		R returnR = R.ok();
		List<Map<String, Object>> rm = h62UsageItemLogService.queryCampUsageLog(campIds);
		returnR.put("usageLog", rm);
		return returnR;
	}
	
	@RequestMapping("/queryCampaignTress")
	public R queryCampaignTress() {
		R returnR = R.ok();
		List<Map<String, String>> rMaps = h62CampaignInfoService.queryCampaignTress();
		returnR.put("campTress", rMaps);
		return returnR;
	}
	
	@RequestMapping("/queryCampaignTress2")
	public R queryCampaignTress2() {
		R returnR = R.ok();
		List<Map<String, String>> rMaps = h62CampaignInfoService.queryCampaignTress2();
		returnR.put("campTress", rMaps);
		return returnR;
	}
}
