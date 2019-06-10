 package com.eastern.jinxin.redis.entity;
 
 import java.util.List;
 
 public class TagCtgyEntity
 {
   private String tagCtgyId;
   private List<String> tagIds;
 
   public String getTagCtgyId()
   {
     return this.tagCtgyId;
   }
 
   public void setTagCtgyId(String tagCtgyId) {
     this.tagCtgyId = tagCtgyId;
   }
 
   public List<String> getTagIds() {
     return this.tagIds;
   }
 
   public void setTagIds(List<String> tagIds) {
     this.tagIds = tagIds;
   }
 }

/* Location:           C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\
 * Qualified Name:     com.seabox.tagsys.persongroup.entity.TagCtgyEntity
 * JD-Core Version:    0.6.0
 */