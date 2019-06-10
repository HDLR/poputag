package com.eastern.jinxin.redis.service;

public abstract interface CacheKeyStore
{
  public abstract String getKey();

  public abstract void setKey(String paramString);
}
