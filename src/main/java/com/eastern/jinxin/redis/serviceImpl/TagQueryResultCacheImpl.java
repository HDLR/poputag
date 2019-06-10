 package com.eastern.jinxin.redis.serviceImpl;
 
import com.eastern.jinxin.redis.entity.TagQueryResult;
import com.eastern.jinxin.redis.service.CacheKeyStore;
 
 public class TagQueryResultCacheImpl
   implements TagQueryResult, CacheKeyStore
 {
   private String key;
 
   public String getKey()
   {
     return this.key;
   }
 
   public void setKey(String key) {
     this.key = key;
   }
 }
