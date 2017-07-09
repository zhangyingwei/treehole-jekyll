package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleConfigUtils;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/6/7.
 */
@Controller
@RequestMapping
public class FeedController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TreeHoleConfig treeHoleConfig;
    @Autowired
    private BlogManagerService blogManagerService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/feed")
    @ResponseBody
    public void feed(HttpServletResponse response) throws TreeHoleException {
        try {
            String feed = TreeHoleUtils.createFeed(this.getBlogInfo(), this.getPosts(), this.getSiteConfig());
            response.setCharacterEncoding(TreeHoleEnum.FEED_ENCODING.getValue());
            PrintWriter writer = response.getWriter();
            writer.write(feed);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * rss请求重定向到feed请求
     * @return
     */
    @GetMapping("/rss")
    public String rss(){
        return "redirect:/feed";
    }

    /**
     * 获取主题配置文件中的信息
     * @return
     * @throws TreeHoleException
     */
    public Map<String,Object> getSiteConfig() throws TreeHoleException {
        Map<String, Object> siteConfig = new HashMap<String,Object>();
        try {
            siteConfig = TreeHoleConfigUtils.readThremeYmlConfig(treeHoleConfig);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("主题配置文件未找到");
        }
        return siteConfig;
    }

    /**
     * 获取站点信息
     * @return
     * @throws TreeHoleException
     */
    public BlogConf getBlogInfo() throws TreeHoleException {
        BlogConf blogInfo = this.blogManagerService.getBlogConf();
        return blogInfo;
    }

    /**
     * 获取文章信息
     * @return
     */
    public List<Article> getPosts() throws TreeHoleException {
        List<Article> posts = new ArrayList<Article>();
        posts = this.articleService.getPosts();
        return posts;
    }
}
