package com.zhangyingwei.treehole.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/4/24.
 */
public class Ajax {

    /**
     * code of ajax success
     */
    public static final int CODE_SUCCESS = 200;
    /**
     * message of ajax success
     */
    public static final String MSG_SUCCESS = "success";

    /**
     * code of ajax error
     */
    public static final int CODE_ERROR = 400;

    /**
     * message of ajax error
     */
    public static final String MSG_ERROR = "error";

    /**
     * ajax result data
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Map<String,Object> result(int code,String message,Object data){
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("data", data);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("code", code);
        map.put("message", message);
        map.put("result", result);
        return map;
    }

    /**
     * ajax result message
     * @param code
     * @param message
     * @return
     */
    private static Map<String,Object> message(int code,String message){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }

    /**
     * ajax success data
     * @param message
     * @param data
     * @return
     */
    public static Map<String,Object> success(String message,Object data){
        return result(CODE_SUCCESS, message, data);
    }

    /**
     * ajax success data with message and code
     * @param message
     * @return
     */
    public static Map<String,Object> success(String message){
        return message(CODE_SUCCESS, message);
    }

    /**
     * ajax result data with default message
     * @param data
     * @return
     */
    public static Map<String,Object> success(Object data){
        return result(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    /**
     * ajax error result
     * @return
     */
    public static Map error(String message) {
        return message(CODE_ERROR, message);
    }
}
