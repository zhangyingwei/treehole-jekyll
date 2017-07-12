package com.zhangyingwei.treehole.blog.model;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/12.
 * 当前页面对象
 * 主要包含页面信息
 */
public class Page {
    /**
     * 当前页的标题
     */
    private String title;
    /**
     * 当前页的 url
     */
    private String url;
    /**
     * 当前页的 description
     */
    private String description;
    /**
     * 如果当前被处理的页面是一个 Post，这个变量就会包含最多10个相关的 Post。默认的情况下， 相关性是低质量的，但是能被很快的计算出来。
     */
    private List<Post> relatedPosts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getRelatedPosts() {
        return relatedPosts;
    }

    public void setRelatedPosts(List<Post> relatedPosts) {
        this.relatedPosts = relatedPosts;
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", relatedPosts=" + relatedPosts +
                '}';
    }
}
