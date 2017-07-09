package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by zhangyw on 2017/5/8.
 * 博客信息管理控制器
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogManageController {
    private Logger logger = LoggerFactory.getLogger(BlogManageController.class);

    @Autowired
    private BlogManagerService blogManagerService;

    @GetMapping("/basic")
    @TreeHoleAtcion("打开基础信息页面")
    public String indexBasicInfoManage(Map<String, Object> model) throws TreeHoleException {
        BlogConf blogConf = this.blogManagerService.getBlogConf();
        InstallConf installConf = this.blogManagerService.getInstallinfo();
        model.put("bloginfo", blogConf);
        model.put("installinfo", installConf);
        return Pages.ADMIN_BLOG_BASIC;
    }

    @PutMapping("/basic")
    @ResponseBody
    @TreeHoleAtcion("修改博客基础信息")
    public Map<String, Object> editBlogInfo(@Valid BlogConf blogConf) throws TreeHoleException{
        this.blogManagerService.updateBlogInfo(blogConf);
        return Ajax.success("修改基础信息成功");
    }


}
