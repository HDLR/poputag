package com.eastern.jinxin.business.label.util;

/**
 * <p>Title : OrBean.java</p>
 * <p>Description :逻辑或操作 </p>	
 * @author qg.sun
 * @date 2018年5月23日
 * @Version 1.0.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title : OrBean.java</p>
 * <p>Description : </p>	
 * @author qg.sun
 * @date 2018年5月23日
 */
public class OrBean {
	private  List<String> orList=new ArrayList<String>();
	
	/**
	 * @return the orList
	 */
	public List<String> getOrList() {
		return orList;
	}

	/**
	 * @param orList the orList to set
	 */
	public void setOrList(List<String> orList) {
		this.orList = orList;
	}

	public void clearList(){
		orList.clear();
	}
	
	public void addOrTag(String tag_id){
		this.orList.add(tag_id);
	}

	public String getQueryStr(){
		String decreate_first="OR[";
		String decreate_end="]";
		int count=0;
		for(String andCondition:orList){
			if(count!=orList.size()-1){
				decreate_first +=andCondition +",";
			}else{
				decreate_first +=andCondition;
			}
			count++;
		}
		return decreate_first+decreate_end;
	}
}
