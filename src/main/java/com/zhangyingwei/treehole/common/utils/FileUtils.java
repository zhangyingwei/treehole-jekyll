package com.zhangyingwei.treehole.common.utils;


import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by zhangyw on 2017/4/21.
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean exists(String path){
        if(StringUtils.isEmpty(path)){
            return false;
        }
        return new File(path).exists();
    }

    public static void newFile(String path) throws IOException {
        new File(path).createNewFile();
    }

    public static void formatFileType(String path,String oldSuffix,String newSuffix){
        File file = new File(path);
        String[] listFile = file.list();
        for (String name : listFile) {
            File temp = new File(path+"/"+name);
            if(temp.isFile()){
                if(temp.getName().endsWith(oldSuffix)){
                    temp.renameTo(new File(path + "/" + name.replace(oldSuffix, newSuffix)));
                }
            }else{
                formatFileType(path + "/" + name,oldSuffix,newSuffix);
            }
        }
    }

    public static void main(String[] args) {
//        FileUtils.formatFileType("templates/theme/default", ".ejs", ".html");
        FileUtils.formatFileType(TreeHoleEnum.RES_BASEPATH.getValue()+"templates/theme/default", ".ejs", ".html");
    }

    /**
     * 保存文件
     * @param filePath
     * @param bytes
     */
    public static void saveFile(String filePath, byte[] bytes) throws TreeHoleException {
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                throw new TreeHoleException("创建文件错误");
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("保存文件内容错误");
        }
    }

    /**
     * 创建文件夹
     * @param path
     */
    public static void createDir(String path) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }else if(!file.isDirectory()){
            file.mkdirs();
        }
    }

    /**
     * 删除文件
     * @param path
     */
    public static void deleteFile(String path) {
        new File(path).deleteOnExit();
    }
}
