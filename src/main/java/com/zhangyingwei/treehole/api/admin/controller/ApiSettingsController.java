package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhangyw on 2018/3/8.
 */
@RestController
@RequestMapping("/api/admin/settings")
@CrossOrigin
public class ApiSettingsController {
    private Logger logger = LoggerFactory.getLogger(ApiSettingsController.class);

    @Autowired
    private BlogManagerService blogManagerService;

    @GetMapping("/userinfo")
    public Map getUserInfo() throws TreeHoleApiException {
        try {
            BlogConf userinfo = this.blogManagerService.getBlogConf();
            return Ajax.success(userinfo);
        } catch (TreeHoleException e) {
            throw new TreeHoleApiException(e);
        }
    }
}
