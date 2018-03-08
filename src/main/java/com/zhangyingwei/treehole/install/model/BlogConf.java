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

    private String nickname;
    private String email;
    private String weibo;
    private String weixin;
    private String zhihu;
    private String facebook;
    private String twitter;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getZhihu() {
        return zhihu;
    }

    public void setZhihu(String zhihu) {
        this.zhihu = zhihu;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "BlogConf{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", weibo='" + weibo + '\'' +
                ", weixin='" + weixin + '\'' +
                ", zhihu='" + zhihu + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
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
