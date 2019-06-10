package com.eastern.jinxin.sys.operation.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	@RequestMapping("{url}.html")
	public String page(@PathVariable("url") String url){
		return url + ".html";
	}
	
	@RequestMapping("{module}/{url}.html")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url){
		return module + "/" + url + ".html";
	}
	
	@RequestMapping("{parents}/{module}/{url}.html")
	public String page(@PathVariable("parents") String parents,@PathVariable("module") String module, @PathVariable("url") String url){
		return parents+"/"+module + "/" + url + ".html";
	}
	
	@RequestMapping("{parents}/{module}/{type}/{url}.html")
	public String page(@PathVariable("parents") String parents,@PathVariable("module") String module, @PathVariable("type") String type, @PathVariable("url") String url){
		return parents+"/"+module + "/" + type + "/" + url + ".html";
	}
	
	@RequestMapping("toNewView/{url}")
	public String toNewView(HttpServletRequest request, @PathVariable("url") String url, String params) {
		request.setAttribute("params", params);
		return url + ".html";
	}
	
	@RequestMapping("toNewView/{module}/{url}")
	public String toNewView(HttpServletRequest request, @PathVariable("module") String module, @PathVariable("url") String url, String viewParams) {
		request.setAttribute("viewParams", viewParams);
		return module + "/" + url + ".html";
	}
	
	@RequestMapping("toNewView/{parents}/{module}/{url}")
	public String parentsToNewViewYear(HttpServletRequest request, String yearNeed, @PathVariable("parents") String parents, @PathVariable("module") String module, @PathVariable("url") String url, String viewParams) {
		request.setAttribute("viewParams", viewParams);
		request.setAttribute("yearNeed", yearNeed);
		return parents + "/" + module + "/" + url + ".html";
	}
	
	@RequestMapping("toNewView/{parents}/{module}/{type}/{url}")
	public String parentsToNewView(HttpServletRequest request, @PathVariable("parents") String parents, @PathVariable("module") String module, @PathVariable("type") String type, @PathVariable("url") String url, String viewParams) {
		request.setAttribute("viewParams", viewParams);
		return parents + "/" + module + "/" + type + "/" + url + ".html";
	}
}
