 package com.eastern.jinxin.redis.entity;


 import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "match")
 public class Match extends TagConditionBase
 {
	/*    */
	/*    */ @JsonProperty
	/*    */ private String tag;
	/*    */
	/*    */ @JsonProperty
	/*    */ private String tagId;

	/*    */
	/*    */ public Match(String tagId, String tag)
	/*    */ {
		/* 24 */ this.tagId = tagId;
		/* 25 */ this.tag = tag;
		/*    */ }

	/*    */
	/*    */ public Match()
	/*    */ {
		/*    */ }

	/*    */
	/*    */ public String getTag() {
		/* 33 */ return this.tag;
		/*    */ }

	/*    */
	/*    */ public void setTag(String tag) {
		/* 37 */ this.tag = tag;
		/*    */ }

	/*    */
	/*    */ public String getTagId() {
		/* 41 */ return this.tagId;
		/*    */ }

	/*    */
	/*    */ public void setTagId(String tagId) {
		/* 45 */ this.tagId = tagId;
		/*    */ }


	@Override
	public <R extends TagQueryResult> R accept(TagConditionVisitor<R> visitor) {
		R result = visitor.visitTagCondition(this);
		return result;
	}

	/*    */
	/*    */ public String toString()
	/*    */ {
		/* 56 */ return "Match tag:" + this.tag + " , tagId:" + this.tagId;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\ Qualified Name:
 * com.seabox.tagsys.usertags.logicquery.impl.Match JD-Core Version: 0.6.0
 */