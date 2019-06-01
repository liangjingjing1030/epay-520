package org.epay.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 名称: json工具类
 * 作者: HappyDan
 * 版本: V1.0
 */
public class JsonUtil {

    static {
        System.setProperty("fastjson.compatibleWithJavaBean", "true");
    }

    public static String object2Json(Object object) {
        if (object == null) {
            return null;
        }
        return JSONObject.toJSONString(object);
    }

    public static <T> T getObjectFromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> getObjectListFromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        return JSON.parseArray(json, clazz);
    }

    public static JSONObject getJSONObjectFromJson(String json) {
        if (json == null) {
            return null;
        }
        return JSONObject.parseObject(json);
    }

    public static JSONObject getJSONObjectFromObj(Object object) {
        if (object == null) {
            return null;
        }
        return (JSONObject) JSONObject.toJSON(object);
    }

}
