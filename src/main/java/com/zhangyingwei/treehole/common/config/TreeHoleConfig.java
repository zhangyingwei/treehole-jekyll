package com.zhangyingwei.treehole.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangyw on 2017/5/11.
 */
@Configuration
public class TreeHoleConfig {

    @Value("${treehole.theme}")
    private String theme = "deafult";
    @Value("${treehole.mainpage.page}")
    private Boolean page = true;
    @Value("${app.dev}")
    private Boolean env = false;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Boolean getPage() {
        return page;
    }

    public void setPage(Boolean page) {
        this.page = page;
    }

    public Boolean getEnv() {
        return env;
    }

    public void setEnv(Boolean env) {
        this.env = env;
    }
}
