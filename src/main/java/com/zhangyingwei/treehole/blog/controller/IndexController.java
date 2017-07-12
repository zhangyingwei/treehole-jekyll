package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.blog.model.Page;
import com.zhangyingwei.treehole.blog.model.Post;
import com.zhangyingwei.treehole.blog.model.Paginator;
import com.zhangyingwei.treehole.blog.model.Site;
import com.zhangyingwei.treehole.blog.service.IPageService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import com.zhangyingwei.treehole.common.utils.TreeHoleConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/4/21.
 */
@Controller
@RequestMapping
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TreeHoleConfig treeHoleConfig;
    @Autowired
    private BlogManagerService blogManagerService;
    @Autowired
    private IPageService pageService;

    @GetMapping
    public String index(){
        return "redirect:/articles/pages/1";
    }

    @GetMapping("/articles/pages/{page}")
    @TreeHoleAtcion("打开博客首页")
    public String indexArticles(Map<String, Object> model,@PathVariable("page")Integer page) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Paginator paginator = new Paginator().setPage(page);
        List<Post> posts = this.pageService.listPostsOrderByDate(paginator);
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/articles/pages/"+page);
        pageInfo.setDescription(site.getConfig("desc"));
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("posts", posts);
        model.put("paginator", paginator.bulid());
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX);
    }

    @GetMapping("/articles/{id}")
    @TreeHoleAtcion("打开文章")
    public String getArticle(Map<String,Object> model , @PathVariable("id") Integer id) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/articles/"+id);
        pageInfo.setDescription(site.getConfig("desc"));
        //查询文章信息
        Post post = this.pageService.getPageById(id);
        //加载配置信息
        model.put("site", site.bulid());
        //加载page信息
        model.put("page", pageInfo);
        //加载page信息
        model.put("post", post);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/articles/alias/{alias}")
    @TreeHoleAtcion("通过别名打开文章")
    public String getArticle(Map<String,Object> model , @PathVariable("alias") String alias) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/articles/alias/"+alias);
        pageInfo.setDescription(site.getConfig("desc"));
        //查询文章信息
        Post post = this.pageService.getPageBySubpath(alias);
        //加载配置信息
        model.put("site", site.bulid());
        //加载page信息
        model.put("page", post);
        //加载page信息
        model.put("post", post);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/categories/")
    @TreeHoleAtcion("按分类打开文章")
    public String categories(Map<String, Object> model) throws TreeHoleException {
        Site site = this.getSiteConfig();
        List<Post> posts = this.pageService.listPostOrderByCategorie();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/categories/");
        pageInfo.setDescription(site.getConfig("desc"));
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("posts", posts);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_CATEGORIES);
    }

    @GetMapping("/categories/{categories}")
    @TreeHoleAtcion("按分类打开文章")
    public String getPostByCategories(Map<String, Object> model, @PathVariable("categories") String categories) throws TreeHoleException {
        Site site = this.getSiteConfig();
        List<Post> posts = this.pageService.listPostsByCategories(categories);
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/categories/"+categories);
        pageInfo.setDescription(site.getConfig("desc"));
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("posts", posts);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_CATEGORIES);
    }

    /**
     * 获取主题配置文件中的信息
     * @return
     * @throws TreeHoleException
     */
    public Site getSiteConfig() throws TreeHoleException {
        Site site = new Site();
        try {
            site.setTime(DateUtils.now());
            site.setTheme(treeHoleConfig.getTheme());
            //从 yml 配置文件中读取配置文件
            Map siteConfig = TreeHoleConfigUtils.readThremeYmlConfig(treeHoleConfig);
            site.setConfigs(siteConfig);
            //读取界面中配置的博客信息
            Map blogConf = this.blogManagerService.getBlogConf().bulid();
            site.setConfigs(blogConf);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("主题配置文件未找到");
        }
        return site;
    }

}