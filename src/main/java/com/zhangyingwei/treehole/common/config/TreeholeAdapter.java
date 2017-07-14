package com.zhangyingwei.treehole.common.config;

import com.zhangyingwei.treehole.common.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by zhangyw on 2017/7/14.
 */

@ComponentScan(basePackages = "com.zhangyingwei.treehole")
@PropertySource(value = "classpath:application.properties",ignoreResourceNotFound = true,encoding = "UTF-8")
@Configuration
public class TreeholeAdapter extends WebMvcConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(TreeholeAdapter.class);

    @Autowired

    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 资源处理器
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("addResourceHandlers");
        registry.addResourceHandler("/").addResourceLocations("classpath:/**");
        super.addResourceHandlers(registry);
    }
}
