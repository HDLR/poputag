package com.eastern.jinxin.sys.common.excel.enums.colnums;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class EnumUtils {

	/**
	 * 枚举类对应的包路径
	 */
	public final static String PACKAGE_NAME = "com.seabox.common.excel.enums.columns.imp";
	/**
	 * 枚举接口类全路径
	 */
	public final static String ENUM_MESSAGE_PATH = "com.seabox.common.excel.enums.columns.BaseExcelEnum";
	/**
	 * 枚举类对应的全路径集合
	 */
	public static final List<String> ENUM_OBJECT_PATH = PackageUtil.getPackageClasses(PACKAGE_NAME, true);
	/**
	 * 导出集合枚举
	 */
	private static Map<Class, List<String>> ENUM_LIST_IMPORTS = null;
	/**
	 * 导入集合枚举
	 */
	private static Map<Class, List<String>> ENUM_LIST_UPLOADS = null;
	/**
	 * 主键枚举集合英文名称code
	 */
	private static Map<Class, List<String>> ENUM_LIST_PRIMARYKEY = null;
	/**
	 * 前台插入集合枚举
	 */
	private static Map<Class, List<String>> ENUM_LIST_INSERT_PARAMS = null;
	/**
	 * 主键枚举集合中文名称codename
	 */
	private static Map<Class, List<String>> ENUM_LIST_PRIMARYKEY_CN = null;
	/**
	 * 主键枚举集合中英文名称code,codename
	 */
	private static Map<Class, List<Map<String, String>>> ENUM_LIST_PRIMARYKEY_CODE_NAME = null;
	/**
	 * 插入枚举集合中英文名称code,codename
	 */
	private static Map<Class, List<Map<String, String>>> ENUM_LIST_INSERT_CODE_NAME = null;

	static {
		initialEnum();
	}

	/**
	 * excel数据导出的枚举集合(code)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getImports(Class clazz) {
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_IMPORTS).get(clazz);
		return list;
	}

	/**
	 * excel数据导入的枚举集合(codename)
	 * 数据查询的字段
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getUpload(Class clazz) {
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_UPLOADS).get(clazz);
		return list;
	}

	/**
	 * 主键枚举集合(code)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getPrimaryKeys(Class clazz) {
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_PRIMARYKEY).get(clazz);
		return list;
	}

	/**
	 * excel数据插入的主键枚举集合(codename)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getPrimaryKeysCN(Class clazz) {
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_PRIMARYKEY_CN).get(clazz);
		return list;
	}

	/**
	 * excel数据插入的主键枚举集合(code,codename)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, String>> getPrimaryKeysCodeAndName(Class clazz) {
		List<Map<String, String>> list = (EnumUtils.ENUM_LIST_PRIMARYKEY_CODE_NAME).get(clazz);
		return list;
	}

	/**
	 * excel数据插入的枚举集合(code,codename)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, String>> getInsertCodeAndName(Class clazz) {
		List<Map<String, String>> list = (EnumUtils.ENUM_LIST_INSERT_CODE_NAME).get(clazz);
		return list;
	}

	/**
	 * 前台数据插入的枚举集合(code,codeName)
	 * 
	 * @param request
	 * @param map
	 * @param clazz
	 */
	public static void getInsertParams(HttpServletRequest request, Map<String, Object> map, Class clazz) {
		String val = "";
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_INSERT_PARAMS).get(clazz);
		for (String key : list) {
			val = request.getParameter(key);
			val = StringUtils.isNotBlank(val) ? (val.trim()) : "";
			map.put(key, val);
		}
	}
	
	/**
	 * 前台数据插入的枚举集合(code,codeName)
	 * 
	 * @param request
	 * @param map
	 * @param clazz
	 */
	public static List<String> getInsertCode( Class clazz) {
		List<String> list = ((Map<Class, List<String>>) EnumUtils.ENUM_LIST_INSERT_PARAMS).get(clazz);
		return list;
	}

	/**
	 * 加载所有枚举对象数据
	 * 
	 * @param isFouceCheck
	 *            是否强制校验枚举是否实现了BaseExcelEnum接口
	 *
	 */
	@SuppressWarnings("rawtypes")
	private static void initialEnum() {
		ENUM_LIST_IMPORTS = new HashMap<Class, List<String>>();
		ENUM_LIST_UPLOADS = new HashMap<Class, List<String>>();
		ENUM_LIST_PRIMARYKEY = new HashMap<Class, List<String>>();
		ENUM_LIST_INSERT_PARAMS = new HashMap<Class, List<String>>();
		ENUM_LIST_PRIMARYKEY_CN = new HashMap<Class, List<String>>();
		ENUM_LIST_PRIMARYKEY_CODE_NAME = new HashMap<Class, List<Map<String, String>>>();
		ENUM_LIST_INSERT_CODE_NAME = new HashMap<Class, List<Map<String, String>>>();

		try {
			for (String classname : ENUM_OBJECT_PATH) {
				Class<?> cls = null;
				cls = Class.forName(classname);
				Class<?>[] iter = cls.getInterfaces();
				boolean flag = false;
				for (Class cz : iter) {
					if (cz.getName().equals(ENUM_MESSAGE_PATH)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					initImports(cls);
					initUploads(cls);
					initPrimaryKey(cls);
					initInsertParams(cls);
					initPrimaryKeyCN(cls);
					initPrimaryKeyCodeAndName(cls);
					initInsertCodeAndName(cls);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initImports(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<String> list = new ArrayList<String>();
		for (BaseExcelEnum u : inter) {
			list.add(u.getCode());
		}
		ENUM_LIST_IMPORTS.put(cls, list);
	}

	private static void initUploads(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<String> list = new ArrayList<String>();
		for (BaseExcelEnum u : inter) {
			list.add(u.getCodeName());
		}
		ENUM_LIST_UPLOADS.put(cls, list);
	}

	private static void initInsertParams(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<String> list = new ArrayList<String>();
		for (BaseExcelEnum u : inter) {
			if (u.isUpload()) {
				list.add(u.getCode());
			}
		}
		ENUM_LIST_INSERT_PARAMS.put(cls, list);
	}

	private static void initPrimaryKey(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<String> list = new ArrayList<String>();
		for (BaseExcelEnum u : inter) {
			if (u.isPrimaryKey()) {
				list.add(u.getCode());
			}
		}
		ENUM_LIST_PRIMARYKEY.put(cls, list);
	}

	private static void initPrimaryKeyCN(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<String> list = new ArrayList<String>();
		for (BaseExcelEnum u : inter) {
			if (u.isPrimaryKey()) {
				list.add(u.getCodeName());
			}
		}
		ENUM_LIST_PRIMARYKEY_CN.put(cls, list);
	}

	private static void initPrimaryKeyCodeAndName(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (BaseExcelEnum u : inter) {
			if (u.isPrimaryKey()) {
				map = new HashMap<String, String>();
				map.put(u.getCode(), u.getCodeName());
				list.add(map);
			}
		}
		ENUM_LIST_PRIMARYKEY_CODE_NAME.put(cls, list);
	}

	private static void initInsertCodeAndName(Class<?> cls) throws Exception {
		Method method = cls.getMethod("values");
		BaseExcelEnum inter[] = (BaseExcelEnum[]) method.invoke(null, null);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (BaseExcelEnum u : inter) {
			if (u.isUpload()) {
				map = new HashMap<String, String>();
				map.put(u.getCode(), u.getCodeName());
				list.add(map);
			}
		}
		ENUM_LIST_INSERT_CODE_NAME.put(cls, list);
	}

}