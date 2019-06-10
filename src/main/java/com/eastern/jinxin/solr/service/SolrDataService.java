package com.eastern.jinxin.solr.service;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;

public interface SolrDataService {

	void querySolrDocuments() throws Exception;
	
	void querySolrDocumentsLegal() throws Exception;
}
