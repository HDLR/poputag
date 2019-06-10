package com.eastern.jinxin.redis.entity;

import java.util.Map;

public abstract interface UsersDistributeByTag
{
  public abstract String getKeyOfUsers();

  public abstract long getTotalNumOfUsers();

  public abstract Map<String, Long> getTagToUserCountMap();
}
