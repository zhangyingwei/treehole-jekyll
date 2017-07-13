package com.zhangyingwei.treehole.admin.model;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by zhangyw on 2017/7/13.
 */
public class Link {
    private Integer id;
    private String name;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public java.lang.String toString() {
        return "Link{" +
                "id=" + id +
                ", name=" + name +
                ", url=" + url +
                '}';
    }
}
