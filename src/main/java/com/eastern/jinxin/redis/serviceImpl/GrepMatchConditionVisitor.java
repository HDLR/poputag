 package com.eastern.jinxin.redis.serviceImpl;
 
 import com.eastern.jinxin.redis.entity.TagCondition;
 import com.eastern.jinxin.redis.entity.TagConditionVisitor;
 import com.eastern.jinxin.redis.entity.And;
import com.eastern.jinxin.redis.entity.Match;
 import com.eastern.jinxin.redis.entity.Or;
 import java.util.List;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 public class GrepMatchConditionVisitor
   implements TagConditionVisitor<GrepMatchConditionResult>
 {
   private static final Logger logger = LoggerFactory.getLogger(GrepMatchConditionVisitor.class);
 
   GrepMatchConditionResult allMatchTagIds = new GrepMatchConditionResult();
 
   public GrepMatchConditionResult visitTagCondition(TagCondition op)
   {
     if ((op instanceof Match))
     {
       Match opMatch = (Match)op;
       this.allMatchTagIds.add(opMatch.getTagId());
     } else if ((op instanceof And))
     {
       List<TagCondition> children = ((And)op).getConditions();
       for (TagCondition child : children)
         visitTagCondition(child);
     }
     else if ((op instanceof Or))
     {
       List<TagCondition> children = ((Or)op).getConditions();
       for (TagCondition child : children)
         visitTagCondition(child);
     }
     else {
       logger.error("Error, GrepMatchConditionResult:  the TagCondition is not handled: {} ", op.getClass());
     }
     return this.allMatchTagIds;
   }
 }
