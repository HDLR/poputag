package com.eastern.jinxin.sys.common.common.validator;

import org.apache.commons.lang3.StringUtils;

import com.eastern.jinxin.sys.common.common.utils.RRException;

/**
 * 数据校验
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
