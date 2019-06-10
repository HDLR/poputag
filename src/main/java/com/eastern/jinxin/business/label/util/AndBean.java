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
public class AndBean {
	private List<String> andList=new ArrayList<String>();
	
	/**
	 * @return the andList
	 */
	public List<String> getAndList() {
		return andList;
	}

	/**
	 * @param andList the andList to set
	 */
	public void setAndList(List<String> andList) {
		this.andList = andList;
	}
	
	public void clearList(){
		andList.clear();
	}
	
	public void addAndTag(String tag_id){
		this.andList.add(tag_id);
	}

	public String getQueryStr(){
		String decreate_first="AND[";
		String decreate_end="]";
		int count=0;
		for(String andCondition:andList){
			if(count!=andList.size()-1){
				decreate_first +=andCondition +",";
			}else{
				decreate_first +=andCondition;
			}
			count++;
		}
		return decreate_first+decreate_end;
	}
}
