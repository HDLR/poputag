package com.eastern.jinxin.cxf.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

//上面package名反过来写
@WebService(targetNamespace="http://webservice.cxf.jinxin.eastern.com")
public interface CxfWebService {
	@WebMethod
	public @WebResult String sayHello(@WebParam(name = "userName") String userName);
}
