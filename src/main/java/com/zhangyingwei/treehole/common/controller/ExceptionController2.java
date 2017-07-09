package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhangyw on 2017/4/24.
 */

@Controller
@ControllerAdvice
public class ExceptionController2 {

    private Logger logger = LoggerFactory.getLogger(ExceptionController2.class);

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, TreeHoleException.class,NullPointerException.class})
    public Object bindExceptionHandler(Exception ex){
        String message = "";
        if(ex instanceof BindException){
            BindException bex = (BindException) ex;
            message = bex.getFieldError().getDefaultMessage();
        }else{
            message = ex.getMessage();
        }
        logger.info("hello exception: "+message);
//        ex.printStackTrace();
        return Ajax.error(message);
    }
}
