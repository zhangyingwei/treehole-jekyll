package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.blog.model.BlogPage;
import com.zhangyingwei.treehole.blog.model.Page;
import com.zhangyingwei.treehole.blog.model.Paginator;
import com.zhangyingwei.treehole.blog.model.Site;
import com.zhangyingwei.treehole.blog.service.IPageService;
import com.zhangyingwei.treehole.blog.service.PageService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import com.zhangyingwei.treehole.common.utils.TreeHoleConfigUtils;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Paginator paginator = new Paginator().setPage(page);
        List<Page> pages = this.pageService.listPostsOrderByDate(paginator);
        List<Page> posts = this.pageService.listPostsOrderByDate();
        //加载site信息
        model.put("site", this.getSiteConfig(posts));
        model.put("pages", pages);
        model.put("posts", posts);
        //加载 content 信息
        model.put("content", "");
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载分页信息
        model.put("paginator", paginator);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX);
    }

    @GetMapping("/articles/{id}")
    @TreeHoleAtcion("打开文章")
    public String getArticle(Map<String,Object> model , @PathVariable("id") Integer id) throws TreeHoleException {
        //查询文章信息
        Page post = this.pageService.getPageById(id);
        //加载配置信息
        model.put("site", this.getSiteConfig(null));
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("page", post);
        //加载page信息
        model.put("post", post);
        //加载内容信息
        model.put("content", post.getContent());
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/articles/alias/{id}")
    @TreeHoleAtcion("通过别名打开文章")
    public String getArticle(Map<String,Object> model , @PathVariable("alias") String alias) throws TreeHoleException {
        //查询文章信息
        Page post = this.pageService.getPageBySubpath(alias);
        //加载配置信息
        model.put("site", this.getSiteConfig(null));
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("page", post);
        //加载page信息
        model.put("post", post);
        //加载内容信息
        model.put("content", post.getContent());
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/categories/")
    @TreeHoleAtcion("按分类打开文章")
    public String categories(Map<String, Object> model) throws TreeHoleException {
        List<Page> posts = this.pageService.listPostOrderByCategorie();
        //加载配置信息
        model.put("site", this.getSiteConfig(posts));
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("pages", posts);
        //加载文章信息
        model.put("posts", posts);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_CATEGORIES);
    }

    @GetMapping("/categories/{categories}")
    @TreeHoleAtcion("按分类打开文章")
    public String getPostByCategories(Map<String, Object> model, @PathVariable("categories") String categories) throws TreeHoleException {
        List<Page> posts = this.pageService.listPostsByCategories(categories);
        //加载配置信息
        model.put("site", this.getSiteConfig(posts));
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("pages", posts);
        //加载文章信息
        model.put("posts", posts);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_CATEGORIES);
    }

    /**
     * 获取主题配置文件中的信息
     * @return
     * @throws TreeHoleException
     */
    public Map<String,Object> getSiteConfig(List<Page> posts) throws TreeHoleException {
        Site site = new Site();
        try {
            site.setTime(DateUtils.now());
            site.setPosts(posts);
            site.setPages(posts);
            site.setRelatedPosts(new ArrayList());//这里是首页，所以不会有相关文章
            site.setCategories(new ArrayList()); //这里假设只有在分类页面才会有这个属性
            site.setTags(new ArrayList()); //这里假设在标签页面才有这个属性
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
        return site.bulid();
    }


    /**
     * 获取主题信息
     * @return
     */
    public String getThemeInfo() {
        return treeHoleConfig.getTheme();
    }

}