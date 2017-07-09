package com.zhangyingwei.treehole.admin.log.model;

/**
 * Created by zhangyw on 2017/6/16.
 * 访问页面信息
 */
public class PageVisitView {
    private Integer id;
    private String ip;
    private String uri;
    private String datetime;

    public PageVisitView(Integer id, String ip, String uri, String datetime) {
        this.id = id;
        this.ip = ip;
        this.uri = uri;
        this.datetime = datetime;
    }

    public PageVisitView() {
    }

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "PageVisit{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", uri='" + uri + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
