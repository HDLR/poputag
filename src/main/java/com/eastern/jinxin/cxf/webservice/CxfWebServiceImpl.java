package com.eastern.jinxin.cxf.webservice;

import javax.jws.WebService;
import org.springframework.stereotype.Component;
 
@WebService(
		serviceName="cxfWebService",//【对外发布的服务名 】：需要见名知意
		targetNamespace="http://webservice.cxf.jinxin.eastern.com",//【名称空间】：【实现类包名的反缀】
		endpointInterface = "com.eastern.jinxin.cxf.webservice.CxfWebService")//【服务接口全路径】  【接口的包名】
public class CxfWebServiceImpl implements CxfWebService {
 
	@Override
	public String sayHello(String name) {
		System.out.println("=======================>"+name);
		return "hello ： " + name;
	}
}
 
