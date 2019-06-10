 package com.eastern.jinxin.redis.serviceImpl;
 
 import com.eastern.jinxin.redis.entity.TagCondition;
 import com.eastern.jinxin.redis.entity.TagConditionVisitor;
 import com.eastern.jinxin.redis.entity.And;
 import com.eastern.jinxin.redis.entity.Match;
 import com.eastern.jinxin.redis.entity.Or;
 import com.eastern.jinxin.redis.utils.UserTagUtils;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.List;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import redis.clients.jedis.BitOP;
 import redis.clients.jedis.Jedis;
 
 public class TagOpWithRedisVisitor
   implements TagConditionVisitor<TagQueryResultCacheImpl>
 {
   private static final Logger logger = LoggerFactory.getLogger(TagOpWithRedisVisitor.class);
   private Jedis jedis;
 
   public TagOpWithRedisVisitor(Jedis jedis)
   {
     this.jedis = jedis;
   }
 
   public TagQueryResultCacheImpl visitTagCondition(TagCondition op)
   {
     if ((op instanceof Match))
     {
       Match opMatch = (Match)op;
       String key = UserTagUtils.getCacheKeyByTagId(opMatch.getTagId());
       TagQueryResultCacheImpl result = createResultObject(key);
       return result;
     }if ((op instanceof And))
     {
       List children = ((And)op).getConditions();
       TagQueryResultCacheImpl result = computeBitOperationWithStore(BitOP.AND, children);
       return result;
     }if ((op instanceof Or))
     {
       List children = ((Or)op).getConditions();
       TagQueryResultCacheImpl result = computeBitOperationWithStore(BitOP.OR, children);
       return result;
     }
     logger.error("Error, TagOpWithRedisVisitor:  the TagCondition is not handled: {} ", op.getClass());
     return null;
   }
 
   protected TagQueryResultCacheImpl computeBitOperationWithStore(BitOP bitOP, Collection<? extends TagCondition> childrenConditions)
   {
     List srcKeys = new ArrayList();
     StringBuffer stringBuffer = new StringBuffer(bitOP.toString());
 
     for (TagCondition child : childrenConditions) {
       TagQueryResultCacheImpl r1 = visitTagCondition(child);
       srcKeys.add(r1.getKey());
     }
     stringBuffer.append(srcKeys);
 
     String resultKey = stringBuffer.toString();
     String[] srcKeyArray = new String[srcKeys.size()];
     srcKeys.toArray(srcKeyArray);
 
     cacheBitOperationWithStore(bitOP, resultKey, srcKeyArray);
 
     TagQueryResultCacheImpl result = createResultObject(resultKey);
 
     return result;
   }
 
   protected TagQueryResultCacheImpl createResultObject(String key) {
     TagQueryResultCacheImpl result = new TagQueryResultCacheImpl();
     result.setKey(key);
     return result;
   }
 
   protected void cacheBitOperationWithStore(BitOP bitOP, String resultKey, String[] srcKeyArray) {
     this.jedis.bitop(bitOP, resultKey, srcKeyArray);
 
     CacheServiceRedisImpl.safeExpireRedisKey(this.jedis, resultKey);
 
     logger.debug("perform Redis Operation, bitOP:{},  stored as new key: {}", bitOP, resultKey);
   }
 }
