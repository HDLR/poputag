package com.eastern.jinxin.solr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.solr.model.LegalInfo;
import com.eastern.jinxin.solr.service.LegalSolrDataService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

@Service
public class LegalSolrDataServiceImpl implements  LegalSolrDataService{

	/*@Value("${toUrl}")
	private String toUrl;
	
	@Value("${client.url}")
	private String clientUrl;*/
	
	/*@Autowired  
    private HttpSolrClient httpSolrClient;*/
	
	@Resource(name = "httpSolrClientLegal")
	private HttpSolrClient httpSolrClientLegal;
	
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
			 query="legal_17_corp_nm:*";
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
        QueryResponse queryResponse = this.httpSolrClientLegal.query(solrParams);
        List<LegalInfo> popuList = queryResponse.getBeans(LegalInfo.class);
        pageInfo.setRows(popuList);
	    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
	}
	
	private String pd(String query, String value){
		String regex = "^[0-9A-Za-z]{1,30}$";
		if(value.matches(regex)){
			if(StringUtils.isEmpty(query)){
				query = "all_number_17:" + value+"*";
			}else{
				query = " AND " +"all_number_17:" + value+"*";
			}
		}else{
			if(StringUtils.isEmpty(query)){
				query = "all_content_17:" + value;
			}else{
				query = " AND " +"all_content_17:" + value;
			}
		}
		return query;
	}
	
	public LegalInfo querySolrDocumentById(String name, String value) throws Exception{
		SolrQuery query = new SolrQuery();
		query.setQuery(name + ":" + value);
		QueryResponse queryResponse = this.httpSolrClientLegal.query(query);
		List<LegalInfo> popuList = queryResponse.getBeans(LegalInfo.class);
		if(null != popuList && popuList.size() > 0) {
			for(LegalInfo info : popuList) {
				info.createMap();
			}
			return popuList.get(0);
		}
		return null;
	}
	
	public String querySolrByCardId(String cardId) throws Exception{
		SolrQuery query = new SolrQuery();
		query.setQuery("legal_17_lpr_cert_num:" + cardId);//法人身份证号
		QueryResponse queryResponse = this.httpSolrClientLegal.query(query);
		SolrDocumentList docomentList = queryResponse.getResults();
		if(null != docomentList && docomentList.getNumFound() > 0) {
	        for(SolrDocument d : docomentList) {
	        	return d.get("legal_17_lpr_id")  + "value" + d.get("legal_17_uscc");//统一社会信用代码
	        }
		}
		return null;
	}

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
			System.out.println(type);
			solrParams.set("q", "*:*");    
			solrParams.set("fq", "{!geofilt}");           //距离过滤函数
			solrParams.set("pt", queryValue); //当前经纬度
			solrParams.set("sfield", "legal_17_lat_lon_rpt"); //经纬度的字段
			solrParams.set("d", "5"); //就近 d km的所有数据
		}*/
		if(type.equals("polygon")){
			solrParams.set("q", "legal_17_lpr_nm:"+name);    
			solrParams.set("fq","legal_17_lat_lon_rpt"+':'+'"'+queryValue+'"');
		}
		
		int from = pageInfo.getFrom();
		int size = pageInfo.getSize();
		solrParams.setStart(from);
		solrParams.setRows(size);
		//solrParams.setQuery("q:*:*");
        //查询并返回结果  
        QueryResponse queryResponse = this.httpSolrClientLegal.query(solrParams);
        List<LegalInfo> legalInfo = queryResponse.getBeans(LegalInfo.class);
        pageInfo.setRows(legalInfo);
	    pageInfo.setTotal((int)queryResponse.getResults().getNumFound());
	}
	
	/*public R httpRequestClient(String cardId) throws Exception {
		String reqParam = "cardId=" + cardId;//法人身份证号
		String re = RequestInformationUtil.sendGet(toUrl, reqParam);
		if(StringUtils.isBlank(re)) {
			return R.error();
		}
		JSONObject jsonobject = JSONObject.fromObject(re);
		return (R)JSONObject.toBean(jsonobject,R.class);
	}
	
	public String getClientUrl() {
		return clientUrl + "?username=together&password=together1";
	}*/
}
