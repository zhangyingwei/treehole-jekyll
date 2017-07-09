package com.zhangyingwei.treehole.common.utils;

import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/11.
 * yaml配置文件操作工具类
 */
public class YamlUtils {
    public static Map<String,Object> read(String path) throws FileNotFoundException {
        File file = new File(path);
        Map<String,Object> yaml = (Map<String, Object>) Yaml.load(file);
        return yaml;
    }
}