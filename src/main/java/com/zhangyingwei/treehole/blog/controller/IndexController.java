package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.blog.model.BlogPage;
import com.zhangyingwei.treehole.blog.model.Page;
import com.zhangyingwei.treehole.blog.model.Site;
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
    private ArticleService articleService;

    @GetMapping
    public String index(){
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    @TreeHoleAtcion("打开博客首页")
    public String indexArticles(Map<String, Object> model) throws TreeHoleException {
        //加载site信息
        model.put("site", this.getSiteConfig());
        //加载page信息
        model.put("pages", this.getPages());
        //加载博客信息
        model.put("blog", this.getBlogInfo());
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载文章信息
        model.put("posts", this.getPosts());
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_INDEX);
    }

    @GetMapping("/articles/{subpath}")
    @TreeHoleAtcion("打开文章")
    public String getArticle(Map<String,Object> model , @PathVariable("subpath") String subpath) throws TreeHoleException {
        //查询文章信息
        Article article = this.getArticleInfo(subpath);
        //加载配置信息
        model.put("site", this.getSiteConfig());
        //加载博客信息
        model.put("blog", this.getBlogInfo());
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("page", this.getPageInfo()
                .setTitle(article.getTitle())
                .setDate(article.getDate())
                .setCategories(article.getKind())
                .setContent(article.getArticleHtml())
                .setIntroduction(article.getIntroHtml())
                .setTags(article.getTags()==null?new String[0]:article.getTags().split(","))
                .setUseCommont(article.getUsecommont())
        );
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/categories/")
    @TreeHoleAtcion("按分类打开文章")
    public String categories(Map<String, Object> model) throws TreeHoleException {
        //加载配置信息
        model.put("site", this.getSiteConfig());
        //加载博客信息
        model.put("blog", this.getBlogInfo());
        //加载主题信息
        model.put("theme", this.getThemeInfo());
        //加载page信息
        model.put("page", this.getPageInfo());
        //加载文章信息
        model.put("posts", this.getPosts());
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_CATEGORIES);
    }

    /**
     * 查询文章详细信息
     * 1 如果根据subpath可以查询出来，就按照subpath查询
     * 2 如果subpath查询不出来，就按照id查询
     * @param subpath
     * @return
     * @throws TreeHoleException
     */
    private Article getArticleInfo(String subpath) throws TreeHoleException {
        Article article = this.articleService.getPostBySubPath(subpath);
        if(article == null){
            article = this.articleService.getPostById(subpath);
        }
        return article;
    }

    /**
     * 获取主题配置文件中的信息
     * @return
     * @throws TreeHoleException
     */
    public Map<String,Object> getSiteConfig() throws TreeHoleException {
        Site site = new Site();
        try {
            List<Article> posts = this.articleService.getPosts();
            site.setTime(DateUtils.now());
            site.setPosts(posts);
            site.setPages(posts);
            site.setRelatedPosts(new ArrayList());//这里是首页，所以不会有相关文章
            site.setCategories(new ArrayList()); //这里假设只有在分类页面才会有这个属性
            site.setTags(new ArrayList()); //这里假设在标签页面才有这个属性
            Map siteConfig = TreeHoleConfigUtils.readThremeYmlConfig(treeHoleConfig);
            site.setConfigs(siteConfig);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("主题配置文件未找到");
        }
        return site.bulid();
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
     * 获取主题信息
     * @return
     */
    public String getThemeInfo() {
        String theme = treeHoleConfig.getTheme();
        return theme;
    }

    /**
     * 构造page信息
     * @return
     */
    public Page getPageInfo() {
        Page page = new Page();
        return page;
    }

    private List<Page> getPages() throws TreeHoleException {
        List<Page> pages = new ArrayList<Page>();
        List<Article> posts = this.articleService.getPosts();
        posts.stream().map(post -> post.toPage())
        return null;
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