 package com.eastern.jinxin.hbase.service.impl;
 
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.Map;
import com.eastern.jinxin.redis.entity.TagQueryResult;
import com.eastern.jinxin.redis.entity.UsersDistributeByTag;
 
 public class UsersDistributeByTagResultImpl
   implements UsersDistributeByTag, TagQueryResult
 {
   String keyOfUsers;
   long totalNumOfUsers;
   Map<String, Long> numOfUsersByTag = new HashMap();
 
   public String getKeyOfUsers()
   {
     return this.keyOfUsers;
   }
 
   public long getTotalNumOfUsers()
   {
     return this.totalNumOfUsers;
   }
 
   public Map<String, Long> getTagToUserCountMap()
   {
     return Collections.unmodifiableMap(this.numOfUsersByTag);
   }
 
   public void setTotalNumOfUsers(long totalNumOfUsers) {
     this.totalNumOfUsers = totalNumOfUsers;
   }
 
   public void addIntoTagToUserCountMap(String tagId, long numOfUsers) {
     this.numOfUsersByTag.put(tagId, Long.valueOf(numOfUsers));
   }
 
   public void setKeyOfUsers(String keyOfUsers) {
     this.keyOfUsers = keyOfUsers;
   }
 }
