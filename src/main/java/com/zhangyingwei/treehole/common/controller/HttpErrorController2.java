package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyw on 2017/4/24.
 */
@Controller
@RequestMapping
public class HttpErrorController2 implements ErrorController {

    private final static String ERROR_PATH = Pages.ERROR;

    /**
     * Supports the HTML Error View
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public Object errorHtml(HttpServletRequest request) {
        return "error2";
    }

    /**
     * Supports other formats like JSON, XML
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        String message = "资源未找到";
        return Ajax.error(message);
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
