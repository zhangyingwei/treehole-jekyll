package com.zhangyingwei.treehole.common.controller;

import com.zhangyingwei.treehole.admin.log.LogHandler;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.ApplicationContextProvider;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhangyw on 2017/5/8.
 */
@Aspect
@Configuration
@Scope("prototype")
public class InterCeptorController2 {
    private Logger logger = LoggerFactory.getLogger(InterCeptorController2.class);

    private LogHandler logHandler = ApplicationContextProvider.getBean("logHandler", LogHandler.class);

    @Pointcut("execution(public * com.zhangyingwei.treehole..*.controller.*.*(..))")
    public void controllerMethodPointcut(){}

    @Before("execution(public * com.zhangyingwei.treehole..*.controller.*Controller.*(..))")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("method:" + joinPoint.getThis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if(uri.startsWith("/install")) {
            if(TreeHoleUtils.isInstalled()){
                logger.info("已经安装，不能再次安装");
                response.sendRedirect("/admin");
            }
        }else {
            if(!TreeHoleUtils.isInstalled()){
                logger.info("没有安装");
                response.sendRedirect("/install");
            }else{
                if (session.getAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue()) == null) {
                    session.setAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue(),TreeHoleUtils.getGolbleStateDic());
                }
                if(uri.startsWith("/admin") && !TreeHoleUtils.isLogin(session)){
                    response.sendRedirect("/admin/login");
                }
            }
        }
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
    }

    @After("controllerMethodPointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        // 处理完请求，返回内容
//        logger.info("method:" + joinPoint.getThis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        LogModel log = new LogModel();
        log.setIp(getIpAddress(request));
        log.setUrl(request.getRequestURL().toString());
        log.setUri(uri);
        log.setAgent(request.getHeader("User-Agent"));
        log.setReferer(request.getHeader("Referer"));
        log.setTimestamp(DateUtils.getTimeStamp());
        log.setReqType(request.getMethod());
        log.setAction(this.getAction(joinPoint));
        this.logHandler.produceLog(log);
    }

    /**
     * 获取用户真实ip
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取用户行为
     * @param joinPoint
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private String getAction(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint)joinPoint;
        Annotation[] aaa = methodPoint.getClass().getAnnotations();
        Field[] fs = methodPoint.getClass().getDeclaredFields();
        Field proxy = methodPoint.getClass().getDeclaredField("methodInvocation");
        proxy.setAccessible(true);
        ReflectiveMethodInvocation j = (ReflectiveMethodInvocation) proxy.get(methodPoint);
        Method method = j.getMethod();
        TreeHoleAtcion annotation = method.getAnnotation(TreeHoleAtcion.class);
        if(annotation!=null){
            return annotation.value();
        }else{
            return "未识别";
        }
    }
}
