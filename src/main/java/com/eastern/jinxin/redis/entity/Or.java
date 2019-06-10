 package com.eastern.jinxin.redis.entity;
 
 import com.fasterxml.jackson.annotation.JsonProperty;
 import java.util.ArrayList;
 import java.util.List;
 import javax.xml.bind.annotation.XmlElementRef;
 import javax.xml.bind.annotation.XmlRootElement;
 
 @XmlRootElement(name="or")
 public class Or extends TagConditionBase
 {
 
   @JsonProperty
   @XmlElementRef(type=TagConditionBase.class)
   protected List<TagCondition> conditions = new ArrayList();
 
   public <R extends TagQueryResult> R accept(TagConditionVisitor<R> visitor)
   {
     R result = visitor.visitTagCondition(this);
     return result;
   }
 
   public List<TagCondition> getConditions()
   {
     return this.conditions;
   }
 }
