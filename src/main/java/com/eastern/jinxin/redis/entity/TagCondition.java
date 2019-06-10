package com.eastern.jinxin.redis.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;

@JsonTypeInfo(use= Id.NAME, include= As.PROPERTY)
@JsonSubTypes({@JsonSubTypes.Type(value=And.class, name="And"), @JsonSubTypes.Type(value=Or.class, name="Or"), @JsonSubTypes.Type(value=Match.class, name="Match")})
public abstract interface TagCondition extends Serializable
{
  public abstract <R extends TagQueryResult> R accept(TagConditionVisitor<R> paramTagConditionVisitor);
}
