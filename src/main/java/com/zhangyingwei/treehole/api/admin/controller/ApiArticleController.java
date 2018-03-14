package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.admin.service.KindService;
import com.zhangyingwei.treehole.api.admin.service.ApiArticleService;
import com.zhangyingwei.treehole.api.admin.service.ApiKindsService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午9:06
 * @desc:
 */
@RestController
@RequestMapping("/api/admin/articles")
@CrossOrigin
public class ApiArticleController {
    private Logger logger = LoggerFactory.getLogger(ApiArticleController.class);

    @Autowired
    private ApiArticleService apiArticleService;

    //    @Autowired
//    private KindService kindService;
    @Autowired
    private ApiKindsService apiKindsService;

    @GetMapping("/kinds")
    public Map listKinds() throws TreeHoleApiException {
        List<Kind> kinds = this.apiKindsService.listActiveKinds();
        return Ajax.success(kinds);
    }

    @GetMapping("/count")
    public Map count() throws TreeHoleApiException {
        Integer count = this.apiArticleService.count();
        return Ajax.success(count);
    }

    @PostMapping("/save")
    public Map saveArticle(Article article) throws TreeHoleApiException {
        String id = this.apiArticleService.saveArticle(article);
        return Ajax.success("保存文章成功",id);
    }

    @PostMapping
    public Map publishArticle(Article article) throws TreeHoleApiException {
        String id = this.apiArticleService.publishArticle(article);
        return Ajax.success("发布文章成功",id);
    }

    @PostMapping("/{id}")
    public Map publishOne(@PathVariable("id") String id) throws TreeHoleApiException {
        this.apiArticleService.publishOne(id);
        return Ajax.success("发布成功");
    }

    @DeleteMapping("/{id}")
    public Map deleteOne(@PathVariable("id") String id) throws TreeHoleApiException {
        this.apiArticleService.deleteOne(id);
        return Ajax.success("删除成功");
    }

    @GetMapping
    public Map listArticles(PageInfo pageInfo,Article article) throws TreeHoleApiException {
        List<Article> articles = this.apiArticleService.listArticlesWithPage(pageInfo, article);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("articles", articles);
        result.put("page", pageInfo);
        return Ajax.success(result);
    }
}
