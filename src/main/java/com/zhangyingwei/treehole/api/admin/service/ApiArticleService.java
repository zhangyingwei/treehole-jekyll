package com.zhangyingwei.treehole.api.admin.service;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.api.admin.dao.ApiArticleDao;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午9:14
 * @desc:
 */
@Service
public class ApiArticleService {
    private Logger logger = LoggerFactory.getLogger(ApiArticleService.class);

    @Autowired
    private ApiArticleDao apiArticleDao;

    public String saveArticle(Article article) throws TreeHoleApiException {
        try {
            if (StringUtils.isEmpty(article.getId())) {
                article.setId(UUID.randomUUID().toString());
            }
            article.setDate(DateUtils.now());
            article.setFlag(Article.FLAG_SAVE);
            if (apiArticleDao.queryArticle(article.getId()) != null) {
                this.apiArticleDao.updateArticle(article);
            } else {
                this.apiArticleDao.saveArticle(article);
            }
            return article.getId();
        } catch (Exception e) {
            throw new TreeHoleApiException(e);
        }
    }

    public String publishArticle(Article article) throws TreeHoleApiException {
        try {
            if (article.getId() == null) {
                article.setId(UUID.randomUUID().toString());
            }
            article.setDate(DateUtils.now());
            article.setFlag(Article.FLAG_PUBLISH);
            this.apiArticleDao.saveArticle(article);
            return article.getId();
        } catch (Exception e) {
            throw new TreeHoleApiException(e);
        }
    }
}
