package com.zhangyingwei.treehole.common.config;

import com.zhangyingwei.treehole.common.function.*;
import org.beetl.core.Function;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author: zhangyw
 * @date: 2017/5/7
 * @time: 上午12:08
 * @desc:
 */

@Configuration
public class BeetlConf {
    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
        beetlGroupUtilConfiguration.setFunctions(new HashMap<String,Function>(){
            {
                put("yearFromString", new YearFromString());
                put("monthFromString", new MonthFromString());
                put("dayFromString", new DayFromString());
                put("date_to_xmlschema", new DateToXmlSchema());
                put("date_to_rfc822", new DateToRfc());
                put("date_to_string", new DateToString());
                put("date_to_long_string", new DateToLongString());
                put("xml_escape", new XmlEscape());
                put("cgi_escape", new CgiEscape());
                put("uri_escape", new UriEscape());
                put("number_of_words", new NumberOfWords());
                put("array_to_sentence_string", new ArrayToSentenceString());
                put("markdownify", new MarkDownify());
                put("replace", new Replace());
                put("limit", new Limit());
            }
        });
        //读取配置文件信息
//        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(Thread.currentThread().getContextClassLoader());
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}