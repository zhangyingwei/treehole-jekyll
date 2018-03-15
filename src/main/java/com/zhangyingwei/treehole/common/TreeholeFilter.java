package com.zhangyingwei.treehole.common;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangyw on 2018/3/15.
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = {
        "/vue/*",
        "/admin/*"
},filterName = "treeholeFilter")
public class TreeholeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getRequestURI();
        if (path.startsWith("/vue/")) {
            System.out.println(path);
            filterChain.doFilter(req, resp);
        } else if (path.startsWith("/admin")) {
            resp.sendRedirect("/vue" + path);
        } else {
            filterChain.doFilter(req, resp);
        }
//        System.out.println(path);
//        resp.sendRedirect(path);
//        RequestDispatcher requestDesp = req.getRequestDispatcher(path);
//        requestDesp.forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
