package com.zhangyingwei.treehole.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhangyw on 2017/4/24.
 * properties utils
 */
public class PropertiesUtils {

    public static void update(String path, String key, String value) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        properties.put(key, value);
        properties.store(new FileOutputStream(path),"");
    }

    public static void createProperties(String filePath,String commones) throws IOException {
        Properties properties = new Properties();
        properties.store(new FileOutputStream(filePath),commones);
    }
}
