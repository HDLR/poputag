package com.eastern.jinxin.business.label.util;

 
 import java.util.ArrayList;
 import java.util.Stack;
 import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 @Service("nbl")
 public class NBL {
	 @Autowired
	 private Cal cal;
     //  操作符栈
     private Stack<String> czf_stack =null;        // 存放 运算符的栈
     private  ArrayList<String> ysbds_list =null;     //存放 原始表达式的 arraylist
     private  ArrayList<String> nblbds_list =null;     // 存放转换后的 逆波兰式
     private static final int One = 1;      //
     private static final int Two = 3;     //
     private static final int Three = 5;   //规定优先级   Three 最高
     
     //  定义一个运算栈
     private static Stack<String> ys_stack = new Stack<>();
     
     public NBL(){
    	 super();
     }
     
     // 初始化                             使用StringTokenizer分割 字符串
     public NBL(String bdString) {
         StringTokenizer stringTokenizer = new StringTokenizer(bdString, "&!|()",true);
         while(stringTokenizer.hasMoreTokens()){
             ysbds_list.add(stringTokenizer.nextToken());
         }
     }
     
     public void init(){
    	   //  操作符栈
         czf_stack = new Stack<>();        // 存放 运算符的栈
         ysbds_list = new ArrayList<>();     //存放 原始表达式的 arraylist
         nblbds_list = new ArrayList<>(); 
     }
     public void tran(String bdString) {
         StringTokenizer stringTokenizer = new StringTokenizer(bdString, "!&|()",true);
         while(stringTokenizer.hasMoreTokens()){
             ysbds_list.add(stringTokenizer.nextToken());
         }
     }
     
     
     // 判断 是否是数字
     public boolean isNum(String str){
         if(str.matches("[0-9]{0,10}")){    //这里使用正则表达式 验证是否是数字
             return true;
         }else{
             return false;
         }
     }
     
     // 判断 是否是操作符
     public boolean isCzf(String str){
         if(str.matches("[\\&\\|\\!\\(\\)]")){
             return true;
         }else{
             return false;
         }
     }
     
     // 获取 优先级
     public int getYxj(String str){
         
         switch(str){
         case "(":return Three;
         case "*":return Two;
         case "!":
         case "&":
         case "|":return One;
         case ")":return 0;
         
         default : return -1;
         
         }
         
     }
     
     // 判断优先级 
     public boolean isYxj(String str1,String str2){
         return getYxj(str1) > getYxj(str2);   
     }
     
     //   ********* 当 当前操作元素为 操作符时**********    这里是 核心代码， 用于操作符栈的判断
     public void stack_czf(String czf){
         
         //判断当前栈内是否为空
         if(czf_stack.isEmpty()){
             czf_stack.push(czf);
             return;
         }
         
         //判断是否为 (
         if("(".equals(czf)){
             czf_stack.push(czf);
             return;
         }
         
         //判断是否为 )
         if(")".equals(czf)){
             String string = "";
             while(!"(".equals(string = czf_stack.pop())){
                 nblbds_list.add(string);
             }
             return;
         }
         
         //如果 当前栈顶元素是  (  直接入栈
         if("(".equals(czf_stack.peek())){
             czf_stack.push(czf);
             return;
         }
         
         // 判断 与 栈顶元素的优先级 , > 为true
         if(isYxj(czf, czf_stack.peek())){
             czf_stack.push(czf);
             return;
         }
         
         if(!isYxj(czf, czf_stack.peek())){
             nblbds_list.add(czf_stack.pop());
             stack_czf(czf);   //这里调用函数 本身，并将本次的操作数传参
         }
         
     }
     
     // 中缀 —> 后缀
     public void zz_hz(){
         
         // 遍历原始表达式list
         for(String str:ysbds_list){
             
             if(isNum(str)){
                 nblbds_list.add(str);
             }else if(isCzf(str)){
                 //TODO
                 stack_czf(str);
             }else{
                 System.out.println("非法");
             }
             
         }
         
         // 遍历完原始表达式后  将操作符栈内元素 全部添加至 逆波兰表达式list
 
         while(!czf_stack.isEmpty()){
             //System.out.println("即将 " + czf_stack.peek());
             nblbds_list.add(czf_stack.pop());
         }
         
     }
     
     public String jsff(String data1,String data2,String s3){
         switch(s3){
           case "&":return cal.calAnd(data1,data2);
           case "|":return cal.calOr(data1,data2);
           case "!":return cal.calNot(data2,data1);
           default : return "";
         }
     }
     
     //  计算 逆波兰式
     public String js_nbl(){
         for(String str:nblbds_list){
             if(isNum(str)){
                 ys_stack.push(str);
             }else{
                 ys_stack.push(String.valueOf(jsff(ys_stack.pop(), ys_stack.pop(), str)));
             }
         }
         return ys_stack.pop();  //最后 栈中元素 即为结果
     }
     
     
     
     public static void main(String[] args) {
//		 String source="1001010101|(1001010502|(1001010503&1001010504))&1001010205|1001010506";
//	     Scanner keyboard = new Scanner(System.in);
//	     System.out.println("请输入");
//	     String input = keyboard.nextLine();
//	     NBL nbl = new NBL(source);
//	     String nbls_cc = new String();
//	     String result = "";
//	     nbl.zz_hz();
//	     System.out.println("对应的逆波兰式为 ：" + nbls_cc);
//	     System.out.println("结果是：");
//	     result = nbl.js_nbl();
//	     System.out.println(result);
     }
     
     
 }