package com.eastern.jinxin.business.userGroup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.hbase.entity.TagHelperBean;
import com.eastern.jinxin.hbase.entity.TagHelperUtil;
import com.eastern.jinxin.hbase.entity.TagHelperUtil.ByteToBitSet;
import com.eastern.jinxin.hbase.service.impl.HbaseServiceImpl;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.redis.utils.UserTagUtils;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignTagRelaDao;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;
import com.eastern.jinxin.business.userGroup.service.MacroscopicPictureService;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

@Service("macroscopicPictureService")
public class MacroscopicPictureServiceImpl implements MacroscopicPictureService{
	
	@Autowired
	private H62CampaignTagRelaDao h62CampaignTagRelaDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;
	
	private String redis_key_pr = RedisUtils.KEY;
	private String bitop_pr = "areacount:";
	
	public List<TagHelperBean> getAllTags(TagHelperUtil.TagOrder order) {
     TagHelperUtil tagHelperUtil = TagHelperUtil.getInstance(this.jedisPool);
     ByteToBitSet value = TagHelperUtil.ByteToBitSet.NOT_NEED;
     List allTags = tagHelperUtil.getAllTagFromRedis(TagHelperUtil.ByteToBitSet.NOT_NEED);
     TagHelperUtil.sortTagByUsers(allTags, order);
 
     return allTags;
   }

	public List<Map<String, Object>> queryCampAllTagDetail(int campId){
		
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			long sumPersionCount = 0;
			Object tag_group_content = m.get("tag_group_content");
			//公式不为空时为组合标签
			if(null != tag_group_content) {
				String key = h50TaggroupInfoService.getQueryGroupTagKey((String)tag_group_content);
				sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne(key, key));
			}else {
				sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne("" + m.get("tag_ctgy_id"), "" + m.get("tag_id")));
			}
			m.put("sumPersionCount", sumPersionCount);
		}
		
		return campaignTagRelas;
	}
	
	//用户群宏观画像
	public Map<String, Map<String, List<Map<String, Object>>>> queryCampAllTagDetail2(int campId){
		
		Map<String, Map<String, List<Map<String, Object>>>> tagMap = new LinkedMap();
		
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			long sumPersionCount = 0;
			Object tag_group_content = m.get("tag_group_content");
			//公式不为空时为组合标签
			if(null != tag_group_content) {
				String key = h50TaggroupInfoService.getQueryGroupTagKey((String)tag_group_content);
				sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne(key, key));
			}else {
				sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsOne("" + m.get("tag_ctgy_id"), "" + m.get("tag_id")));
			}
			m.put("sumPersionCount", sumPersionCount);
			
			String showparent_nm = (String)m.get("showparent_nm");
			String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
			String tag_nm = (String)m.get("tag_nm");
//			String sumPersionCount = (String)m.get("sumPersionCount");
			
			if(tagMap.containsKey(showparent_nm)) {
				Map<String, List<Map<String, Object>>> cMap = tagMap.get(showparent_nm);
				if(cMap.containsKey(tag_ctgy_nm)) {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumPersionCount);
					cMap.get(tag_ctgy_nm).add(nm);
					
				}else {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumPersionCount);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list.add(nm);
					
					cMap.put(tag_ctgy_nm, list);
				}
			}else {
				Map<String, Object> nm = new HashMap<String, Object>();
				nm.put("name", tag_nm);
				nm.put("count", sumPersionCount);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list.add(nm);
				
				Map<String, List<Map<String, Object>>> cMap = new LinkedMap();
				cMap.put(tag_ctgy_nm, list);
				
				tagMap.put(showparent_nm, cMap);
			}
		}
		
		return tagMap;
	}
	
	//用户群特征报告
	public Map<String, Object> queryCampFature(int campId){
		
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Map<String, List<Map<String, Object>>>> tagMap = new LinkedMap();
		
		Jedis jedis = this.jedisPool.getResource();
		String redisKey = h62CampaignInfoService.campRedisKey(campId);
		long campPersons = jedis.bitcount(redisKey);
		res.put("campPersons", campPersons);//群包含的人数
		
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			long sumPersionCount = 0;
			Object tag_group_content = m.get("tag_group_content");
			//公式不为空时为组合标签
			if(null != tag_group_content) {
				String key = h50TaggroupInfoService.getQueryGroupTagKey((String)tag_group_content);
				sumPersionCount = redisCount(jedis, key, redisKey);
			}else {
				sumPersionCount = redisCount(jedis, "" + m.get("tag_id"), redisKey);
			}
			m.put("sumPersionCount", sumPersionCount);
			
			String showparent_nm = (String)m.get("showparent_nm");
			String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
			String tag_nm = (String)m.get("tag_nm");
			
			if(tagMap.containsKey(showparent_nm)) {
				Map<String, List<Map<String, Object>>> cMap = tagMap.get(showparent_nm);
				if(cMap.containsKey(tag_ctgy_nm)) {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumPersionCount);
					cMap.get(tag_ctgy_nm).add(nm);
					
				}else {
					Map<String, Object> nm = new HashMap<String, Object>();
					nm.put("name", tag_nm);
					nm.put("count", sumPersionCount);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list.add(nm);
					
					cMap.put(tag_ctgy_nm, list);
				}
			}else {
				Map<String, Object> nm = new HashMap<String, Object>();
				nm.put("name", tag_nm);
				nm.put("count", sumPersionCount);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list.add(nm);
				
				Map<String, List<Map<String, Object>>> cMap = new LinkedMap();
				cMap.put(tag_ctgy_nm, list);
				
				tagMap.put(showparent_nm, cMap);
			}
		}
		res.put("tagMap", tagMap);//包含的标签属性和人数
		return res;
	}
	
	public long personCnt(){
		//以性别查询总数
		//var resultTagGrp={"grp_0":{"50010102":["5001010201", "5001010202", "5001010299"]}};
		List<Map<String, Object>> mapLists = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("tag_ctgy_id", "50010102");
		m1.put("tag_id", "5001010210");
		mapLists.add(m1);
		
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("tag_ctgy_id", "50010102");
		m2.put("tag_id", "5001010220");
		mapLists.add(m2);
		
		Map<String, Object> m3 = new HashMap<String, Object>();
		m3.put("tag_ctgy_id", "50010102");
		m3.put("tag_id", "5001010299");
		mapLists.add(m3);
		
		long personCnt = cacheService.sumPersonCount(TagsUtils.gainTagsMany(mapLists));
		return personCnt;
	}
	
	//查询人口在海口分布情况
	public Map<String, Object> queryPersonDistribute(String campId){
		Map<String, Object> res = new HashMap<String, Object>();
		String redisKey = h62CampaignInfoService.campRedisKey(Integer.parseInt(campId));
		Jedis jedis = this.jedisPool.getResource();
		
		//5001011301	海口市
		long hk = redisCount(jedis, "5001011301", redisKey);
		
		//5001011302	三亚市
		long sy = redisCount(jedis, "5001011302", redisKey);
		
		//5001011303	三沙市
		long ss = redisCount(jedis, "5001011303", redisKey);
		
		//5001011304	儋州市
		long dz = redisCount(jedis, "5001011304", redisKey);
		
		//5001011305	五指山市
		long wzs = redisCount(jedis, "5001011305", redisKey);
		
		//5001011306	琼海市
		long qh = redisCount(jedis, "5001011306", redisKey);
		
		//5001011307	文昌市
		long wc = redisCount(jedis, "5001011307", redisKey);
		
		//5001011308	万宁市
		long wn = redisCount(jedis, "5001011308", redisKey);
		
		//5001011309	东方市
		long df = redisCount(jedis, "5001011309", redisKey);
		
		//5001011310	定安县
		long da = redisCount(jedis, "5001011310", redisKey);
		
		//5001011311	屯昌县
		long tc = redisCount(jedis, "5001011311", redisKey);
		
		//5001011312	澄迈县
		long cm = redisCount(jedis, "5001011312", redisKey);
		
		//5001011313	临高县
		long lg = redisCount(jedis, "5001011313", redisKey);
		
		//5001011314	白沙黎族自治县
		long bs = redisCount(jedis, "5001011314", redisKey);
		
		//5001011315	昌江黎族自治县
		long cj = redisCount(jedis, "5001011315", redisKey);
		
		//5001011316	乐东黎族自治县
		long ld = redisCount(jedis, "5001011316", redisKey);
		
		//5001011317	陵水黎族自治县
		long ls = redisCount(jedis, "5001011317", redisKey);
		
		//5001011318	保亭黎族苗族自治县
		long bt = redisCount(jedis, "5001011318", redisKey);
		
		//5001011319	琼中黎族苗族自治县
		long qz = redisCount(jedis, "5001011319", redisKey);
		
		//5001011399	未知
		long wz = redisCount(jedis, "5001011399", redisKey);
		 
		res.put("海口市",hk);
		res.put("三亚市",sy);
		res.put("三沙市",ss);
		res.put("儋州市",dz);
		res.put("五指山市",wzs);
		res.put("琼海市",qh);
		res.put("文昌市",wc);
		res.put("万宁市",wn);
		res.put("东方市",df);
		res.put("定安县",da);
		res.put("屯昌县",tc);
		res.put("澄迈县",cm);
		res.put("临高县",lg);
		res.put("白沙县",bs);
		res.put("昌江县",cj);
		res.put("乐东县",ld);
		res.put("陵水县",ls);
		res.put("保亭县",bt);
		res.put("琼中县",qz);
		res.put("未知",wz);
	
		return res;
	}
	
	private Long redisCount(Jedis jedis, String tagKey, String retKey) {
		String key = this.bitop_pr + tagKey;
		String[] srcKeyArray = new String[] {this.redis_key_pr + tagKey, retKey};
		jedis.bitop(BitOP.AND, key, srcKeyArray);
	    CacheServiceRedisImpl.safeExpireRedisKey(jedis, key);
	    Long nums = jedis.bitcount(key);
		return nums;
	}
	
	public List<Map<String, Object>> queryTagsDistribute(String redisKey){
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Jedis jedis = this.jedisPool.getResource();
		
		Long keycCount = jedis.bitcount(redisKey);
		if(keycCount > 0) {
			//5001011301	海口市
			long hk = redisCount(jedis, "5001011301", redisKey);
			
			//5001011302	三亚市
			long sy = redisCount(jedis, "5001011302", redisKey);
			
			//5001011303	三沙市
			long ss = redisCount(jedis, "5001011303", redisKey);
			
			//5001011304	儋州市
			long dz = redisCount(jedis, "5001011304", redisKey);
			
			//5001011305	五指山市
			long wzs = redisCount(jedis, "5001011305", redisKey);
			
			//5001011306	琼海市
			long qh = redisCount(jedis, "5001011306", redisKey);
			
			//5001011307	文昌市
			long wc = redisCount(jedis, "5001011307", redisKey);
			
			//5001011308	万宁市
			long wn = redisCount(jedis, "5001011308", redisKey);
			
			//5001011309	东方市
			long df = redisCount(jedis, "5001011309", redisKey);
			
			//5001011310	定安县
			long da = redisCount(jedis, "5001011310", redisKey);
			
			//5001011311	屯昌县
			long tc = redisCount(jedis, "5001011311", redisKey);
			
			//5001011312	澄迈县
			long cm = redisCount(jedis, "5001011312", redisKey);
			
			//5001011313	临高县
			long lg = redisCount(jedis, "5001011313", redisKey);
			
			//5001011314	白沙黎族自治县
			long bs = redisCount(jedis, "5001011314", redisKey);
			
			//5001011315	昌江黎族自治县
			long cj = redisCount(jedis, "5001011315", redisKey);
			
			//5001011316	乐东黎族自治县
			long ld = redisCount(jedis, "5001011316", redisKey);
			
			//5001011317	陵水黎族自治县
			long ls = redisCount(jedis, "5001011317", redisKey);
			
			//5001011318	保亭黎族苗族自治县
			long bt = redisCount(jedis, "5001011318", redisKey);
			
			//5001011319	琼中黎族苗族自治县
			long qz = redisCount(jedis, "5001011319", redisKey);
			
			//5001011399	未知
			long wz = redisCount(jedis, "5001011399", redisKey);
			 
			res.add(new HashMap<String, Object>() {{put("name", "海口市"); put("value", hk);}});
			res.add(new HashMap<String, Object>() {{put("name", "三亚市"); put("value", sy);}});
			res.add(new HashMap<String, Object>() {{put("name", "三沙市"); put("value", ss);}});
			res.add(new HashMap<String, Object>() {{put("name", "儋州市"); put("value", dz);}});
			res.add(new HashMap<String, Object>() {{put("name", "五指山市"); put("value", wzs);}});
			res.add(new HashMap<String, Object>() {{put("name", "琼海市"); put("value", qh);}});
			res.add(new HashMap<String, Object>() {{put("name", "文昌市"); put("value", wc);}});
			res.add(new HashMap<String, Object>() {{put("name", "万宁市"); put("value", wn);}});
			res.add(new HashMap<String, Object>() {{put("name", "东方市"); put("value", df);}});
			res.add(new HashMap<String, Object>() {{put("name", "定安县"); put("value", da);}});
			res.add(new HashMap<String, Object>() {{put("name", "屯昌县"); put("value", tc);}});
			res.add(new HashMap<String, Object>() {{put("name", "澄迈县"); put("value", cm);}});
			res.add(new HashMap<String, Object>() {{put("name", "临高县"); put("value", lg);}});
			res.add(new HashMap<String, Object>() {{put("name", "白沙县"); put("value", bs);}});
			res.add(new HashMap<String, Object>() {{put("name", "昌江县"); put("value", cj);}});
			res.add(new HashMap<String, Object>() {{put("name", "乐东县"); put("value", ld);}});
			res.add(new HashMap<String, Object>() {{put("name", "陵水县"); put("value", ls);}});
			res.add(new HashMap<String, Object>() {{put("name", "保亭县"); put("value", bt);}});
			res.add(new HashMap<String, Object>() {{put("name", "琼中县"); put("value", qz);}});
			res.add(new HashMap<String, Object>() {{put("name", "未知"); put("value", wz);}});
			
		}else {
			 
			res.add(new HashMap<String, Object>() {{put("name", "海口市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "三亚市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "三沙市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "儋州市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "五指山市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "琼海市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "文昌市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "万宁市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "东方市"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "定安县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "屯昌县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "澄迈县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "临高县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "白沙县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "昌江县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "乐东县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "陵水县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "保亭县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "琼中县"); put("value", keycCount);}});
			res.add(new HashMap<String, Object>() {{put("name", "未知"); put("value", keycCount);}});
		}
	
		return res;
	}
}
