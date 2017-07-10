package com.zhangyingwei.treehole.install.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2017/4/26.
 * 博客配置信息
 */

public class BlogConf {
    /**
     * 博客名称
     */
    @NotEmpty(message = "博客名称不能为空")
    private String name;
    /**
     * 博客地址 <br>
     * 可以是域名，也可以是ip
     */
    private String url;
    /**
     * 博客介绍
     */
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BlogConf{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    /**
     * 构造配置信息为Map
     * @return
     */
    public Map bulid(){
        Map map = new HashMap();
        map.put("name", name);
        map.put("url", url);
        map.put("desc", desc);
        return map;
    }
}
