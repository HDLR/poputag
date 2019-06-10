package com.eastern.jinxin.cxf.configuration;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eastern.jinxin.cxf.webservice.CxfWebService;
import com.eastern.jinxin.cxf.webservice.CxfWebServiceImpl;

import javax.xml.ws.Endpoint;
 
@Configuration//注册到spring启动中
public class CxfConfig {
 
	@Bean
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/cxf/*");// 发布服务名称 localhost:8080/cxf
	}
 
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	
	@Bean
	public CxfWebService cxfWebService() {
		return new CxfWebServiceImpl();
	}
	
	@Bean
	public Endpoint hello() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), cxfWebService());// 绑定要发布的服务实现类
		endpoint.publish("/hello"); // 接口访问地址
		return endpoint;
	}
}

