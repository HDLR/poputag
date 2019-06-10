package com.eastern.jinxin.solr.conf;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SolrConf {

	@Value("${spring.data.solr.popu.host}")
	private String solrUrlPopu;
	
	@Value("${spring.data.solr.legal.host}")
	private String solrUrlLegal;
	
	@Primary
	@Bean(name = "httpSolrClientPopu")
	public HttpSolrClient httpSolrClientPopu() {
		return new HttpSolrClient(solrUrlPopu);
	}
	
	@Bean(name = "httpSolrClientLegal")
	public HttpSolrClient httpSolrClientLegal() {
		return new HttpSolrClient(solrUrlLegal);
	}
}
