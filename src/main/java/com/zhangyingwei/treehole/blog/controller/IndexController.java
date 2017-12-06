package com.zhangyingwei.treehole.blog.controller;

import com.zhangyingwei.treehole.admin.model.Link;
import com.zhangyingwei.treehole.admin.service.BlogManagerService;
import com.zhangyingwei.treehole.admin.service.LinkService;
import com.zhangyingwei.treehole.blog.model.*;
import com.zhangyingwei.treehole.blog.service.IPageService;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.annotation.Auth;
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
    @Autowired
    private LinkService linkService;

    @GetMapping
    public String index(){
        Boolean page = treeHoleConfig.getPage();
        if(page){
            return "redirect:/articles/pages/1";
        }else{
            return "redirect:/articles";
        }
    }

    @GetMapping("/articles/pages/{page}")
    @TreeHoleAtcion("打开博客首页(分页)")
    public String indexArticlesWithPage(Map<String, Object> model,@PathVariable("page")Integer page) throws TreeHoleException {
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

    @GetMapping("/articles")
    @TreeHoleAtcion("打开博客首页(全部文章)")
    public String indexArticles(Map<String, Object> model) throws TreeHoleException {
        Site site = this.getSiteConfig();
        List<Post> posts = this.pageService.listPostsOrderByDate();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/articles");
        pageInfo.setDescription(site.getConfig("desc"));
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("posts", posts);
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
        //查询相关文章
        List<Post> relatedPosts = this.pageService.listRelatedPosts(post.getCategories());
        //加载配置信息
        model.put("site", site.bulid());
        //加载page信息
        model.put("page", pageInfo);
        model.put("related", relatedPosts);
        //加载page信息
        model.put("post", post);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/articles/preview/{id}")
    @TreeHoleAtcion("预览文章)")
    @Auth
    public String previewArticle(Map<String,Object> model , @PathVariable("id") Integer id) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/articles/preview/"+id);
        pageInfo.setDescription(site.getConfig("desc"));
        //查询文章信息
        Post post = this.pageService.getArticleById(id);
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
        //查询相关文章
        List<Post> relatedPosts = this.pageService.listRelatedPosts(post.getCategories());
        //加载配置信息
        model.put("site", site.bulid());
        //加载page信息
        model.put("page", post);
        model.put("related", relatedPosts);
        //加载page信息
        model.put("post", post);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ARTICLE);
    }

    @GetMapping("/categories")
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

    @GetMapping("/about")
    @TreeHoleAtcion("打开关于我页面")
    public String indexAbout(Map<String, Object> model) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/about");
        pageInfo.setDescription(site.getConfig("desc"));
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_ABOUT);
    }

    @GetMapping("/tags")
    @TreeHoleAtcion("打开标签页面")
    public String tags(Map<String, Object> model) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/tags");
        pageInfo.setDescription(site.getConfig("desc"));
        List<Tag> tags = this.pageService.listPostOrderByTags();
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("tags", tags);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_TAGS);
    }

    @GetMapping("/tags/{tag}")
    @TreeHoleAtcion("获取指定标签的文章")
    public String getPostByTag(Map<String, Object> model,@PathVariable("tag") String tag) throws TreeHoleException {
        Site site = this.getSiteConfig();
        Page pageInfo = new Page();
        pageInfo.setTitle(site.getConfig("name"));
        pageInfo.setUrl("/tags");
        pageInfo.setDescription(site.getConfig("desc"));
        List<Post> posts = this.pageService.listPostByTag(tag);
        model.put("site", site.bulid());
        model.put("page", pageInfo);
        model.put("posts", posts);
        return Pages.blog(treeHoleConfig, Pages.BLOG_THEME_TAGS);
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
            List<Link> links = this.linkService.listLinks();
            site.setLinks(links);
            List<String> categories = this.pageService.listCategories();
            List<String> tags = this.pageService.listTags();
            site.setCategories(categories);
            site.setTags(tags);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("主题配置文件未找到");
        }
        return site;
    }

}