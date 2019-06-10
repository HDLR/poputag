package com.eastern.jinxin.sys.configuration.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ThymeleafConf {

	@Bean
	public ShiroDialect shiroDialect() {
	   return new ShiroDialect();
	}
}
