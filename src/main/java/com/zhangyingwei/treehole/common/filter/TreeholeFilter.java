package com.zhangyingwei.treehole.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(TreeholeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String path = req.getRequestURI();
        if (path.startsWith("/vue/")) {
            filterChain.doFilter(req, resp);
        } else if (path.startsWith("/admin") && !path.startsWith("/admin/files")) {
            String toPath = "/vue" + path;
            logger.info("url [{}] redirect to [{}]", path, toPath);
            resp.sendRedirect(toPath);
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
