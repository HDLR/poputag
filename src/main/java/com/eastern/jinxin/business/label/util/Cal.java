package com.eastern.jinxin.business.label.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.RedisUtils;
import redis.clients.jedis.BitOP;

/**
 * <p>Title : Cal.java</p>
 * <p>Description : 逻辑 与 、或的计算</p>	
 * @author qg.sun
 * @date 2018年5月23日
 * @Version 1.0.0
 */
@Service("cal")
public class Cal {
	@Autowired
	private CacheService cacheService;
	public String calAnd(String data1,String data2){
			AndBean and=new AndBean();
			and.addAndTag(decrate(data1));
			and.addAndTag(decrate(data2));
			String[] src=new String[2];
			src[0]=decrate(data1);
			src[1]=decrate(data2);
			cacheService.countLabelByte(BitOP.AND, decrate(and.getQueryStr()), src);
			return and.getQueryStr();
	}
	
	
	public String calOr(String data1,String data2){
			OrBean or=new OrBean();
			or.addOrTag(decrate(data1));
			or.addOrTag(decrate(data2));
			String[] src=new String[2];
			src[0]=decrate(data1);
			src[1]=decrate(data2);
			cacheService.countLabelByte(BitOP.OR, decrate(or.getQueryStr()), src);
			return or.getQueryStr();
	}
	
	public String calNot(String data1,String data2){
		NotBean not=new NotBean();
		not.addNotTag(decrate(data1));
		//not.addNotTag(decrate(data2));
		String[] src=new String[1];
		src[0]=decrate(data1);
		//src[1]=decrate(data2);
		cacheService.countLabelByte(BitOP.NOT, decrate(not.getQueryStr()), src);
		return not.getQueryStr();
	}
	public String decrate(String tag){
		return RedisUtils.KEY + tag;
		//return tag;
	}
}
