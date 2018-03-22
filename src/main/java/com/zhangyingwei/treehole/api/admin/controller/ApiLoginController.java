package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.service.AdminInitService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/3/15.
 */
@Controller
@Scope("prototype")
@RequestMapping()
//@CrossOrigin(allowCredentials="true",origins = "*")
public class ApiLoginController {

    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);
    @Autowired
    private AdminInitService adminInitService;

    @PostMapping("/api/admin/login")
    @ResponseBody
    public Map loginIndex(HttpSession session, @Valid User user) throws TreeHoleApiException {
        try {
            this.adminInitService.login(user);
            String tocken = TreeHoleUtils.markAsLogin(session, user);
            return Ajax.success("登录成功", tocken);
        } catch (TreeHoleException e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }

    @GetMapping("/vue/admin/**")
    public String doEmpty(){
        return Pages.ADMIN_INDEX;
    }
}
