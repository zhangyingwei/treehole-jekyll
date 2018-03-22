package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.api.admin.service.ApiBlogInfoService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.annotation.Auth;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zhangyw on 2018/3/8.
 */
@RestController
@RequestMapping("/api/admin/settings")
@CrossOrigin(allowCredentials = "true",origins = "*")
@Auth
public class ApiSettingsController {
    private Logger logger = LoggerFactory.getLogger(ApiSettingsController.class);

    @Autowired
    private BlogManagerService blogManagerService;
    @Autowired
    private ApiBlogInfoService apiBlogInfoService;

    @GetMapping("/userinfo")
    public Map getUserInfo() throws TreeHoleApiException {
        try {
            BlogConf userinfo = this.blogManagerService.getBlogConf();
            return Ajax.success(userinfo);
        } catch (TreeHoleException e) {
            throw new TreeHoleApiException(e);
        }
    }

    @PostMapping("/userinfo")
    public Map updateUserInfo(BlogConf blogConf) throws TreeHoleApiException {
        if (blogConf.isUserInfoEmpty()) {
            return Ajax.success("信息不能为空");
        }
        this.apiBlogInfoService.updateBlogInfo(blogConf);
        return Ajax.success("success");
    }
}
