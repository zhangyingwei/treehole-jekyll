package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.FileResDao;
import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * @author: zhangyw
 * @date: 2017/5/27
 * @time: 下午11:05
 * @desc: 素材管理
 */
public interface IFileManagerService {
    void saveFile(MultipartFile file) throws TreeHoleException;
    /**
     * 保存文件信息到数据库
     * @param fileRes
     * @throws Exception
     */
    void saveFileInfo(FileRes fileRes) throws Exception;
    /**
     * 根据文件别名获取文件信息
     * @param alias
     * @return File
     */
    FileRes findFileByAlias(String alias) throws Exception;

    /**
     * 查询所有素材文件
     */
    List<FileRes> findFiles() throws TreeHoleException;
    /**
     * 从数据库删除文件信息
     * @param id
     */
    void deleteFileInfo(String id) throws TreeHoleException;

    /**
     * 从硬盘中删除文件
     * @param id
     */
    void deleteFile(String id);

    Integer getFileCount() throws TreeHoleException;
}
