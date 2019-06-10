 package com.eastern.jinxin.hbase.entity;
 
 import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eastern.jinxin.business.label.entity.H50ManagerTagctgyinfoEntity;
import com.eastern.jinxin.business.label.service.H50ManagerTagctgyinfoService;
import com.eastern.jinxin.sys.common.common.utils.SpringContextUtils;
import com.eastern.jinxin.redis.serviceImpl.CacheServiceRedisImpl;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.operation.user.service.SysUserRoleService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
 
 @RequestMapping("tagHelperUtil")
 public class TagHelperUtil extends AbstractController
 {
   private static final Logger logger = LoggerFactory.getLogger(TagHelperUtil.class);
   private static TagHelperUtil tagHelperUtil;
   private final JedisPool jedisPool;
   public static final String tagTitle = RedisUtils.KEY;
   private H50ManagerTagctgyinfoService h50ManagerTagctgyinfoService 
   = (H50ManagerTagctgyinfoService)SpringContextUtils.getBean("h50ManagerTagctgyinfoService");
   private SysUserRoleService sysUserRoleService 
   = (SysUserRoleService)SpringContextUtils.getBean("sysUserRoleService");
   public static TagHelperUtil getInstance(JedisPool jedisPool)
   {
	   if (tagHelperUtil != null) {
       return tagHelperUtil;
     }
     tagHelperUtil = new TagHelperUtil(jedisPool);
     return tagHelperUtil;
   }
 
   private TagHelperUtil(JedisPool jedisPool)
   {
     this.jedisPool = jedisPool;
   }
 
   public List<TagHelperBean> getAllTagFromRedis(ByteToBitSet needToJavaObj)
   {
     Jedis resource = this.jedisPool.getResource();
 
     List resultList = new ArrayList();
     logger.debug("start get tag keys ");
 
     Set<String> allTags = resource.keys(RedisUtils.KEY + "500*");//法人500
     logger.debug("get tag keys has done");
     //除掉输出配置中不可显示的标签
     String status="2";//启用
     String showFlag="0";//不可见
     H50ManagerTagctgyinfoEntity entity= new H50ManagerTagctgyinfoEntity();
     //获取用户所属的角色列表
   	 List<Long> roleIdList = sysUserRoleService.queryRoleIdList(getUserId());
     entity.setStatus(status);
     entity.setShowFlag(showFlag);
     entity.setRoleIdList(roleIdList);
     List<String> tagCtgyIdList=h50ManagerTagctgyinfoService.queryNotShowTagId(entity);
     List<String> tagCtgyIdListAppendTagTitle=new ArrayList<>();
     for(String id:tagCtgyIdList){
    	 tagCtgyIdListAppendTagTitle.add(tagTitle+id);
     }
     allTags.removeAll(tagCtgyIdListAppendTagTitle);
     List<String> tagsList = new ArrayList<String>();
     for (String tagId : allTags)
     {
    	 
    	 tagsList.add(tagId);
     }
     
     //排序
	 Collections.sort(tagsList, new Comparator<String>() {
        @Override
        public int compare(String v1, String v2) {
              return v1.compareTo(v2);
        }
      });
     
     for(String tagId : tagsList) {
    	 TagHelperBean tagHelperBean = new TagHelperBean();
         tagHelperBean.setTagId(tagId);
   
         Long userCount = resource.bitcount(tagId);
         tagHelperBean.setUserCount(userCount);
         if (needToJavaObj == ByteToBitSet.NEED_TURN)
         {
           byte[] tagValueByte = resource.get(tagId.getBytes());
           BitSet tagValueBitSet = CacheServiceRedisImpl.fromByteArrayReverse(tagValueByte);
           tagHelperBean.setUserBit(tagValueBitSet);
         }
   
         resultList.add(tagHelperBean);
   
         resource.bitcount(tagId);
     }
 
     return resultList;
   }
 
   public List<TagHelperBean> checkUserAllTag(Long user)
   {
     logger.debug("start check tags of user :" + user);
 
     List<TagHelperBean>  allTag = getAllTagFromRedis(ByteToBitSet.NOT_NEED);
     logger.debug("tag full list has got :" + user);
 
     List tagList4User = new ArrayList();
     logger.debug("start check user tags....." + user);
     try {
       Jedis resource = this.jedisPool.getResource(); Throwable localThrowable3 = null;
       try {
         for (TagHelperBean tagObj : allTag)
         {
           String tagIdFromRedis = tagObj.getTagId();
 
           Boolean hasCheck = resource.getbit(tagIdFromRedis, user.longValue());
           if (hasCheck.booleanValue())
             tagList4User.add(tagObj);
         }
       }
       catch (Throwable localThrowable5)
       {
         localThrowable3 = localThrowable5; throw localThrowable5;
       }
       finally
       {
         if (resource != null) if (localThrowable3 != null) try { resource.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else resource.close();  
       }
     } catch (Exception e) {
       throw e;
     }
 
     logger.debug("check tags of user :" + user + " has done");
 
     return tagList4User;
   }
 

/**
 * 根据标签命中的人群数进行排序
 * @Param order 排序是否升序,true升序 false降序
 */
public static List<TagHelperBean> sortTagByUsers(List<TagHelperBean> tagList, final TagOrder order){

    Comparator comparable = new Comparator<TagHelperBean>() {

        @Override
        public int compare(TagHelperBean o1, TagHelperBean o2) {
            Long o1Count = o1.getUserCount();
            Long o2Count = o2.getUserCount();
            if(order == TagOrder.ORDER_ASC)
//                return o1Count-o2Count;
                return o1Count.compareTo(o2Count);
            return o2Count.compareTo(o1Count);
        }
    };

    Collections.sort(tagList,comparable);

    return tagList;
}

///*     */   public static List<TagHelperBean> sortTagByUsers(List<TagHelperBean> tagList, TagOrder order)
///*     */   {
///* 149 */     Comparator comparable = new Comparator(order)
///*     */     {
///*     */       public int compare(TagHelperBean o1, TagHelperBean o2)
///*     */       {
///* 153 */         Long o1Count = o1.getUserCount();
///* 154 */         Long o2Count = o2.getUserCount();
///* 155 */         if (order == TagHelperUtil.TagOrder.ORDER_ASC)
///*     */         {
///* 157 */           return o1Count.compareTo(o2Count);
///* 158 */         }return o2Count.compareTo(o1Count);
///*     */       }
///*     */     };
///* 162 */     Collections.sort(tagList, comparable);
///*     */ 
///* 164 */     return tagList;
///*     */   }
 
   public static enum ByteToBitSet
   {
     NEED_TURN, NOT_NEED;
   }
 
   public static enum TagOrder
   {
     ORDER_DESC, ORDER_ASC;
   }
 }

/* Location:           C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\
 * Qualified Name:     com.seabox.tagsys.persongroup.utils.TagHelperUtil
 * JD-Core Version:    0.6.0
 */