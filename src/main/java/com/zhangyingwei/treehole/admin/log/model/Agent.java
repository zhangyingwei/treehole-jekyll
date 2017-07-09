package com.zhangyingwei.treehole.admin.log.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangyw on 2017/6/21.
 * 请求 agent 实体类
 */
public class Agent {
    /**
     * 系统类型
     * windows linux mac android iphone ipad
     */
    private String osType = "未知系统类型";

    /**
     * 系统类型数据字典
     */
    private Map<String, String> osTypeMap = new TreeMap<String,String>(){
        {
            put("windows", "windows");
            put("linux", "linux");
            put("mac", "macOs");
            put("ipad", "ios");
            put("iphone", "ios");
        }
    };

    /**
     * 终端类型
     * 电脑 手机 平板
     */
    private String osKind = "未知终端类型";

    /**
     * 终端类型数据字典
     */
    private Map<String, String> osKindMap = new TreeMap<String,String>(){
        {
            put("spider", "未知爬虫");
            put("windows", "windows");
            put("linux", "linux");
            put("mac", "mac");
            put("ipad", "ipad");
            put("iphone", "iphone");
            put("Android", "安卓");
            put("baiduspider", "百度爬虫");
            put("sogou web spider", "搜狗爬虫");
            put("yisouspider", "宜搜爬虫");
        }
    };

    /**
     * 浏览器类型
     */
    private String browser = "未知浏览器类型";

    private Map<String, String> bowserMap = new TreeMap<String,String>(){
        {
            put("MSIE 6.0", "IE6");
            put("MSIE 7.0", "IE7");
            put("MSIE 8.0", "IE8");
            put("MSIE 9.0", "IE9");
            put("MSIE 10.0", "IE10");
            put("MSIE 11.0", "IE11");
            put("MSIE 12.0", "IE12");
            put("tieba", "百度贴吧");
            put("Weibo", "微博");
            put("Wechat", "微信");
            put("SogouMobileBrowser", "搜狗手机浏览器");
            put("MQQBrowser", "QQ手机浏览器");
            put("Firefox", "火狐浏览器");
            put("Netscape", "Netscape浏览器");
            put("SeaMonkey", "SeaMonkey浏览器");
            put("chrome", "谷歌浏览器");
            put("Safari", "Safari浏览器");
        }
    };

    /**
     * 请求 agent
     */
    private String agent;

    private Boolean isMobile = false;

    public Agent(String agent){
        this.agent = agent;
    }

    public Boolean getMobile() {
        return isMobile;
    }

    /**
     * 获取系统类型
     * @return
     */
    public String getOsType() {
        for (Map.Entry<String, String> entry : osTypeMap.entrySet()) {
            if(this.agent.toLowerCase().contains(entry.getKey().toLowerCase())){
                this.osType = entry.getValue();
                break;
            }
        }
        return osType;
    }

    /**
     * 获取终端类型
     * @return
     */
    public String getOsKind() {
        for (Map.Entry<String, String> entry : osKindMap.entrySet()) {
            if(this.agent.toLowerCase().contains(entry.getKey().toLowerCase())){
                this.osKind = entry.getValue();
                break;
            }
        }
        return osKind;
    }

    /**
     * 获取浏览器类型
     * @return
     */
    public String getBrowser() {
        for (Map.Entry<String, String> entry : bowserMap.entrySet()) {
            if(this.agent.toLowerCase().contains(entry.getKey().toLowerCase())){
                this.browser = entry.getValue();
                break;
            }
        }
        return browser;
    }

    public String getAgent() {
        return agent;
    }
}
