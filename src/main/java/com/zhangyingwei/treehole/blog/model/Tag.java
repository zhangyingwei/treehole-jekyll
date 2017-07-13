package com.zhangyingwei.treehole.blog.model;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/13.
 */
public class Tag {
    private String tag;
    private List<Post> posts;

    public Tag(String tag, List<Post> posts) {
        this.tag = tag;
        this.posts = posts;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                ", posts=" + posts +
                '}';
    }
}
