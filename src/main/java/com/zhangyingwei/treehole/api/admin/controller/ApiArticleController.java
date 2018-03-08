package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.api.admin.service.ApiArticleService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
