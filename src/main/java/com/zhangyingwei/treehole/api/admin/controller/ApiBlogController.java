package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.annotation.Auth;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/3/7
 * @time: 下午8:16
 * @desc:
 */
@RestController
@RequestMapping("/api/admin/blog")
@CrossOrigin(allowCredentials="true",origins = "*")
@Auth
public class ApiBlogController {

    @Autowired
    private BlogManagerService blogManagerService;

    @GetMapping("/basic")
    public Map getbasicInfo() throws TreeHoleException {
        BlogConf conf = this.blogManagerService.getBlogConf();
        return Ajax.success(conf);
    }

    @GetMapping("/install")
    public Map getInstallInfo() throws TreeHoleException {
        InstallConf install = this.blogManagerService.getInstallinfo();
        return Ajax.success(install);
    }
}