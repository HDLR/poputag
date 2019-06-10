package com.eastern.jinxin.solr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.solr.conf.SolrConf;
import com.eastern.jinxin.solr.model.PopuInfo;
import com.eastern.jinxin.solr.model.PopuPoints;
import com.eastern.jinxin.solr.service.PopuSolrDataService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

@Service
public class PopuSolrDataServiceImpl implements PopuSolrDataService{

	/*@Autowired
	private HttpSolrClient httpSolrClient;*/
	
	@Resource(name = "httpSolrClientPopu")
	private HttpSolrClient httpSolrClientPopu;
	
	//private SolrClient client = SolrUtils.createHttpSolrClient();
	
	public void querySolrDocuments(PageInfo pageInfo) throws Exception {
		//创建查询条件  
		SolrQuery solrParams = new SolrQuery();  
		Map<String, Object> m = pageInfo.getCondition();
		String queryValue = (String)m.get("queryValue");
		
		int from = pageInfo.getFrom();
		int size = pageInfo.getSize();
		
		//明确类型查询
		String type = (String)pageInfo.getCondition().get("type");
		String query="";
		if(StringUtils.isBlank(queryValue)){
			 query="popu_13_cert_num:*";
		}else{
			queryValue=queryValue.replaceAll(" +"," ");//去掉空格
			String[] arr=queryValue.split(" ");	
			
			if(StringUtils.isNotBlank(type)){
				query=type+":"+arr[0];
			}else{
				for(String v : arr){
				query += this.pd(query, v);
				}
			}
		}
		solrParams.setStart(from);
		solrParams.setRows(size);
		solrParams.setQuery(query);
        //查询并返回结果  
        QueryResponse queryResponse = this.httpSolrClientPopu.query(solrParams);
        List<PopuInfo> popuList = queryResponse.getBeans(PopuInfo.class);
        pageInfo.setRows(popuList);
	    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
	}
	
	private String pd(String query, String value){
		String regex = "^[0-9A-Za-z]{1,30}$";
		if(value.matches(regex)){
			if(StringUtils.isEmpty(query)){
				query = "all_number_13:" + value+"*";
			}else{
				query = " AND " +"all_number_13:" + value+"*";
			}
		}else{
			if(StringUtils.isEmpty(query)){
				query = "all_content_13:" + value;
			}else{
				query = " AND " +"all_content_13:" + value;
			}
		}
		return query;
	}
	
	public PopuInfo querySolrDocumentById(String name, String value) throws Exception{
		SolrQuery query = new SolrQuery();
		query.setQuery(name + ":" + value);
		QueryResponse queryResponse = this.httpSolrClientPopu.query(query);
		List<PopuInfo> popuList = queryResponse.getBeans(PopuInfo.class);
		if(null != popuList && popuList.size() > 0) {
			for(PopuInfo info : popuList) {
				info.createMap();
			}
			return popuList.get(0);
		}
		return null;
	}
	
	//查询证件号
	public String querySolrByCardId(String cardId) throws Exception{
		SolrQuery query = new SolrQuery();
		query.setQuery("popu_13_cert_num:" + cardId);
		QueryResponse queryResponse = this.httpSolrClientPopu.query(query);
		SolrDocumentList docomentList = queryResponse.getResults();
		if(null != docomentList && docomentList.getNumFound() > 0) {
	        for(SolrDocument d : docomentList) {
	        	return "" + d.get("popu_13_unif_popu_id");
	        }
		}
		return null;
	}
	


	@Override
	public List<PopuPoints> popuList() {
		/*String data=[{
			name:"海口市",
			lon:110.3421,
			lat:20.0215
		},{
			name:"三亚市",
			lon:109.5016,
			lat:18.2472
		},{
			name:"临高市",
			lon:109.7461,
			lat:19.9446
		}
		];*/
		List<PopuPoints> pointsList=new ArrayList<PopuPoints>();
		PopuPoints point1=new PopuPoints();
			point1.setName("海口市");
			point1.setLon("110.3421");
			point1.setLat("20.0215");
			pointsList.add(point1);
		PopuPoints point2=new PopuPoints();
			point2.setName("三亚市");
			point2.setLon("109.5016");
			point2.setLat("18.2472");
			pointsList.add(point2);
		PopuPoints point3=new PopuPoints();
			point3.setName("临高市");
			point3.setLon("109.7461");
			point3.setLat("19.9446");
			pointsList.add(point3);
			
		return pointsList;
	}

	/**
	 * 点坐标solr空间查询
	 * @param pageInfo
	 * @throws Exception
	 */
	@Override
	public void querySolrDocumentsData(PageInfo pageInfo) throws Exception {
		//创建查询条件  
				SolrQuery solrParams = new SolrQuery();  
				Map<String, Object> m = pageInfo.getCondition();
				String queryValue = (String)m.get("queryValue");
				//明确类型查询
				String name=(String)m.get("name");
				if(name.equals("")){
					name="*";
				}
				String type = (String)m.get("type");
				/*if(type.equals("point")){
					solrParams.set("q", "popu_13_popu_nm:"+(name!= null?name:'*'));    
					solrParams.set("fq", "{!geofilt}");//距离过滤函数
					solrParams.set("pt", queryValue); //当前经纬度
					solrParams.set("sfield", sfield); //经纬度的字段
					solrParams.set("d", "5"); //就近 d km的所有数据
				}*/
				if(type.equals("polygon")){
					solrParams.set("q", "popu_13_popu_nm:"+name);
					//solrParams.set("q", "*:*");    
					solrParams.set("fq","popu_13_lat_lon"+':'+'"'+queryValue+'"');
				}
				int from = pageInfo.getFrom();
				int size = pageInfo.getSize();
				solrParams.setStart(from);
				solrParams.setRows(size);
				//solrParams.setQuery("q:*:*");
		        //查询并返回结果  
		        QueryResponse queryResponse = this.httpSolrClientPopu.query(solrParams);
		        List<PopuInfo> popuList = queryResponse.getBeans(PopuInfo.class);
		        pageInfo.setRows(popuList);
			    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
		
	}
	
	
}
