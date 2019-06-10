package com.eastern.jinxin.business.recommend.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignInfoDao;
import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.hbase.service.impl.HBaseConnectionMgr;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.utils.TagGrpToCondition;
import redis.clients.jedis.JedisPool;

@Service("policyPushSmsService")
public class PolicyPushSmsServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(PolicyPushSmsServiceImpl.class);
	@Autowired
	private H62CampaignInfoDao h62CampaignInfoDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private HBaseConnectionMgr connectionMgr;
	private int EACH_COUNT = 10;

	public BitSet getUserBitFromRedisSms(String screeningId) {
		List<Map<String, Object>> tagFromDb = getTagListForSms(screeningId);
		TagConditionBase condition = TagGrpToCondition.dbMapToCondition(tagFromDb);
		BitSet userBit = getUserBitFromRedis(condition);
		return userBit;
	}
	
	private List<Map<String, Object>> getTagListForSms(String screeningId) {
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String, Object>> tagResults = h62CampaignInfoDao.getScreeningTags(screeningId);
		List<Map<String, Object>> tagResultsSms = h62CampaignInfoDao.getScreeningTagsSms();
		Set<String> set = new HashSet<String>();
		for (Map<String, Object> map : tagResults) {
			set.add(map.get("screeningId") + "&" + map.get("grpSeq"));
		}
		for (String str : set) {
			String[] smsArr = str.split("&");
			Map<String, Object> mapSms = tagResultsSms.get(0);
			mapSms.put("screeningId", smsArr[0]);
			mapSms.put("grpSeq", smsArr[1]);
		}
		tagResults.addAll(tagResultsSms);
		return tagResults;
	}
	
	public BitSet getUserBitFromRedis(TagConditionBase condition) {
		UsersDistributeByTag result = cacheService.getUsersDistributeByTag(condition, null);
		String keyOfUsers = result.getKeyOfUsers();
		logger.debug("get user bit from redis and the key of users is-->{}<---", keyOfUsers);
		byte[] userBitVal = this.jedisPool.getResource().get(keyOfUsers.getBytes());
		BitSet userBit = CacheServiceRedisImpl.fromByteArrayReverse(userBitVal);
		logger.debug("from redis the user bit count is {}", Integer.valueOf(userBit.cardinality()));
		return userBit;
	}
	
	/**
	 * 获取短信发送的用户信息
	 * 
	 * @param userBit
	 * @return
	 */
	public List<TUserInfo> getUserDataFromHbaseSms(BitSet userBit, int currPage, int pageSize) {
		List<Integer> userList = getUserListThisTimeSms(userBit, currPage, pageSize);
		logger.debug("userList value is {}", userList.toString());
		List<TUserInfo> userInfoList = new ArrayList<TUserInfo>();
		try {
			Table userTable = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
			Throwable localThrowable3 = null;
			try {
				for (Integer user : userList) {
					TUserInfo userInfo = TUserInfo.findByIdSms(userTable, user.toString());
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
}
