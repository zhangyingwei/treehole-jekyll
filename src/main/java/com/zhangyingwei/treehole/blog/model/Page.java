package com.zhangyingwei.treehole.blog.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午2:54
 * @desc: 打开博客的时候实际显示的对象
 */
public class Page {
    /**
     * 页面内容的源码。
     */
    private String content;

    /**
     * 页面的标题
     */
    private String title;

    /**
     * 页面摘要的源码
     */
    private String excerpt;

    /**
     * 帖子以斜线打头的相对路径，例子： /2008/12/14/my-post.html。
     */
    private String url;

    /**
     * 帖子的日期。
     * 日期的可以在帖子的头信息中通过用以下格式 YYYY-MM-DD HH:MM:SS (假设是 UTC),
     * 或者 YYYY-MM-DD HH:MM:SS +/-TTTT ( 用于声明不同于 UTC 的时区， 比如 2008-12-14 10:30:00 +0900) 来显示声明其他 日期/时间 的方式被改写，
     */
    private String date;

    /**
     * 帖子的唯一标识码（在RSS源里非常有用），比如 /2008/12/14/my-post
     * 这里使用数据库里的唯一标识
     * 使用 UUID 应该可以吧
     */
    private String id;

    /**
     * 这个帖子所属的 Categories。
     */
    private List categories;

    /**
     * 这个 Post 所属的所有 tags。
     */
    private List tags;

    /**
     * Post 或者 Page 的源文件地址,
     * 在这里就是前边补齐域名或者ip信息的地址
     */
    private String path;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public List getTags() {
        return tags;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Page{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", categories=" + categories +
                ", tags=" + tags +
                ", path='" + path + '\'' +
                '}';
    }

    public Page bulid(){
        return this;
    }
}
