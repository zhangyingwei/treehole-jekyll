package com.zhangyingwei.treehole.common.interceptor;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.annotation.Auth;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by zhangyw on 2017/7/14.
 * 登录拦截
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (TreeHoleUtils.isInstalled()) {
            if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
                return this.doHandle(request, response, handler);
            }
        } else {
            return this.doHandle(request, response, handler);
        }
        return true;
    }

    private boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String tocken = request.getHeader(TreeHoleEnum.TOCKEN_HEADER_KEY.getValue());
        if(uri.startsWith("/install")){
            if(TreeHoleUtils.isInstalled()){
                logger.info("已经安装，不能再次安装");
                response.sendRedirect("/admin");
                return false;
            }
        }else{
//            TreeHoleUtils.login(session);//test
            if(!TreeHoleUtils.isInstalled()){
                logger.info("没有安装");
                response.sendRedirect("/install");
                return false;
            }else{
                if (session.getAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue()) == null) {
                    session.setAttribute(TreeHoleEnum.STATE_DIC_KEY.getValue(),TreeHoleUtils.getGolbleStateDic());
                }
                if (!TreeHoleUtils.isLogin(session, tocken)
                        && !uri.contains("/admin/login")
                        && !"/admin/login".equals(uri)) {
                    session.setAttribute(TreeHoleEnum.STATE_URL_BEFORE.getValue(), uri);
                    response.sendRedirect("/admin/login");
//                    response.getWriter().append(Ajax.noLogin("please login...")).close();
                    return false;
                } else {
                    if (TreeHoleUtils.isLogin(session, tocken)) {
                        String fromUri = (String) session.getAttribute(TreeHoleEnum.STATE_URL_BEFORE.getValue());
                        if (StringUtils.isNotEmpty(fromUri)) {
                            response.sendRedirect(fromUri);
                        }
                        session.removeAttribute(TreeHoleEnum.STATE_URL_BEFORE.getValue());
                    }
                }
            }
        }
        return true;
    }
}
