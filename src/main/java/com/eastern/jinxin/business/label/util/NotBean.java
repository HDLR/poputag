package com.eastern.jinxin.business.label.util;

/**
 * <p>Title : AndBean.java</p>
 * <p>Description : 逻辑与操作</p>	
 * @author qg.sun
 * @date 2018年5月23日
 * @Version 1.0.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : AndBean.java</p>
 * <p>Description : </p>	
 * @author qg.sun
 * @date 2018年5月23日
 */
public class NotBean {
	private List<String> notList=new ArrayList<String>();
	
	/**
	 * @return the andList
	 */
	public List<String> getNotList() {
		return notList;
	}

	/**
	 * @param andList the andList to set
	 */
	public void setNotList(List<String> notList) {
		this.notList = notList;
	}
	
	public void clearList(){
		notList.clear();
	}
	
	public void addNotTag(String tag_id){
		this.notList.add(tag_id);
	}

	public String getQueryStr(){
		String decreate_first="NOT[";
		String decreate_end="]";
		int count=0;
		for(String andCondition:notList){
			if(count!=notList.size()-1){
				decreate_first +=andCondition +",";
			}else{
				decreate_first +=andCondition;
			}
			count++;
		}
		return decreate_first+decreate_end;
	}
}
