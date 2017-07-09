package com.zhangyingwei.treehole.admin.log.model;

/**
 * Created by zhangyw on 2017/6/14.
 */
public class LogModel {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 来源 IP
     */
    private String ip;
    /**
     * 请求类型
     */
    private String reqType;
    /**
     * IP 所在位置
     * 中国,北京,北京
     * 考虑到时间问题，这个值放在队列后边获取
     */
    private String ip_location;
    /**
     * 来源网址
     */
    private String referer;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 访问位置
     */
    private String uri;
    /**
     * 来源浏览器类型
     */
    private String agent;
    /**
     * 访问动作
     */
    private String action;
    /**
     * 时间戳
     */
    private Long timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp_location() {
        return ip_location;
    }

    public void setIp_location(String ip_location) {
        this.ip_location = ip_location;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", reqType='" + reqType + '\'' +
                ", ip_location='" + ip_location + '\'' +
                ", referer='" + referer + '\'' +
                ", url='" + url + '\'' +
                ", uri='" + uri + '\'' +
                ", agent='" + agent + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
