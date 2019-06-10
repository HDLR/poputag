package com.eastern.jinxin.solr.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eastern.jinxin.solr.model.DataInfo;
import com.eastern.jinxin.solr.service.SolrDataService;
import com.eastern.jinxin.solr.tools.IsPtInPoly;
import com.eastern.jinxin.solr.tools.Point2D;

@Service
public class SolrDataServiceImpl implements SolrDataService{
	
	@Resource(name = "httpSolrClientPopu")
	private HttpSolrClient httpSolrClientPopu;
	
	@Resource(name = "httpSolrClientLegal")
	private HttpSolrClient httpSolrClientLegal;
	
	public void querySolrDocuments() throws Exception {
		//创建查询条件  
		SolrQuery solrParams = new SolrQuery();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	Date startData = new Date();
    	System.out.println("开始时间：" + df.format(startData));// new Date()为获取当前系统时间
    	
    	int sum = 0;
    	int sumCount = 10000;
    	
    	// 测试一个点是否在多边形内  
	    List<Point2D> pts = new ArrayList<Point2D>();  
	    pts.add(new Point2D(116.395, 39.910));  
	    pts.add(new Point2D(116.394, 39.914));  
	    pts.add(new Point2D(116.403, 39.920));  
	    pts.add(new Point2D(116.402, 39.914));  
	    pts.add(new Point2D(116.410, 39.913));  
		
		for(int page=0; sum<sumCount; page++) {
			int from = page;
			int size = 10000;
			
			//明确类型查询
			String query="popu_13_unif_popu_id:[0000 TO 20000351]";
			
			solrParams.setStart(from);
			solrParams.setRows(size);
			solrParams.setQuery(query);
	        //查询并返回结果  
			
	        QueryResponse queryResponse = httpSolrClientPopu.query(solrParams);
	        sumCount = (int)queryResponse.getResults().getNumFound();
	        List<DataInfo> popuList = queryResponse.getBeans(DataInfo.class);
	        for(DataInfo d : popuList) {
	        	Point2D point = new Point2D(116.404072, 39.916605);  
	        	//System.out.println("---" + d.getId() + "---" + IsPtInPoly.isPtInPoly(point, pts));
	        	
	        }
	        sum = sum + popuList.size();
	        System.out.println("总数：" + sumCount + "，已完成：" + sum + "，用时：" + (new Date().getTime() - startData.getTime())/1000 + "秒");
		}
		System.out.println("完成时间：" + df.format(new Date()));
		
//        pageInfo.setRows(popuList);
//	    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
	}
	
	public void querySolrDocumentsLegal() throws Exception {
		//创建查询条件  
		SolrQuery solrParams = new SolrQuery();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	Date startData = new Date();
    	System.out.println("开始时间：" + df.format(startData));// new Date()为获取当前系统时间
    	
    	int sum = 0;
    	int sumCount = 10000;
    	
		
		for(int page=0; sum<sumCount; page++) {
			int from = page;
			int size = 10000;
			
			//明确类型查询
			String query="legal_17_lpr_cert_num:*";
			
			solrParams.setStart(from);
			solrParams.setRows(size);
			solrParams.setQuery(query);
	        //查询并返回结果  
			
	        QueryResponse queryResponse = httpSolrClientLegal.query(solrParams);
			SolrDocumentList docomentList = queryResponse.getResults();
			if(null != docomentList && docomentList.getNumFound() > 0) {
		        for(SolrDocument d : docomentList) {
		        	System.out.println( d.get("legal_17_lpr_id")  + "value" + d.get("legal_17_uscc"));
		        }
			}
	        System.out.println("总数：" + sumCount + "，已完成：" + sum + "，用时：" + (new Date().getTime() - startData.getTime())/1000 + "秒");
		}
		System.out.println("完成时间：" + df.format(new Date()));
		
//        pageInfo.setRows(popuList);
//	    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
	}
}
