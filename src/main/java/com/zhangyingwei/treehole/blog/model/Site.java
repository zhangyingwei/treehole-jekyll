package com.zhangyingwei.treehole.blog.model;

import com.zhangyingwei.treehole.common.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午2:15
 * @desc: 来自_config.yml文件，全站范围的信息 +配置。详细的信息请参考下文
 */
public class Site {
    /**
     * 当前时间,打开页面当前时间
     */
    private String time;

    /**
     * 所有 Pages 的清单。
     */
    private List pages;

    /**
     * 一个按照时间倒叙的所有 Posts 的清单。
     */
    private List<Post> posts;

    /**
     * 如果当前被处理的页面是一个 Post，这个变量就会包含最多10个相关的 Post。默认的情况下， 相关性是低质量的，但是能被很快的计算出来。
     */
    private List<Post> relatedPosts;

    /**
     * 所有的在 CATEGORY 类别下的帖子。
     */
    private List categories;

    /**
     * 所有的在 TAG 标签下的帖子。
     */
    private List tags;

    /**
     * 所有的通过命令行和 _config.yml 设置的变量都会存到这个 site 里面。 举例来说，如果你设置了 url: http://mysite.com 在你的配置文件中，那么在你的 Posts 和 Pages 里面，这个变量就被存储在了 site.url。
     */
    private Map configs;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List getPages() {
        return pages;
    }

    public void setPages(List pages) {
        this.pages = pages;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }

    public List getRelatedPosts() {
        return relatedPosts;
    }

    public void setRelatedPosts(List relatedPosts) {
        this.relatedPosts = relatedPosts;
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

    public Map getConfigs() {
        return configs;
    }

    public void setConfigs(Map configs) {
        this.configs = configs;
    }

    /**
     * 生成最终结果
     * @return
     */
    public Map bulid(){
        Map map = new HashMap();
        map.put("time", DateUtils.now());
        map.put("pages", this.getPages());
        map.put("posts", this.getPosts());
        map.put("related_posts", this.getRelatedPosts());
        map.put("categories", this.getCategories());
        map.put("tags", this.getTags());
        map.putAll(this.getConfigs());
        return map;
    }
}
