package com.eastern.jinxin.sys.common.excel.enums.colnums;

public interface BaseExcelEnum {
//	public  List<String> getImports();
//
//	public List<String> getUploads();
//
//	public List<String> getPrimaryKeys();
//
//	public Map<String, Object> getInsertParams(HttpServletRequest request);
	public boolean isUpload() ;
	public void setUpload(boolean isUpload) ;
	public String getCode() ;
	public String getCodeName() ;
	public boolean isPrimaryKey() ;
	public void setPrimaryKey(boolean isPrimaryKey) ;
}
