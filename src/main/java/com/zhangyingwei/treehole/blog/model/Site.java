package com.zhangyingwei.treehole.blog.model;

import com.zhangyingwei.treehole.admin.model.Link;
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
     * 当前主题信息
     */
    private String theme;

    /**
     * 所有的通过命令行和 _config.yml 设置的变量都会存到这个 site 里面。 举例来说，如果你设置了 url: http://mysite.com 在你的配置文件中，那么在你的 Posts 和 Pages 里面，这个变量就被存储在了 site.url。
     */
    private Map configs;

    /**
     * 友链
     */
    private List<Link> links;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map getConfigs() {
        return configs;
    }

    public String getConfig(String key){
        return this.configs.get(key)+"";
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setConfigs(Map configs) {
        this.configs = this.configs == null ? new HashMap() : this.configs;
        this.configs.putAll(configs);
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * 生成最终结果
     * @return
     */
    public Map bulid(){
        Map map = new HashMap();
        map.put("time", this.getTime());
        map.put("theme", this.getTheme());
        map.put("links", this.getLinks());
        map.put("baseurl", this.getConfig("url") + "/theme/" + this.getTheme() + "/");
        map.putAll(this.getConfigs());
        return map;
    }
}
