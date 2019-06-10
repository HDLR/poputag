 package com.eastern.jinxin.hbase.entity;
 
 import java.util.BitSet;
 
 public class TagHelperBean
 {
   private String tagId;
   private String tagNm;
   private Long userCount;
   private BitSet userBit;
   private byte[] userByteArray;
 
   public String getTagId()
   {
     return this.tagId;
   }
 
   public void setTagId(String tagId) {
     this.tagId = tagId;
   }
 
   public Long getUserCount() {
     return this.userCount;
   }
 
   public void setUserCount(Long userCount) {
     this.userCount = userCount;
   }
 
   public BitSet getUserBit() {
     return this.userBit;
   }
 
   public void setUserBit(BitSet userBit) {
     this.userBit = userBit;
   }
 
   public byte[] getUserByteArray() {
     return this.userByteArray;
   }
 
   public void setUserByteArray(byte[] userByteArray) {
     this.userByteArray = userByteArray;
   }
 
   public String getTagNm() {
     return this.tagNm;
   }
 
   public void setTagNm(String tagNm) {
     this.tagNm = tagNm;
   }
 }

/* Location:           C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\
 * Qualified Name:     com.seabox.tagsys.persongroup.utils.TagHelperBean
 * JD-Core Version:    0.6.0
 */