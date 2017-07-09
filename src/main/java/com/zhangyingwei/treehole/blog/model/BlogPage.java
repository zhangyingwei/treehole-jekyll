package com.zhangyingwei.treehole.blog.model;

/**
 * Created by zhangyw on 2017/6/1.
 * 博客页面对象
 */
public class BlogPage {
    private String title;
    private String date;
    private String categories;
    private String content;
    private String introduction;
    private String[] tags;
    private Boolean useCommont;

    public String getTitle() {
        return title;
    }

    public String getDate(){
        return date;
    }

    public BlogPage() {
    }

    public BlogPage setTitle(String title) {
        this.title = title;
        return this;
    }

    public BlogPage setDate(String date){
        this.date = date;
        return this;
    }

    public BlogPage(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getCategories() {
        return categories;
    }

    public BlogPage setCategories(String categories) {
        this.categories = categories;
        return this;
    }

    public String getContent() {
        return content;
    }

    public BlogPage setContent(String content) {
        this.content = content;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public BlogPage setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String[] getTags() {
        return tags;
    }

    public BlogPage setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public Boolean getUseCommont() {
        return useCommont;
    }

    public BlogPage setUseCommont(String useCommont) {
        this.useCommont = "on".equals(useCommont);
        return this;
    }
}
