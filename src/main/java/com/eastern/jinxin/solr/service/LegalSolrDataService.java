package com.eastern.jinxin.solr.service;

import com.eastern.jinxin.solr.model.LegalInfo;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;

public interface LegalSolrDataService {

	public void querySolrDocuments(PageInfo pageInfo) throws Exception;
	
	public void querySolrDocumentsData(PageInfo pageInfo) throws Exception;
	
	public LegalInfo querySolrDocumentById(String name, String value) throws Exception;
	
	//public R httpRequestClient(String cardId) throws Exception;
	
	//public String getClientUrl();
	
	public String querySolrByCardId(String cardId) throws Exception;
}
