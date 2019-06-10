package com.eastern.jinxin.business.userGroup.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.business.userGroup.service.MacroscopicPictureService;
import com.eastern.jinxin.hbase.entity.TagHelperUtil;
import com.eastern.jinxin.hbase.service.impl.HbaseServiceImpl;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.serviceImpl.TagQueryResultCacheImpl;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.redis.utils.TagGrpToCondition;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.service.H50TagInfoService;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.statis.service.H62UsageItemLogService;
import com.eastern.jinxin.business.statis.utils.StatisUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("userGroup/macroscopicPicture")
public class MacroscopicPictureController extends AbstractController {

	@Autowired
	private MacroscopicPictureService macroscopicPictureService;
	@Autowired
	private HbaseServiceImpl hbaseService;
	@Autowired
	private H62UsageItemLogService h62UsageItemLogService;
	@Autowired
	private H50TagInfoService h50TagInfoService;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private JedisPool jedisPool;
	
	@SysLogAnn("群组宏观画像")
	@RequestMapping("/queryCampAllTagDetail")
	public R queryCampAllTagDetail(Integer campId) {
		R returnR = R.ok();
		Map<String, Map<String, List<Map<String, Object>>>> campAllTagDetails = macroscopicPictureService.queryCampAllTagDetail2(campId);
		returnR.put("campId", campId);
		returnR.put("campAllTagDetails", campAllTagDetails);
		
		h62UsageItemLogService.insertH62UsageItemLog(campId, StatisUtils.ITEM_TYPE_CAMP, StatisUtils.OPERATE_TYPE_MACR, getUserId());
		
		//returnR.put("personCnt", macroscopicPictureService.personCnt());
		return returnR;
	}
	
	//获取人数总数量
	@RequestMapping("/personCntSum")
	public R personCntSum() {
		R returnR = R.ok();
		returnR.put("personCntSum", macroscopicPictureService.personCnt());
		return returnR;
	}
	
	@SysLogAnn("标签人数前十位画像")
	@RequestMapping("/queryTagsTop")
	public R queryTagsTop(String topN) {
		R returnR = R.ok();
		
		int topNInt = 10;
		if (topN != null) {
			topNInt = new Integer(topN).intValue();
		}
		List allTagList = macroscopicPictureService.getAllTags(TagHelperUtil.TagOrder.ORDER_DESC);
		List<Map<String, Object>> topN_tags = hbaseService.getTopNTags(allTagList, topNInt);
		Collections.reverse(topN_tags);//逆序
		return returnR.put("topN_tags", topN_tags);
	}
	
	@RequestMapping("/queryPersonDistribute")
	public R queryPersonDistribute(String campId) {
		R returnR = R.ok();
		long yz = 0;//已知
		long wz = 0;//未知
		long maxCount = 0;//最大人数
		List<Map<String, Object>> resMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> areaMap = macroscopicPictureService.queryPersonDistribute(campId);
		for(String key : areaMap.keySet()) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", key);
			long num = (long)areaMap.get(key);
			m.put("value", num);
			resMap.add(m);
			yz = yz + num;
			if(num > maxCount) {
				maxCount = num;
			}
		}
		wz = (long)areaMap.get("未知");
		yz = yz - wz;
		returnR.put("persons", resMap);
		returnR.put("wz", wz);//已知
		returnR.put("yz", yz);//未知
		returnR.put("maxCount", maxCount);
		return returnR;
	}
	
	@RequestMapping("/queryTagsDistribute")
	public R queryTagsDistribute(@RequestParam("checkTags") List<String> checkTags) {
		R returnR = R.ok();
		
		List<Map<String, Object>> campaignTagRelas = h50TagInfoService.queryTagsAndGroupTagsData(checkTags);
		Map<String, Map<String, List<Map<String, String>>>> tagMap = new LinkedMap();
		
		for(Map<String, Object> m : campaignTagRelas) {
			String tag_id = "" + m.get("tag_id");
			List<String> check = new ArrayList<String>();
			check.add(tag_id);
			List<Map<String, Object>> ms = h50TaggroupInfoService.queryTagsData(check);
			if(null != ms && ms.size() > 0) {
				m.put("tag_id_group", ms.get(0).get("tag_id_group"));
			}
		}
		
		String retKey = "";
		Map<String, Object> pramMap = TagsUtils.gainTagsMany(campaignTagRelas);
		if(null != pramMap) {
			TagConditionBase resultCondition = TagGrpToCondition.jsonMapToCondition(pramMap);
			Jedis jedis = this.jedisPool.getResource();
			TagQueryResultCacheImpl result = CacheServiceRedisImpl.controllableQueryResultFromTagCondition(resultCondition, jedis, null);
			retKey = result.getKey();
		}
		
		List<Map<String, Object>> areaMap = macroscopicPictureService.queryTagsDistribute(retKey);
		
		returnR.put("areaMap", areaMap);
		return returnR;
	}
	
	@RequestMapping("/queryTagArea")
	public R queryTagArea(@RequestParam("checkTags") List<String> checkTags) {
		R returnR = R.ok();
		
		List<Map<String, Object>> maps = h50TagInfoService.queryTagsAndGroupTagsData(checkTags);
		Map<String, List<Map<String, Object>>> tagMap = new LinkedMap();
		for(Map<String, Object> m : maps) {
			
			String tag_nm = (String)m.get("tag_nm");
			String tag_id = "" + m.get("tag_id");
			
			List<Map<String, Object>> areaMap = macroscopicPictureService.queryTagsDistribute(RedisUtils.KEY + tag_id);
			tagMap.put(tag_nm, areaMap);
		}
		
		returnR.put("echartData", tagMap);
		return returnR;
	}
}
