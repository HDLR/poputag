package com.eastern.jinxin.sys.common.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.eastern.jinxin.sys.common.utils.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * XSS过滤
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2017-04-01 10:20
 */
public class XssFilter implements Filter {

	private String excludedPages;       
	private String[] excludedPageArray;
	private String ctxPath;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {     
		excludedPages = fConfig.getInitParameter("excludedPages");     
		if (StringUtils.isNotBlank(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}
		ctxPath = fConfig.getInitParameter("ctxPath");
		return;     
	}     

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		httpServletRequest.setAttribute("systemTime", new Date());
		String ctxPathLocal = httpServletRequest.getScheme() + "://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort();
		httpServletRequest.setAttribute("ctxPath", ctxPathLocal);
		
		boolean isExcludedPage = false;
		if(null != excludedPageArray) {
			for (String page : excludedPageArray) {
				//判断是否在过滤url之外     
				String uri = httpServletRequest.getRequestURI();
				if(uri.contains(page)){     
					isExcludedPage = true;     
					break;     
				}     
			}
		}
		
		if (isExcludedPage) {//在过滤url之外     
			chain.doFilter(httpServletRequest, response);     
		}else {
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(httpServletRequest);
			chain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void destroy() {
	}

}