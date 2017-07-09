package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.ArticleDao;
import com.zhangyingwei.treehole.admin.dao.KindDao;
import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 */
@Service
public class ArticleService implements IArticleService {
    private Logger logger = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private KindDao kindDao;
    @Autowired
    private ArticleDao articleDao;

    /**
     * 查询所有文章类型列表
     * @return
     * @throws TreeHoleException
     */
    @Override
    public List<Kind> getKinds() throws TreeHoleException {
        try {
            return this.kindDao.selectKinds();
        } catch (Exception e) {
            throw new TreeHoleException("查询文章类别列表错误", e);
        }
    }

    /**
     * 查询所有文章
     *
     * @return
     * @throws TreeHoleException
     */
    @Override
    public List<Article> getArticles() throws TreeHoleException {
        try {
            return this.articleDao.selectArticles();
        } catch (Exception e) {
            throw new TreeHoleException("查询所有文章错误: " + e.getMessage());
        }
    }

    /**
     * 查询所有已发布文章
     *
     * @return
     * @throws TreeHoleException
     */
    @Override
    public List<Article> getPosts() throws TreeHoleException {
        try {
            return this.articleDao.selectPosts();
        } catch (Exception e) {
            throw new TreeHoleException("查询所有已发布文章错误: " + e.getMessage());
        }
    }

    /**
     * 根据subpath查询文章
     * @param subpath
     * @return
     */
    @Override
    public Article getArticleBySubPath(String subpath) throws TreeHoleException {
        try {
            return this.articleDao.selectArticleBySubpath(subpath);
        } catch (Exception e) {
            throw new TreeHoleException("根据subpath查询文章错误",e);
        }
    }

    /**
     * 根据subpath查询已发布文章
     * @param subpath
     * @return
     */
    @Override
    public Article getPostBySubPath(String subpath) throws TreeHoleException {
        try {
            return this.articleDao.selectPostBySubpath(subpath);
        } catch (Exception e) {
            throw new TreeHoleException("根据subpath查询已发布文章错误",e);
        }
    }

    /**
     * 根据 title 查询文章
     * @param title
     * @return
     */
    @Override
    public Article getArticleByTitle(String title) throws TreeHoleException {
        try {
            return this.articleDao.selectArticleByTitle(title);
        } catch (Exception e) {
            throw new TreeHoleException("根据标题查询文章错误", e);
        }
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Article getArticleById(String id) throws TreeHoleException {
        try {
            return this.articleDao.selectArticleById(id);
        } catch (Exception e) {
            throw new TreeHoleException("根据编号查询文章错误", e);
        }
    }

    /**
     * 根据id查询已发布文章
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Article getPostById(String id) throws TreeHoleException {
        try {
            return this.articleDao.selectPostById(id);
        } catch (Exception e) {
            throw new TreeHoleException("根据编号查询已发布文章错误", e);
        }
    }

    /**
     * 新增文章
     * @param article
     * @throws TreeHoleException
     */
    @Override
    public void addArticle(Article article) throws TreeHoleException{
        try {
            if(StringUtils.isNotEmpty(article.getSubpath())){
                Article tmpArticle = this.articleDao.selectArticleBySubpath(article.getSubpath());
                if (tmpArticle != null && StringUtils.isNotEmpty(tmpArticle.getSubpath()) && tmpArticle.getSubpath().equals(article.getSubpath())) {
                    throw new TreeHoleException("自定义访问地址 "+article.getSubpath() + " 已存在");
                }
            }
            if(StringUtils.isNotEmpty(article.getTitle())){
                Article tmpArticle = this.articleDao.selectArticleByTitle(article.getTitle());
                if (tmpArticle != null && StringUtils.isNotEmpty(tmpArticle.getTitle()) && tmpArticle.getTitle().equals(article.getTitle())) {
                    throw new TreeHoleException("文章标题 "+article.getSubpath() + " 已存在");
                }
            }
            this.articleDao.insertArticle(article);
        } catch (Exception e) {
            throw new TreeHoleException(e.getLocalizedMessage());
        }
    }

    /**
     * 通过id 发布文章
     * 修改状态为发布状态即可
     * @param id
     */
    @Override
    public void addPublishArticle(String id) throws TreeHoleException {
        try {
            this.articleDao.publish(id);
        } catch (Exception e) {
            throw new TreeHoleException("发布文章错误", e);
        }
    }

    /**
     * 删除文章
     * @param id
     */
    @Override
    public void deleteArticle(String id,boolean state) throws TreeHoleException {
        try{
            if(state){
                this.articleDao.deleteStateById(id);
            }else{
                this.articleDao.deleteById(id);
            }
        }catch (Exception e){
            throw new TreeHoleException("删除文章错误", e);
        }
    }

    /**
     * 编辑文章
     * @param article
     */
    @Override
    public void editArticle(Article article) throws TreeHoleException {
        try {
            this.articleDao.updateArticleById(article);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new TreeHoleException("根据id编辑文章错误",e);
        }
    }

    @Override
    public Integer getPostCount() throws TreeHoleException {
        try {
            return this.articleDao.getPostCount();
        } catch (Exception e) {
            throw new TreeHoleException("获取发表文章总数错误");
        }
    }
}
