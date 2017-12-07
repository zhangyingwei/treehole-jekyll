package com.zhangyingwei.treehole.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangyw on 2017/4/21.
 * 系统公用枚举参数
 */
public enum TreeHoleEnum {
    RES_BASEPATH("resources/"),
    RES_BASEPATH_ENV("src/main/resources/"),
    INSTALL_LOCK("install.lock"),//安装检测文件
    CONF_INSTALL_SQL("install.sql"),
    DB_MYSQL_CLASS("com.mysql.jdbc.Driver"),
    DB_SQLITE_CLASS("org.sqlite.JDBC"),
    LOGIN_USER_KEY("login_user"),
    LOGIN_MENU_KEY("menus"),
    THEME_BASEPATH("theme"),
    STATE_DIC_KEY("stateDic"),
    STATE_URL_BEFORE("from_uri"),
    UPLOAD_FILE_BASEPATH("upload/"),
    RES_IMG_DEFAULT("static/images/default.jpg"),
    THEME_CONFIG("_config.yml"),
    FEED_ENCODING("UTF-8");

    private String value;

    @Value("${app.env}")
    private Boolean env = true;

    TreeHoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getResValue(Boolean env){
        if (env && value.contains("resources/")){
            value = RES_BASEPATH_ENV.value;
        }
        return value;
    }
}
