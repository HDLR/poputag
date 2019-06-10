package com.eastern.jinxin.redis.utils;

import redis.clients.jedis.JedisPool;

public class RedisFac {

	private static JedisPool jedisPool;
	
	public static JedisPool getJedisPool() {
		return jedisPool;
	}
}
