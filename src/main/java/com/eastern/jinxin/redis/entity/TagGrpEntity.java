 package com.eastern.jinxin.redis.entity;
 
 import java.util.List;
 
 public class TagGrpEntity
 {
   private String grpSeq;
   private List<TagCtgyEntity> tagCtgys;
 
   public String getGrpSeq()
   {
     return this.grpSeq;
   }
 
   public void setGrpSeq(String grpSeq) {
     this.grpSeq = grpSeq;
   }
 
   public List<TagCtgyEntity> getTagCtgys() {
     return this.tagCtgys;
   }
 
   public void setTagCtgys(List<TagCtgyEntity> tagCtgys) {
     this.tagCtgys = tagCtgys;
   }
 }

/* Location:           C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\
 * Qualified Name:     com.seabox.tagsys.persongroup.entity.TagGrpEntity
 * JD-Core Version:    0.6.0
 */