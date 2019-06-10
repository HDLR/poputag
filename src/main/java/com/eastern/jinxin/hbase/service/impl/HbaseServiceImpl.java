package com.eastern.jinxin.hbase.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eastern.jinxin.sys.common.utils.StringUtils;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import com.eastern.jinxin.business.label.dao.H50TagInfoDao;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.userGroup.dao.H50FlatRuleConfigDao;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignInfoDao;
import com.eastern.jinxin.business.userGroup.entity.H50FlatRuleConfigEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.hbase.entity.HBaseUtil;
import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.hbase.entity.TUserInfo._CustInfo;
import com.eastern.jinxin.hbase.entity.TagHelperBean;
import com.eastern.jinxin.hbase.entity.TagHelperUtil;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.redis.utils.TagGrpToCondition;
import redis.clients.jedis.JedisPool;


@Service("hbaseService")
public class HbaseServiceImpl{
	private static final Logger logger = LoggerFactory.getLogger(HbaseServiceImpl.class);
	private ThreadLocal<Integer> pageNumLocal = new ThreadLocal<Integer>();
	private ThreadLocal<Integer> pageSizeLocal = new ThreadLocal<Integer>();
	private ThreadLocal<Integer> totalCntLocal = new ThreadLocal<Integer>();
	private int EACH_COUNT = 10;
	private static Table userTable1 = null;
	
	@Autowired
	private H62CampaignInfoDao h62CampaignInfoDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private HBaseConnectionMgr connectionMgr;
	@Autowired
	private H50TagInfoDao h50TagInfoDao;
	@Autowired
	private H50TaggroupInfoService h50TaggroupInfoService;
	@Autowired
	private H50FlatRuleConfigDao h50FlatRuleConfigDao;
	
	public List<Map<String, Object>> getTopNTags(List<TagHelperBean> allTagList, int topN){
     logger.debug("start running get top n tags all tag list");
     return getTagInfoList(allTagList, 0, topN, true);
    }
	public Map<String,Object> getUserDetail(String userGuid){
		Map<String,Object> resMap=new HashMap<>();
		Get get = new Get(userGuid.getBytes());
		get.addFamily(_CustInfo.ColFamily());
		try {
			
			Table userInfoTable = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
			Result result = userInfoTable.get(get);
			if (HBaseUtil.isAnValidResult(result)) {
				//System.out.println("gerenxinxi:-------->"+result);
				//查询表select * from h50_flat_rule_config获取字段
				List<H50FlatRuleConfigEntity> configList=h50FlatRuleConfigDao.getRuleConfig("1");
				for(H50FlatRuleConfigEntity configInfo:configList){
					String key = configInfo.getIndxClmnNm().toLowerCase();
					String flatClmnNm = configInfo.getFlatClmnNm();
					String type=configInfo.getTagCtgyNm();//分类名称
					String colmn=""+key;
					String value = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), colmn.getBytes());
						if(!StringUtils.isBlank(type)){
							if(resMap.containsKey(type)){
								List<Map<String, String>> tmpList=(List<Map<String, String>>)resMap.get(type);
								Map<String,String> mapInfo=new HashMap<String,String>();
								mapInfo.put("tag_ctgy_nm", flatClmnNm);
								mapInfo.put("tag_nm", value);
								tmpList.add(mapInfo);
							}else{
								List<Map<String, String>> tmpList=new ArrayList<>();
								Map<String,String> mapInfo=new HashMap<String,String>();
								mapInfo.put("tag_ctgy_nm", flatClmnNm);
								mapInfo.put("tag_nm", value);
								tmpList.add(mapInfo);
								resMap.put(type, tmpList);
							}
						}
				}
			}
		} catch (Exception e) {
			logger.error("failed to find record ", e);
		}
		
		String resss = JSON.toJSONString(resMap);
		System.out.println("resss======" + resss);
		
		return resMap;
	}
	public List<Map<String, Object>> getUserAllTag(String userGuid){
     TagHelperUtil tagHelperUtil = TagHelperUtil.getInstance(this.jedisPool);
 
     int eachCount = 10;
 
     logger.debug("start check user all tag from redis.....");
     List tagList = tagHelperUtil.checkUserAllTag(new Long(userGuid));
     logger.debug("check user all tag from redis has done.....tag list size {}", Integer.valueOf(tagList.size()));
 
     int forTime = 1;
     if (tagList.size() > eachCount) {
       forTime = tagList.size() / eachCount;
       if (tagList.size() % eachCount > 0)
         forTime++;
     }
     List resultList = new ArrayList();
     logger.debug("start check out user information ,check time {} and the count of each check is {}", Integer.valueOf(forTime), Integer.valueOf(eachCount));
     for (int i = 0; i < forTime; i++) {
       int startNum = i * eachCount;
       int endNum = startNum + eachCount;
       if (forTime == i + 1) {
         endNum = startNum + tagList.size() % eachCount;
       }
 
       List tagInfoList = getTagInfoList(tagList, startNum, endNum, false);
       resultList.addAll(tagInfoList);
     }
     return resultList;
   }
	
	private List<Map<String, Object>> getTagInfoList(List<TagHelperBean> allTagList, int startNum, int endNum, boolean ignoreUnknow){
     logger.debug("allTagList data start num {} , end num {} , all tag list size is {}, and ignore unknow stats is {}", new Object[] { Integer.valueOf(startNum), Integer.valueOf(endNum), allTagList, Boolean.valueOf(ignoreUnknow) });
 
     List searchList = new ArrayList();
     Map tagAndCountTmp = new HashMap();
 
     List<Map<String, Object>> tagResult = new ArrayList();
     TagHelperBean tagHelperBean;
     if (ignoreUnknow) {
       int eachAllTagListTime = 0;
       int forTime = 0;
       while (true) {
         logger.debug("while each is running....... eachAllTagListTime value is {},tagResult size is {}", Integer.valueOf(eachAllTagListTime), Integer.valueOf(tagResult.size()));
         if (eachAllTagListTime == allTagList.size()) {
           break;
         }
         if (tagResult.size() == 10) {
           break;
         }
         int eachNum = endNum - startNum;
         logger.debug("batch check tag num ,eachNum value is {},and fortime value is {}", Integer.valueOf(eachNum), Integer.valueOf(forTime));
         for (int s = eachNum * forTime; s < eachNum * (forTime + 1); s++) {
            tagHelperBean = (TagHelperBean)allTagList.get(s);
           eachAllTagListTime++;
           String realTagId = tagHelperBean.getTagId().replace(RedisUtils.KEY, "");
           List searchListTmp = new ArrayList();
           searchListTmp.add(realTagId);
           tagAndCountTmp.put(realTagId, tagHelperBean.getUserCount());
 
           filterUnknowTag(searchListTmp, tagResult);
 
           if (eachAllTagListTime == allTagList.size()) {
             break;
           }
         }
         forTime++;
       }
     }
     else {
       for (int s = startNum; s < endNum; s++) {
         tagHelperBean = (TagHelperBean)allTagList.get(s);
 
         String realTagId = tagHelperBean.getTagId().replace(RedisUtils.KEY, "");
         searchList.add(realTagId);
         tagAndCountTmp.put(realTagId, tagHelperBean.getUserCount());
       }
 
       if(null != searchList && searchList.size() > 0) {
    	   Map paramMap = new HashMap();
    	   paramMap.put("tagList", searchList);
           tagResult = h50TagInfoDao.getTagInfoByTaList(paramMap);
       }
     }
 
     logger.debug("tag info list before filter : {}", tagResult.toString());
 
     List finalResult = new ArrayList();
 
     for (Map tagMap : tagResult)
     {
       String tagNm = (String)tagMap.get("tagNm");
       tagMap.put("userCount", tagAndCountTmp.get(tagMap.get("tagId").toString()));
 
       if ((tagNm != null) && ("未知".equals(tagNm))) {
         continue;
       }
       finalResult.add(tagMap);
     }
 
     logger.debug("tag info list after filter : {}", finalResult.toString());
 
     Collections.sort(finalResult, this.comparable);
 
     return finalResult;
   }
	
	private void filterUnknowTag(List tagList, List resultList){
     logger.debug("start filter unknow tag ,before filter result list value is {}", resultList);
     Map paramMap = new HashMap();
     paramMap.put("tagList", tagList);
     List<Map<String,Object>> tagResult = h50TagInfoDao.getTagInfoByTaList(paramMap);
     logger.debug("this list ------ {} --------will filter", tagResult);
     List unknowList = new ArrayList();
     for (Map tagMap : tagResult) {
       String tagNm = (String)tagMap.get("tagNm");
       if (!"未知".equals(tagNm)) {
         unknowList.add(tagMap.get("tagId"));
         if (resultList.size() == 10) {
           return;
         }
         resultList.add(tagMap);
         if (resultList.size() == 10) {
           return;
         }
       }
     }
 
     logger.debug("unknow list {} ", unknowList);
 
     logger.debug("after filter result list value is {}", resultList);
   }

	public List<TUserInfo> createPersonListFromHbase(PageInfo pageInfo, String screeningId, int pageNum, int pageSize) {
		this.pageNumLocal.set(Integer.valueOf(pageNum));
		this.pageSizeLocal.set(Integer.valueOf(pageSize));
		List<Map<String, Object>> tagFromDb = getTagList(screeningId);
		
		TagConditionBase condition = TagGrpToCondition.dbMapToCondition(tagFromDb);

		BitSet userBit = getUserBitFromRedis(condition);
		if(null != userBit) {
			List<TUserInfo> userList = getUserDataFromHbase(userBit);
			logger.debug("person count value is :{},{}", Integer.valueOf(userList.size()), Integer.valueOf(getTotalNum(userBit)));
			int total = Integer.valueOf(getTotalNum(userBit));
			this.totalCntLocal.set(total);
			pageInfo.setTotal(total);
			return userList;
		}else {
			this.totalCntLocal.set(0);
			pageInfo.setTotal(0);
			return null;
		}
	}
	
	
	public Map<String, Object> queryDetailAll(String screeningId){
		List<Map<String, Object>> tagFromDb = getTagList(screeningId);
		TagConditionBase condition = TagGrpToCondition.dbMapToCondition(tagFromDb);

		BitSet userBit = getUserBitFromRedis(condition);
		List<Integer> userList = getAllUserDataFromHbase(userBit);
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("tags", tagFromDb);
		reMap.put("users", userList);
		return reMap;
	}
	
	public List<Integer> getAllUserDataFromHbase(BitSet userBit) {
		List<Integer> userList = getAllUserListThisTime(userBit);
		return userList;
		/*
		logger.debug("userList value is {}", userList.toString());
		List<TUserInfo> userInfoList = new ArrayList<TUserInfo>();
		try {
			Table userTable = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
			Throwable localThrowable3 = null;
			try {
				for (Integer user : userList) {
					TUserInfo userInfo = TUserInfo.findById(userTable, user.toString());
					if (userInfo != null) {
						userInfo.setUserGuid(user.toString());
						logger.debug(userInfo.toString());
						userInfoList.add(userInfo);
					}
				}
			} catch (Throwable localThrowable5) {
				localThrowable3 = localThrowable5;
				throw localThrowable5;
			} finally {
				if (userTable != null)
					if (localThrowable3 != null)
						try {
							userTable.close();
						} catch (Throwable localThrowable2) {
							localThrowable3.addSuppressed(localThrowable2);
						}
					else
						userTable.close();
			}
		} catch (IOException localIOException) {
		}
		return userInfoList;
		*/
	}
	
	public List<Integer> getAllUserListThisTime(BitSet userBit) {
		int startIndexPlace = 0;
		int forTime = 0;

		List<Integer> resultList = new ArrayList<Integer>();
		int allSize = Integer.valueOf(getTotalNum(userBit));
		while (true) {
			startIndexPlace = userBit.nextSetBit(startIndexPlace);
			if (startIndexPlace == -1)	break;
			resultList.add(Integer.valueOf(startIndexPlace));
			forTime++;
			startIndexPlace++;
			if (allSize == forTime) {
				break;
			}
		}
		return resultList;
	}
	
	private List<Map<String, Object>> getTagList(String screeningId){
		
		List<Map<String, Object>> mAll = new ArrayList<Map<String, Object>>();
		
		List<Map<String, Object>> ms1 = h62CampaignInfoDao.getScreeningTags(screeningId);
		List<Map<String, Object>> ms2 = h62CampaignInfoDao.getGroupScreeningTags(screeningId);
		
		if(ms1 != null) {
			mAll.addAll(ms1);
		}
		
		if(ms2 != null) {
			List<String> checkTags = new ArrayList<String>();
			for(Map<String, Object> m : ms2) {
				checkTags.add("" + m.get("tagId"));
				List<Map<String, Object>> queryList = h50TaggroupInfoService.queryTagsData(checkTags);
				if(null != queryList && queryList.size() > 0) {
					m.put("tagId", queryList.get(0).get("tag_id_group"));
				}
			}
			mAll.addAll(ms2);
		}
		return mAll;
	}
	
	public BitSet getUserBitFromRedis(TagConditionBase condition) {
		UsersDistributeByTag result = this.cacheService.getUsersDistributeByTag(condition, null);
		String keyOfUsers = result.getKeyOfUsers();
		logger.debug("get user bit from redis and the key of users is-->{}<---", keyOfUsers);
		byte[] userBitVal = this.jedisPool.getResource().get(keyOfUsers.getBytes());
		if(null != userBitVal) {
			BitSet userBit = CacheServiceRedisImpl.fromByteArrayReverse(userBitVal);
			logger.debug("from redis the user bit count is {}", Integer.valueOf(userBit.cardinality()));
			return userBit;
		}
		return null;
		
	}
	
	public TUserInfo queryTUserInfo(Integer user) {
		TUserInfo userInfo = null;
		try {
			if(null == userTable1) {
				userTable1 = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
			}
			userInfo = TUserInfo.findById(userTable1, user.toString());
		} catch (IOException e) {
			logger.debug("queryTUserInfo", user.toString());
			e.printStackTrace();
		}
		return userInfo;
	}
	
	public List<TUserInfo> getUserDataFromHbase(BitSet userBit) {
		List<Integer> userList = getUserListThisTime(userBit);
		logger.debug("userList value is {}", userList.toString());
		List<TUserInfo> userInfoList = new ArrayList<TUserInfo>();
		try {
			Table userTable = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
			Throwable localThrowable3 = null;
			try {
				for (Integer user : userList) {
					TUserInfo userInfo = TUserInfo.findById(userTable, user.toString());
					if (userInfo != null) {
						userInfo.setUserGuid(user.toString());
						logger.debug(userInfo.toString());
						userInfoList.add(userInfo);
					}
				}
			} catch (Throwable localThrowable5) {
				localThrowable3 = localThrowable5;
				throw localThrowable5;
			} finally {
				if (userTable != null)
					if (localThrowable3 != null)
						try {
							userTable.close();
						} catch (Throwable localThrowable2) {
							localThrowable3.addSuppressed(localThrowable2);
						}
					else
						userTable.close();
			}
		} catch (IOException localIOException) {
		}
		return userInfoList;
	}
	
	public List<Integer> getUserListThisTimeSms(BitSet userBit, int currPage, int pageSize) {
		if (pageSize == 0)
			pageSize = this.EACH_COUNT;
		int startPagePlace = (currPage - 1) * pageSize + 1;

		int startIndexPlace = 0;
		int checkTime = 0;
		int forTime = 0;

		List<Integer> resultList = new ArrayList<Integer>();
		while (true) {
			startIndexPlace = userBit.nextSetBit(startIndexPlace);
			if (startIndexPlace == -1)
				break;
			forTime++;
			if (forTime == startPagePlace) {
				resultList.add(Integer.valueOf(startIndexPlace));
				checkTime++;
			} else if (resultList.size() > 0) {
				resultList.add(Integer.valueOf(startIndexPlace));
				checkTime++;
			}

			startIndexPlace++;
			if (checkTime == pageSize) {
				break;
			}
		}
		return resultList;
	}
	
	private int getTotalNum(BitSet userBit) {
		int trueCount = userBit.cardinality();
		return trueCount;
	}
	
	public List<Integer> getUserListThisTime(BitSet userBit) {
		int currPage = ((Integer) this.pageNumLocal.get()).intValue();
		int pageSize = ((Integer) this.pageSizeLocal.get()).intValue();
		if (pageSize == 0)
			pageSize = this.EACH_COUNT;
		int startPagePlace = (currPage - 1) * pageSize + 1;

		int startIndexPlace = 0;
		int checkTime = 0;
		int forTime = 0;

		List<Integer> resultList = new ArrayList<Integer>();
		while (true) {
			startIndexPlace = userBit.nextSetBit(startIndexPlace);
			if (startIndexPlace == -1)
				break;
			forTime++;
			if (forTime == startPagePlace) {
				resultList.add(Integer.valueOf(startIndexPlace));
				checkTime++;
			} else if (resultList.size() > 0) {
				resultList.add(Integer.valueOf(startIndexPlace));
				checkTime++;
			}

			startIndexPlace++;
			if (checkTime == pageSize) {
				break;
			}
		}
		return resultList;
	}
	
	@SuppressWarnings("rawtypes")
	Comparator comparable = new Comparator(){
		
	     public int compare(Map<String, Object> o1, Map<String, Object> o2) {
	       Long userCount1 = (Long)o1.get("userCount");
	       Long userCount2 = (Long)o2.get("userCount");
	       return userCount2.compareTo(userCount1);
	     }

		@Override
		public int compare(Object o1, Object o2) {
			return 0;
		}   
	};
	
}
