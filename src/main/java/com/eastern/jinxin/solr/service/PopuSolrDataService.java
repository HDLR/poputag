package com.eastern.jinxin.solr.service;

import java.util.List;

import com.eastern.jinxin.solr.model.PopuInfo;
import com.eastern.jinxin.solr.model.PopuPoints;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
 

public interface PopuSolrDataService {
	public void querySolrDocuments(PageInfo pageInfo) throws Exception;
	
	public void querySolrDocumentsData(PageInfo pageInfo) throws Exception;
	
	public PopuInfo querySolrDocumentById(String name, String value) throws Exception;
	
	public List<PopuPoints>  popuList();
}
