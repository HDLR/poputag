package com.eastern.jinxin.redis.service;

import com.eastern.jinxin.redis.entity.TagCondition;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
import redis.clients.jedis.BitOP;

import java.util.Collection;
import java.util.Map;

import com.sun.istack.internal.Nullable;

public abstract interface CacheService
{
	
  public Long queryCountByKey(String key);
	
  public void countLabelByte(BitOP bitOP, String resultKey, String[] srcKeyArray);
	
  public abstract long getNumOfUsersByCondition(TagCondition paramTagCondition, @Nullable Collection<String> paramCollection);

  public abstract UsersDistributeByTag getUsersDistributeByTag(TagCondition paramTagCondition, @Nullable Collection<String> paramCollection);
  
  public long sumPersonCount(Map<String, Object> pramMap);
}
