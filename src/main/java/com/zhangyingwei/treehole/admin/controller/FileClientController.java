package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.admin.service.FileManagerService;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by zhangyw on 2017/6/14.
 */
@Controller
@RequestMapping("/files")
public class FileClientController {
    private Logger logger = Logger.getLogger(FileClientController.class);
    @Autowired
    private FileManagerService fileManagerService;
    @GetMapping("/{fileAlias}")
    @TreeHoleAtcion("获取文件")
    public void downLoad(@PathVariable("fileAlias") String fileAlias, HttpServletResponse response){
        FileRes fileRes = null;
        File file = null;
        try {
            fileRes = this.fileManagerService.findFileByAlias(fileAlias);
            file = new File(fileRes.getPath());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", fileRes.getContentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        } catch (Exception e) {
            file = new File(TreeHoleEnum.RES_IMG_DEFAULT.getValue());
            logger.info("file not found show the defalue image");
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info(StringUtils.join("download file:",file.getName()));
    }
}
