package com.eastern.jinxin.sys.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectJsonUtils {
	
	public static <T> String objectToJson(T object)
   {
     ObjectMapper mapper = new ObjectMapper();
     String jsonValue = null;
     try {
       jsonValue = mapper.writeValueAsString(object);
     }
     catch (JsonProcessingException e) {
       e.printStackTrace();
     }
 
     return jsonValue;
   }

}
