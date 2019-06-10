package com.eastern.jinxin.redis.utils;

import java.io.IOException;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserTagUtils {
	private static final Logger logger = LoggerFactory.getLogger(UserTagUtils.class);
	public static final String TAG_PREFIX = RedisUtils.KEY;
	public static final String REDIS_KEY_INDEX_TO_USER_GUID_MAP = "n-2-guid";
	public static final String REDIS_KEY_USER_GUID_TO_INDEX_MAP = "guid-2-n";
	public static final String GLOBAL_EXCLUDE_USERS_KEY = "g:excludeUsers";
	public static final int DEFAULT_TEMP_CALC_REDIS_KEY_EXPIRE_SECONDS = 180;
	private static final Character[] SHORT_ENCODING_CHARS = { Character.valueOf('a'), Character.valueOf('b'), Character.valueOf('c'),
			Character.valueOf('d'), Character.valueOf('e'), Character.valueOf('f'), Character.valueOf('g'), Character.valueOf('h'),
			Character.valueOf('i'), Character.valueOf('j'), Character.valueOf('k'), Character.valueOf('l'), Character.valueOf('m'),
			Character.valueOf('n'), Character.valueOf('o'), Character.valueOf('p'), Character.valueOf('q'), Character.valueOf('r'),
			Character.valueOf('s'), Character.valueOf('t'), Character.valueOf('u'), Character.valueOf('v'), Character.valueOf('w'),
			Character.valueOf('x'), Character.valueOf('y'), Character.valueOf('z'), Character.valueOf('0'), Character.valueOf('1'),
			Character.valueOf('2'), Character.valueOf('3'), Character.valueOf('4'), Character.valueOf('5'), Character.valueOf('6'),
			Character.valueOf('7'), Character.valueOf('8'), Character.valueOf('9'), Character.valueOf('A'), Character.valueOf('B'),
			Character.valueOf('C'), Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'), Character.valueOf('G'),
			Character.valueOf('H'), Character.valueOf('I'), Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'),
			Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'), Character.valueOf('P'), Character.valueOf('Q'),
			Character.valueOf('R'), Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'), Character.valueOf('V'),
			Character.valueOf('W'), Character.valueOf('X'), Character.valueOf('Y'), Character.valueOf('Z'), Character.valueOf('-'),
			Character.valueOf('_') };

	private static final int[] chars_mapping = new int[256];

	public static String getCacheKeyByTagId(String tagId) {
		StringBuffer bf = new StringBuffer(RedisUtils.KEY);
		bf.append(tagId);
		return bf.toString();
	}

	public static String getCampBitSetRedisKey(String campId) {
		return "camp_bits:" + campId;
	}

	public static String encodeTwoDigitsToShortUrl(long digit1, long digit2) {
		StringBuffer sbDigit1 = new StringBuffer();
		StringBuffer sbDigit2 = new StringBuffer();

		getShortEncodeStr(sbDigit1, digit1);

		getShortEncodeStr(sbDigit2, digit2);

		StringBuffer result = new StringBuffer();

		result.append(sbDigit1);
		result.append(":");
		result.append(sbDigit2);

		return result.toString();
	}

	public static String encodeOneDigitToShortUrl(long digit1) {
		StringBuffer sbDigit1 = new StringBuffer();

		getShortEncodeStr(sbDigit1, digit1);

		return sbDigit1.toString();
	}

	public static long decodeOneDigitFromShortUrl(String digitStr) {
		long val = 0L;
		for (int i = 0; i < digitStr.length(); i++) {
			char c = digitStr.charAt(i);

			int index = chars_mapping[c];
			if (index == -1) {
				logger.error("wrong digitStr: {}", digitStr);
				return -1L;
			}
			val <<= 6;
			val += index;
		}

		return val;
	}

	private static void getShortEncodeStr(StringBuffer sb, long val) {
		long sub = val / 64L;
		long mod = val % 64L;

		if (val <= 64L) {
			sb.append(SHORT_ENCODING_CHARS[(int) mod]);
		} else {
			getShortEncodeStr(sb, sub);
			sb.append(SHORT_ENCODING_CHARS[(int) mod]);
		}
	}

	public static <T> T jsonToObject(String json, Class<T> classType) throws Exception {
		return getSerializeTool().jsonToObject(json, classType);
	}

	public static String objectToJson(Object object) {
		return getSerializeTool().objectToJson(object);
	}

	private static SerializeTool getSerializeTool() {
		return JacksonTool.instance;
	}

	static {
		for (int j = 0; j < chars_mapping.length; j++) {
			chars_mapping[j] = -1;
		}

		for (int i = 0; i < SHORT_ENCODING_CHARS.length; i++) {
			char valChar = SHORT_ENCODING_CHARS[i].charValue();
			chars_mapping[valChar] = i;
		}
	}

	private static class JacksonTool implements SerializeTool {
		public static final JacksonTool instance = new JacksonTool();

		@Override
		public String objectToJson(Object object) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			String jsonValue = null;
			try {

				jsonValue = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

			} catch (JsonProcessingException e) {
				logger.error("Error on Serialize Object to Json, object= {}, detail exception:", object, e);
			}
			return jsonValue;
		}

		@Override
		public <T> T jsonToObject(String json, Class<T> classType) throws Exception {
			ObjectMapper objectMapper = new ObjectMapper();
			Object obj = null;
			try {
				obj = objectMapper.readValue(json, classType);
			} catch (IOException e) {
				UserTagUtils.logger.error("Error on DeSerialize json to Object, json= {},  detail exception:", json, e);
				throw e;
			}
			return (T) obj;
		}
	}

	private static class GsonTool implements SerializeTool {
		private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

		public static final GsonTool instance = new GsonTool();

		public String objectToJson(Object object) {
			return gson.toJson(object);
		}

		public <T> T jsonToObject(String json, Class<T> classType) throws Exception {
			Object obj = null;
			try {
				obj = gson.fromJson(json, classType);
			} catch (Throwable e) {
				UserTagUtils.logger.error("Error on DeSerialize json to Object, json= {},  detail exception:", json, e);
				throw e;
			}
			return (T) obj;
		}
	}

	private static abstract interface SerializeTool {
		public abstract String objectToJson(Object paramObject);

		public abstract <T> T jsonToObject(String paramString, Class<T> paramClass) throws Exception;
	}
}

/*
 * Location: C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\ Qualified Name:
 * com.seabox.tagsys.usertags.utils.UserTagUtils JD-Core Version: 0.6.0
 */