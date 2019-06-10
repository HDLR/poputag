package com.eastern.jinxin.business.label.util;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.stereotype.Service;

/**
 * <p>Title : spiltStr.java</p>
 * <p>Description :把!9 的计算转化 成（9!000000000）符号二元的运算 </p>	
 * @author qg.sun
 */
@Service("forMatterData")
public  class ForMatterData {
	public String replaceNot(String content){
		 //分解字符串
		 List<String> contenList=new ArrayList<String>();
		 StringTokenizer stringTokenizer = new StringTokenizer(content.replace(" ", "").replace("+", "&"), "&|!()",true);
		 while(stringTokenizer.hasMoreTokens()){
			 contenList.add(stringTokenizer.nextToken()+"");
            System.out.println(contenList+"");
        }
		Object[] content_arr = contenList.toArray();
		List<Integer> elementIndex=new ArrayList<Integer>();//存放非计算符号后面的 tagid 和index 或者是 ！（）中）的index
		//1。找出 ！和tag 的组合 替换成 tag ! 和 f
		//2。找出！（ ） 的替换成 （ ）! 和f  
		for(int i=0;i<content_arr.length;i++){
			if(content_arr[i].equals("!")){
				if(content_arr[i+1].equals("(")){
					int findIndex=kuohao(content_arr,i);//查询 匹配的 ）对应的index
					elementIndex.add(findIndex);
				}else if(isNum(content_arr[i+1])){//后面是tag
					elementIndex.add(i+1);
				}
			}
		}
		for(int i=0;i<content_arr.length;i++){
			if(content_arr[i].equals("!")){
				//content_arr[i]="";
				content_arr[i]="(";
			}
		}
		for(Integer i:elementIndex){
			content_arr[i]=content_arr[i]+"!00000000)";
		}
		String retString="";
		for(int i=0;i<content_arr.length;i++){
			retString=retString+content_arr[i];
		}
		return retString;
		
	}
	
	 public boolean isNum(Object content_arr){
        if(((String) content_arr).matches("[0-9]+")){    //这里使用正则表达式 验证是否是数字
            return true;
        }else{
            return false;
        }
    }
	 
	 /**
	 * 查找匹配 （ 的另一个 ）
	 */
	public int kuohao(Object[] content_arr ,int start){
		
		//查找下一个右括号，中间不能出现 左括号
		int index=0;
		for(int i=start;i<content_arr.length;i++){
			if(content_arr[i].equals(")")&&!existKuohao(content_arr,start+1,i)){
				index=i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * 1，查找 （ ）两段 之间是否存在 ( 
	 * 2，或者（ ）两段之间存在 成对的（）
	 */
	public boolean existKuohao(Object[] content_arr ,int start,int end){
		int left=0;//左括号
		int right=0;//右括号
		boolean flag=false;
		for(int i=start;i<=end;i++){
			if(content_arr[i].equals("(")){
				left++;
				flag=true;
			}else if(content_arr[i].equals(")")){
				right++;
			}
		}
		return left!=right&&flag;
	}
}	
