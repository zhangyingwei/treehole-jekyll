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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: zhangyw
 * @date: 2017/5/27
 * @time: 下午11:05
 * @desc: 素材管理
 */
@Service
public class FileManagerService implements IFileManagerService {
    private Logger logger = LoggerFactory.getLogger(FileManagerService.class);
    @Autowired
    private FileResDao fileResDao;

    /**
     * 1 保存文件到硬盘
     * 2 保存文件信息到数据库
     * @param file
     * @throws TreeHoleException
     */
    @Override
    public void saveFile(MultipartFile file) throws TreeHoleException {
        try {
            String fileName = file.getOriginalFilename();
            boolean exits = TreeHoleUtils.fileExits(file.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
            if(exits){
                fileName = StringUtils.join(uuid,"-",fileName);
                logger.info(StringUtils.join("存在同名文件，重命名为：", fileName));
            }
            //保存文件到硬盘
            TreeHoleUtils.saveUploadFile(fileName,file.getBytes());
            //保存文件信息到数据库
            this.saveFileInfo(new FileRes(fileName,TreeHoleUtils.getFilePath(fileName),uuid,file.getContentType()));
            logger.info(StringUtils.join("upload file:",fileName));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("保存文件错误");
        }
    }

    /**
     * 保存文件信息到数据库
     * @param fileRes
     * @throws Exception
     */
    @Override
    public void saveFileInfo(FileRes fileRes) throws Exception {
        this.fileResDao.insert(fileRes);
    }

    /**
     * 根据文件别名获取文件信息
     * @param alias
     * @return File
     */
    @Override
    public FileRes findFileByAlias(String alias) throws Exception {
        FileRes fileRes = this.fileResDao.selectByAlias(alias);
        if(fileRes == null){
            throw new FileNotFoundException("资源未找到");
        }
        return fileRes;
    }

    /**
     * 查询所有素材文件
     */
    @Override
    public List<FileRes> findFiles() throws TreeHoleException{
        try {
            return this.fileResDao.selectFiles();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("查询文件列表错误");
        }
    }

    /**
     * 从数据库删除文件信息
     * @param id
     */
    @Override
    public void deleteFileInfo(String id) throws TreeHoleException {
        try {
            this.fileResDao.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("删除文件信息错误");
        }
    }

    /**
     * 从硬盘中删除文件
     * @param id
     */
    @Override
    public void deleteFile(String id){
        FileRes fileRes = this.fileResDao.selectById(id);
        if(fileRes!=null){
            TreeHoleUtils.deleteUploadFile(fileRes.getPath());
        }
    }

    @Override
    public Integer getFileCount() throws TreeHoleException {
        try {
            return this.fileResDao.selectFileCount();
        } catch (Exception e) {
            throw new TreeHoleException("获取素材总数错误");
        }
    }
}
