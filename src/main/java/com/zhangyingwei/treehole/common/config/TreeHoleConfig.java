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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
