package com.eastern.jinxin.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
	
	@Value("${spring.redis.host}")
	private String hostIp;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;

	@Bean
    public JedisPool jedisPool() {
		
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(2000);
        config.setMaxIdle(10000);
        config.setMaxWaitMillis(5000);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setMinEvictableIdleTimeMillis(30000);
        config.setTestOnBorrow(true);
        
        return new JedisPool(config, hostIp, port, 0, password);
    }
}
