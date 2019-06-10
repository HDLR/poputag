package com.eastern.jinxin.redis.entity;

public abstract interface TagConditionVisitor<R extends TagQueryResult>
{
  public abstract R visitTagCondition(TagCondition paramTagCondition);
}
