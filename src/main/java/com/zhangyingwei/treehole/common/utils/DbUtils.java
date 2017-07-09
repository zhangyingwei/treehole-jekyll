package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.install.model.DbConf;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhangyw on 2017/4/25.
 * database utils
 * 数据库工具类
 */
public class DbUtils {
    private static Logger logger = LoggerFactory.getLogger(DbUtils.class);

    /**
     * check whether the database is available mysql
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static boolean mysqlValid(String url, String username, String password) {
        Connection connection = null;
        try {
            Class.forName(TreeHoleEnum.DB_MYSQL_CLASS.getValue());
            connection = DriverManager.getConnection(url, username, password);
            return connection != null && connection.isValid(1000);
        } catch (ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * check whether the database is avaliable sqlite
     * @param url
     * @return
     */
    public static Boolean sqliteValid(String url) {
        Connection connection = null;
        try {
            Class.forName(TreeHoleEnum.DB_SQLITE_CLASS.getValue());
            connection = DriverManager.getConnection(url);
            return connection != null && connection.isValid(1000);
        } catch (ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 执行sql语句
     *
     * @param sql
     */
    public static void execute(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    /**
     * 根据传入的 dbconf 创建 connection
     * 如果只有 url 默认数据库为 sqlite
     * 如果 url username password 都有 默认使用mysql
     * @param dbConf
     * @return
     */
    public static Connection getConnection(DbConf dbConf){
        if (StringUtils.isEmpty(dbConf.getUsername()) && StringUtils.isEmpty(dbConf.getPassword())) {
            return getConnection(TreeHoleEnum.DB_SQLITE_CLASS, dbConf.getUrl());
        } else if (StringUtils.isNotEmpty(dbConf.getUrl()) && StringUtils.isNotEmpty(dbConf.getUsername()) && StringUtils.isNotEmpty(dbConf.getPassword())) {
            return getConnection(TreeHoleEnum.DB_MYSQL_CLASS, dbConf.getUrl(), dbConf.getUsername(), dbConf.getPassword());
        }
        return null;
    }


    /**
     * 创建 connection 根据传入的 枚举 类型创建相应的 数据库连接
     * @param DB_CLASS
     * @param url
     * @return
     */
    private static Connection getConnection(Enum DB_CLASS, String url) {
        Connection connection = null;
        try {
            if (TreeHoleEnum.DB_SQLITE_CLASS.equals(DB_CLASS)) {
                Class.forName(TreeHoleEnum.DB_SQLITE_CLASS.getValue());
            }else if(TreeHoleEnum.DB_MYSQL_CLASS.equals(DB_CLASS)){
                Class.forName(TreeHoleEnum.DB_MYSQL_CLASS.getValue());
            }
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage(),e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(),e);
        }
        return connection;
    }

    /**
     * 根据传入的枚举类型创建相应的数据库连接
     * @param DB_CLASS
     * @param url
     * @param username
     * @param password
     * @return
     */
    private static Connection getConnection(Enum DB_CLASS,String url, String username, String password) {
        Connection connection = null;
        try {
            if (TreeHoleEnum.DB_MYSQL_CLASS.equals(DB_CLASS)) {
                Class.forName(TreeHoleEnum.DB_MYSQL_CLASS.getValue());
            }else if(TreeHoleEnum.DB_SQLITE_CLASS.equals(DB_CLASS)){
                Class.forName(TreeHoleEnum.DB_SQLITE_CLASS.getValue());
            }
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(), e);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage(), e);
        }
        return connection;
    }
}
