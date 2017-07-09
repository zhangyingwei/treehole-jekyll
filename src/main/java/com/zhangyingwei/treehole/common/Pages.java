package com.zhangyingwei.treehole.common;

import com.zhangyingwei.treehole.common.config.TreeHoleConfig;

import java.util.Map;

/**
 * Created by zhangyw on 2017/4/21.
 * 路由导航信息
 */
public class Pages {
    public static final String INSTALL = "install";
    public static final String ADMIN_INDEX = "admin/index";
    public static final String ADMIN_LOGIN = "admin/login";
    public static final String ADMIN_BLOG_BASIC = "admin/blog/basic-index";
    public static final String ADMIN_BLOG_STATISTIC = "admin/blog/statistic-index";
    public static final String ADMIN_BLOG_SETTINGS = "admin/blog/settings-index";
    public static final String ADMIN_ARTICLES_PUBLISH = "admin/article/publish-index";
    public static final String ADMIN_ARTICLES_EDIT = "admin/article/edit-index";
    public static final String ADMIN_ARTICLES_HISTORY = "admin/article/history-index";
    public static final String ADMIN_KIND = "admin/article/kind-index";
    public static final String ADMIN_FILEMANAGE = "admin/file/file-index";
    public static final String BLOG_THEME_INDEX = "index";
    public static final String BLOG_THEME_ARTICLE = "_layouts/post";
    public static final String BLOG_THEME_CATEGORIES = "categories/index";
    public static final String ADMIN_COMMONTS = "admin/article/commont-index";

    public static String blog(TreeHoleConfig config,String themePage){
        return TreeHoleEnum.THEME_BASEPATH.getValue() + "/" + config.getTheme() + "/" + themePage;
    }
}