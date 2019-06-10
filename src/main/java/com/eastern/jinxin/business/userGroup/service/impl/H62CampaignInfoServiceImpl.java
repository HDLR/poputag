package com.eastern.jinxin.business.userGroup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.utils.ObjectJsonUtils;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.serviceImpl.TagQueryResultCacheImpl;
import com.eastern.jinxin.redis.utils.TagGrpToCondition;
import com.eastern.jinxin.redis.utils.TagsUtils;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.statis.dao.H62UsageItemLogDao;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignInfoDao;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignTagRelaDao;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignInfoEntity;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignTagRelaEntity;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;
import com.eastern.jinxin.business.userGroup.service.H62CampaignTagRelaService;

@Service("h62CampaignInfoService")
 public class H62CampaignInfoServiceImpl  extends ServiceImpl<H62CampaignInfoDao, H62CampaignInfoEntity> implements H62CampaignInfoService {
	private AtomicLong count_getUsersDistributeByTag = new AtomicLong(0L);
	@Autowired
	private H62CampaignInfoDao h62CampaignInfoDao;
	@Autowired
	private H62CampaignTagRelaDao h62CampaignTagRelaDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private H62UsageItemLogDao h62UsageItemLogDao;
	
	@Override
	public List<Map<String, String>> queryCampaignTress(){
		return h62CampaignInfoDao.queryCampaignTress();
	}
	
	@Override
	public List<Map<String, String>> queryCampaignTress2(){
		return h62CampaignInfoDao.queryCampaignTress2();
	}
	
	//判断是否是数字
	private boolean isNumeric(String str){
	   Pattern pattern = Pattern.compile("[0-9]*");
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false;
	   }
	   return true;
	}
	
	@Override
	public List<Map<String, Object>> heatAnalysis(List<String> campIds){
		List<Map<String, Object>> mList = new ArrayList<Map<String,Object>>();
		for(String id : campIds) {
			if(this.isNumeric(id)) {
				H62CampaignInfoEntity e = this.queryCampaign(Integer.parseInt(id));
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("campNm", e.getCampNm());
				m.put("sum", e.getSumPersionCount());
				mList.add(m);
			}
		}
		
		//排序
		Collections.sort(mList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                Integer sum1 = Integer.parseInt("" + m1.get("sum"));
                Integer sum2 = Integer.parseInt("" + m2.get("sum"));
                return sum1.compareTo(sum2);
            }
        });
		
		return mList;
	}
	
	@Override
	public H62CampaignInfoEntity queryCampaign(Integer campId) {
		H62CampaignInfoEntity e = h62CampaignInfoDao.selectById(campId);
		
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			String tag_id = "" + m.get("tag_id");
			List<String> checkTags = new ArrayList<String>();
			checkTags.add(tag_id);
			List<Map<String, Object>> ms = h50TaggroupInfoService.queryTagsData(checkTags);
			if(null != ms && ms.size() > 0) {
				m.put("tag_id_group", ms.get(0).get("tag_id_group"));
			}
		}
		e.setTags(campaignTagRelas);
		
		//符合的人员数量
		long sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsMany(campaignTagRelas));
		e.setSumPersionCount(sumPersionCount);
		
		//组合同类标签
		e.setTagMap(TagsUtils.combinationTags((List<Map<String, Object>>)e.getTags()));
		return e;
	}
	
	@Override
	public H62CampaignInfoEntity queryCampaign2(Integer campId) {
		H62CampaignInfoEntity e = h62CampaignInfoDao.selectById(campId);
		
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			String tag_id = "" + m.get("tag_id");
			List<String> checkTags = new ArrayList<String>();
			checkTags.add(tag_id);
			List<Map<String, Object>> ms = h50TaggroupInfoService.queryTagsData(checkTags);
			if(null != ms && ms.size() > 0) {
				m.put("tag_id_group", ms.get(0).get("tag_id_group"));
			}
		}
		e.setTags(campaignTagRelas);
		
		//符合的人员数量
		long sumPersionCount = cacheService.sumPersonCount(TagsUtils.gainTagsMany(campaignTagRelas));
		e.setSumPersionCount(sumPersionCount);
		
		//组合同类标签，大中小类
		e.setCategoryMap(TagsUtils.combinationParent((List<Map<String, Object>>)e.getTags()));
		
		//使用次数
		List<String> list = new ArrayList<>();
		list.add("" + campId);
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		m.put("campIds", list);
		List<Map<String, Object>> mList = h62UsageItemLogDao.queryCampUsageLog(m);
		String usageCount = "0";
		if(!mList.isEmpty()) {
			usageCount = "" + mList.get(0).get("count");
		}
		e.setUsageCount(usageCount);
		
		return e;
	}
	
	@Override
	public String campRedisKey(Integer campId) {
		//包含的标签
		List<Map<String, Object>> campaignTagRelas = h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
		for(Map<String, Object> m : campaignTagRelas) {
			String tag_id = "" + m.get("tag_id");
			List<String> checkTags = new ArrayList<String>();
			checkTags.add(tag_id);
			List<Map<String, Object>> ms = h50TaggroupInfoService.queryTagsData(checkTags);
			if(null != ms && ms.size() > 0) {
				m.put("tag_id_group", ms.get(0).get("tag_id_group"));
			}
		}
		
		Map<String, Object> pramMap = TagsUtils.gainTagsMany(campaignTagRelas);
		if(null != pramMap) {
			TagConditionBase resultCondition = TagGrpToCondition.jsonMapToCondition(pramMap);
			this.count_getUsersDistributeByTag.getAndIncrement();
			Jedis jedis = this.jedisPool.getResource();
			TagQueryResultCacheImpl result = CacheServiceRedisImpl.controllableQueryResultFromTagCondition(resultCondition, jedis, null);
			String retKey = result.getKey();
			return retKey;
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> queryCampaignTagRelas(Integer campId){
		return h62CampaignTagRelaDao.queryH62CampaignTagRela(campId);
	}
	
	@Override
	public H62CampaignInfoEntity queryObject(Integer campId){
		return h62CampaignInfoDao.selectById(campId);
	}
	
	@Override
	@Transactional
	public void save(H62CampaignInfoEntity h62CampaignInfo){
		Date dt = new Date();
		h62CampaignInfo.setUserId(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		h62CampaignInfo.setStartDt(dt);
		h62CampaignInfo.setEndDt(dt);
		h62CampaignInfo.setCreatedTs(dt);
		h62CampaignInfo.setUpdatedTs(dt);
		h62CampaignInfoDao.insert(h62CampaignInfo);
		
		//插入新的
		List ls = h62CampaignInfo.getTags();
		for(Object o : ls) {
			Map<String, Object> m = (Map<String, Object>)o;
			H62CampaignTagRelaEntity e = new H62CampaignTagRelaEntity();
			e.setCampId(h62CampaignInfo.getCampId());
			e.setTagCtgyId("" + m.get("tag_ctgy_id"));
			e.setTagId(new BigInteger("" + m.get("tag_id")));
			e.setCreatedTs(dt);
			e.setUpdatedTs(dt);
			e.setTagGroupSeq(1);
			h62CampaignTagRelaDao.insertH62CampaignTagRela(e);
		}
	}
	
	//只能修改用户群组包含的标签属性
	@Override
	@Transactional
	public void update(H62CampaignInfoEntity h62CampaignInfo){
		
		//删除原有的
		EntityWrapper<H62CampaignTagRelaEntity> tagRelaWrapper = new EntityWrapper<H62CampaignTagRelaEntity>();
		H62CampaignTagRelaEntity tagRelaTag = new H62CampaignTagRelaEntity();
		tagRelaTag.setCampId(h62CampaignInfo.getCampId());
		tagRelaWrapper.setEntity(tagRelaTag);
		h62CampaignTagRelaDao.delete(tagRelaWrapper);
		
		//插入新的
		List ls = h62CampaignInfo.getTags();
		for(Object o : ls) {
			Map<String, Object> m = (Map<String, Object>)o;
			H62CampaignTagRelaEntity e = new H62CampaignTagRelaEntity();
			e.setCampId(h62CampaignInfo.getCampId());
			e.setTagCtgyId("" + m.get("tag_ctgy_id"));
			e.setTagId(new BigInteger("" + m.get("tag_id")));
			Date dt = new Date();
			e.setCreatedTs(dt);
			e.setUpdatedTs(dt);
			e.setTagGroupSeq(1);
			h62CampaignTagRelaDao.insertH62CampaignTagRela(e);
		}
		
		h62CampaignInfoDao.updateById(h62CampaignInfo);
	}
	
	@Override
	public void delete(Integer campId){
		h62CampaignInfoDao.deleteById(campId);
	}
	
	@Override
	public void deleteBatch(Integer[] campIds){
		h62CampaignInfoDao.deleteBatchIds(Arrays.asList(campIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62CampaignInfoEntity> page = new Page<H62CampaignInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62CampaignInfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62CampaignInfoDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
