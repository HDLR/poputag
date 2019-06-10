package com.eastern.jinxin.redis.serviceImpl;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eastern.jinxin.sys.common.utils.ObjectJsonUtils;
import com.eastern.jinxin.redis.entity.TagCondition;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
import com.eastern.jinxin.redis.serviceImpl.TagQueryResultCacheImpl;
import com.eastern.jinxin.redis.serviceImpl.UsersDistributeByTagResultImpl;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.redis.utils.TagGrpToCondition;
import com.eastern.jinxin.redis.utils.UserTagUtils;
import com.eastern.jinxin.redis.service.CacheService;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

@Service
public class CacheServiceRedisImpl implements CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheServiceRedisImpl.class);

	@Autowired
	private JedisPool jedisPool;
	private AtomicLong count_getNumOfUsersByCondition = new AtomicLong(0L);
	private AtomicLong count_getUsersDistributeByTag = new AtomicLong(0L);

	public static boolean ENABLE_GLOBAL_FILTER = false;

	@PostConstruct
	public void init() {
		Jedis jedis = this.jedisPool.getResource();
		Throwable localThrowable3 = null;
		try {
			Set<String> andKeys = jedis.keys("AND*");
			Set<String> orKeys = jedis.keys("OR*");
			Pipeline pipeline = jedis.pipelined();

			pipeline.multi();
			for (String key : andKeys) {
				pipeline.del(key);
			}

			for (String key : orKeys) {
				pipeline.del(key);
			}

			pipeline.exec();

			pipeline.sync();
		} catch (Throwable localThrowable1) {
			localThrowable3 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (jedis != null)
				if (localThrowable3 != null)
					try {
						jedis.close();
					} catch (Throwable localThrowable2) {
						localThrowable3.addSuppressed(localThrowable2);
					}
				else
					jedis.close();
		}
	}

	public static void safeExpireRedisKey(Jedis jedis, String key) {
		if (null == key) {
			return;
		}

		if ((key.startsWith(RedisUtils.KEY)) || (key.equals("n-2-guid")) || (key.equals("guid-2-n")) || (key.equals("g:excludeUsers"))
				|| (key.startsWith("camp_bits:"))) {
			jedis.persist(key);
		} else
			jedis.expire(key, 180);
	}
	
	public static void safeExpireRedisKeyGroup(Jedis jedis, String key) {
		if (null == key) {
			return;
		}

		//组合标签只是暂时的，设置redis缓存中存在的时间
		jedis.expire(key, 180);
			
	}

	public static BitSet fromByteArrayReverse(byte[] bytes) {
		BitSet bits = new BitSet();
		//long j=0;
		for (int i = 0; i < bytes.length * 8; i++) {
			if ((bytes[(i / 8)] & 1 << 7 - i % 8) != 0) {
				bits.set(i);
			}
		}
		return bits;
	}

	public static TagQueryResultCacheImpl controllableQueryResultFromTagCondition(TagCondition condition, Jedis jedis,
			@Nullable Collection<String> excludeUserInCamps) {
		TagOpWithRedisVisitor visitor = new TagOpWithRedisVisitor(jedis);
		TagQueryResultCacheImpl result = (TagQueryResultCacheImpl) condition.accept(visitor);

		if ((null != excludeUserInCamps) && (!excludeUserInCamps.isEmpty())) {
			String[] campsKey = new String[excludeUserInCamps.size()];
			Iterator<String> ite = excludeUserInCamps.iterator();
			int i = 0;
			while (ite.hasNext()) {
				campsKey[(i++)] = UserTagUtils.getCampBitSetRedisKey((String) ite.next());
			}

			Pipeline pipeline = jedis.pipelined();
			String f3ExcludesKey = "f3-or:" + campsKey.toString();
			pipeline.bitop(BitOP.OR, f3ExcludesKey, campsKey);

			String f1Key = "f1-xor:" + result.getKey();
			pipeline.bitop(BitOP.XOR, f1Key, new String[] { f3ExcludesKey, result.getKey() });

			String f2Key = "f2-and:" + f1Key;
			pipeline.bitop(BitOP.AND, f2Key, new String[] { f1Key, result.getKey() });

			pipeline.del(f3ExcludesKey);
			pipeline.del(f1Key);

			pipeline.sync();

			result.setKey(f2Key);

			safeExpireRedisKey(jedis, f2Key);
		}

		if ((ENABLE_GLOBAL_FILTER) && (jedis.exists("g:excludeUsers").booleanValue())) {
			Pipeline pipeline = jedis.pipelined();
			String f1Key = "f1-xor:" + result.getKey();
			pipeline.bitop(BitOP.XOR, f1Key, new String[] { "g:excludeUsers", result.getKey() });

			String f2Key = "f2-and:" + f1Key;
			pipeline.bitop(BitOP.AND, f2Key, new String[] { f1Key, result.getKey() });

			pipeline.del(f1Key);

			pipeline.sync();

			result.setKey(f2Key);

			safeExpireRedisKey(jedis, f2Key);
		}

		return result;
	}

	public long getNumOfUsersByCondition(TagCondition condition, @Nullable Collection<String> excludeUserInCamps) {
		this.count_getNumOfUsersByCondition.getAndIncrement();
		try {
			Jedis jedis = this.jedisPool.getResource();
			Throwable localThrowable4 = null;
			try {
				TagQueryResultCacheImpl result = controllableQueryResultFromTagCondition(condition, jedis, excludeUserInCamps);
				String retKey = result.getKey();
				Long nums = jedis.bitcount(retKey);

				long l = nums == null ? 0L : nums.longValue();
				return l;
			} catch (Throwable localThrowable2) {
				localThrowable4 = localThrowable2;
				throw localThrowable2;
			} finally {
				if (jedis != null)
					if (localThrowable4 != null)
						try {
							jedis.close();
						} catch (Throwable localThrowable3) {
							localThrowable4.addSuppressed(localThrowable3);
						}
					else
						jedis.close();
			}
		} catch (Throwable e) {
			logger.error("getNumOfUsersByCondition() exception:", e);
		}

		return 0L;
	}
	
	public void countLabelByte(BitOP bitOP, String resultKey, String[] srcKeyArray) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.bitop(bitOP, resultKey, srcKeyArray);
		//组合标签只是暂时的，设置redis缓存中存在的时间
		safeExpireRedisKeyGroup(jedis, resultKey);
	}
	
	public Long queryCountByKey(String key) {
		Jedis jedis = this.jedisPool.getResource();
		Long nums = jedis.bitcount(key);
		return nums;
	}

	public UsersDistributeByTag getUsersDistributeByTag(TagCondition condition, @Nullable Collection<String> excludeUserInCamps) {
		this.count_getUsersDistributeByTag.getAndIncrement();
		try {
			Jedis jedis = this.jedisPool.getResource();
			Throwable localThrowable4 = null;
			try {
				TagQueryResultCacheImpl result = controllableQueryResultFromTagCondition(condition, jedis, excludeUserInCamps);
				String retKey = result.getKey();
				logger.debug("retKey value is {}", retKey);

				Long nums = jedis.bitcount(retKey);

				UsersDistributeByTagResultImpl distResult = new UsersDistributeByTagResultImpl();
				distResult.setTotalNumOfUsers(nums.longValue());
				distResult.setKeyOfUsers(retKey);

				GrepMatchConditionVisitor grepVisitor = new GrepMatchConditionVisitor();
				GrepMatchConditionResult grepResult = (GrepMatchConditionResult) condition.accept(grepVisitor);

				for (Object localObject1 = grepResult.iterator(); ((Iterator) localObject1).hasNext();) {
					String tagId = (String) ((Iterator) localObject1).next();
					String tagKey = UserTagUtils.getCacheKeyByTagId(tagId);
					String retKeyByTag = "calc_DIST(" + tagKey + ", " + retKey + ")";

					Pipeline pipeline = jedis.pipelined();
					pipeline.multi();
					pipeline.bitop(BitOP.AND, retKeyByTag, new String[] { retKey, UserTagUtils.getCacheKeyByTagId(tagId) });
					Response numOfUserForTag = pipeline.bitcount(retKeyByTag);
					pipeline.del(retKeyByTag);
					pipeline.exec();
					pipeline.sync();

					distResult.addIntoTagToUserCountMap(tagId, ((Long) numOfUserForTag.get()).longValue());
				}

				logger.debug("getTagToUserCountMap value is " + distResult.getTagToUserCountMap());
				// localObject1 = distResult;
				return distResult;
			} catch (Throwable localThrowable2) {
				localThrowable4 = localThrowable2;
				throw localThrowable2;
			} finally {
				if (jedis != null)
					if (localThrowable4 != null)
						try {
							jedis.close();
						} catch (Throwable localThrowable3) {
							localThrowable4.addSuppressed(localThrowable3);
						}
					else
						jedis.close();
			}
		} catch (Throwable e) {
			logger.error("getUsersDistributeByTag() exception:", e);
		}

		return (UsersDistributeByTag) new UsersDistributeByTagResultImpl();
	}
	
	//查询符合条件的人员数量
	public long sumPersonCount(Map<String, Object> pramMap) {
		if(null != pramMap) {
			TagConditionBase resultCondition = TagGrpToCondition.jsonMapToCondition(pramMap);
		     UsersDistributeByTag result = this.getUsersDistributeByTag(resultCondition, null);
		     long userCont = result.getTotalNumOfUsers();
		     logger.debug("\n" + ObjectJsonUtils.objectToJson(pramMap) + "----->" + userCont);
		     return userCont;
		}
		return 0;
	}
}
