package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.annotation.Auth;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.service.AdminInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/4/23
 * @time: 上午10:23
 * @desc: 登录
 */
@RestController
@Scope("prototype")
@RequestMapping("/admin")
@CrossOrigin
//@Auth
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AdminInitService adminInitService;

    @GetMapping
    @TreeHoleAtcion("打开管理端首页")
    public String index(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
        if (TreeHoleUtils.isLogin(session)) {
            session.setAttribute(TreeHoleEnum.LOGIN_MENU_KEY.getValue(),TreeHoleUtils.getMenu());
            return Pages.ADMIN_INDEX;
        } else {
            Cookie[] cookie = request.getCookies();
            return "redirect:/admin/login";
        }
    }

    @PostMapping("/login")
    @TreeHoleAtcion("登录")
    public Map login(HttpSession session, @Valid User user) throws TreeHoleException {
        logger.info(user.toString());
        //记录登录用户信息到session
        try {
            user.setPassword(TreeHoleUtils.encodePasswordByMD5(user.getPassword()));
            logger.info(user.toString());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("密码加密错误");
        }
        Boolean login = this.adminInitService.login(user);
        if(login){
            TreeHoleUtils.markAsLogin(session,user);
            return Ajax.success("login success");
        }
        return Ajax.error("login error");
    }

    @GetMapping("/logout")
    @TreeHoleAtcion("登出")
    public String logout(HttpSession session){
        TreeHoleUtils.logout(session);
        logger.info("logout");
        return "redirect:/admin";
    }
}
