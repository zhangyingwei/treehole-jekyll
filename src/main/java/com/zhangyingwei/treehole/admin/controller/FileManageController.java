package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.admin.service.FileManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/5/27
 * @time: 下午10:15
 * @desc:
 */
@Controller
@RequestMapping("/admin/files")
public class FileManageController {
    private Logger logger = Logger.getLogger(FileManageController.class);
    @Autowired
    private FileManagerService fileManagerService;
    @GetMapping
    @TreeHoleAtcion("打开附件管理页面")
    public String fileManageIndex(){
        return Pages.ADMIN_FILEMANAGE;
    }

    @PostMapping("/upload")
    @ResponseBody
    @TreeHoleAtcion("上传文件")
    public Map<String, Object> upload(MultipartFile file) throws TreeHoleException {
        this.fileManagerService.saveFile(file);
        return Ajax.success("success");
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    @TreeHoleAtcion("删除文件")
    public Map<String,Object> delete(@PathVariable("id") String id) throws TreeHoleException {
        this.fileManagerService.deleteFile(id);
        this.fileManagerService.deleteFileInfo(id);
        return Ajax.success("删除素材成功");
    }

    @GetMapping("/list")
    @ResponseBody
    @TreeHoleAtcion("查询文件列表")
    public Map<String, Object> listFiles() throws TreeHoleException {
        List<FileRes> files = this.fileManagerService.findFiles();
        return Ajax.success(files);
    }

    @GetMapping("/count")
    @ResponseBody
    @TreeHoleAtcion("获取素材总数")
    public Map<String,Object> getFilesCount() throws TreeHoleException{
        Integer count = this.fileManagerService.getFileCount();
        return Ajax.success(count);
    }
}
