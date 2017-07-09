package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/31.
 */
public class TreeHoleConfigUtils {

    /**
     * 读取主题配置yml文件
     * @param treeHoleConfig
     * @return
     */
    public static Map<String,Object> readThremeYmlConfig(TreeHoleConfig treeHoleConfig) throws FileNotFoundException {
        String path = TreeHoleEnum.RES_BASEPATH.getValue() + "templates/" + TreeHoleEnum.THEME_BASEPATH.getValue() + "/" + treeHoleConfig.getTheme() + "/" + TreeHoleEnum.THEME_CONFIG.getValue();
        return YamlUtils.read(path);
    }
}
