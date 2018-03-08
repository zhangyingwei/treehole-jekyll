package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.admin.service.LinkService;
import com.zhangyingwei.treehole.blog.model.Site;
import com.zhangyingwei.treehole.blog.service.IPageService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.exception.TreeHoleOutOfPageException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * Created by zhangyw on 2017/4/24.
 */

@Controller
@ControllerAdvice
public class ExceptionController2 {
    private Logger logger = LoggerFactory.getLogger(ExceptionController2.class);
    @Autowired
    private TreeHoleConfig treeHoleConfig;
    @Autowired
    private BlogManagerService blogManagerService;
    @Autowired
    private IPageService pageService;
    @Autowired
    private LinkService linkService;

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, TreeHoleException.class})
    public Object bindExceptionHandler(Exception ex) throws TreeHoleOutOfPageException {
        String message = "";
        if(ex instanceof BindException){
            BindException bex = (BindException) ex;
            message = bex.getFieldError().getDefaultMessage();
        } else{
            message = ex.getMessage();
        }
        logger.info("hello exception: "+message);
        return Ajax.error(message);
    }

    @ResponseBody
    @ExceptionHandler(value = {TreeHoleApiException.class})
    public Object bindApiExceptionHandler(Exception ex) {
        String message = "";
        if(ex instanceof BindException){
            BindException bex = (BindException) ex;
            message = bex.getFieldError().getDefaultMessage();
        } else{
            message = ex.getMessage();
        }
        logger.info("hello exception: "+message);
        return Ajax.error(message);
    }

    /**
     * 把所有空指针定位到 404 错误页面
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {NullPointerException.class,TreeHoleOutOfPageException.class})
    public Object bindNullExceptionHandler(Exception ex) throws TreeHoleException {
        Site site = TreeHoleUtils.getSiteConfig(
                this.treeHoleConfig,
                this.blogManagerService,
                this.linkService,
                this.pageService
        );
        String message = ex.getMessage();
        logger.info("hello exception: "+message);
        ModelAndView view = new ModelAndView();
        view.addObject("site", site);
        view.setViewName(Pages.ERROR);
        return view;
    }
}
