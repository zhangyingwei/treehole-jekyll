package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 */
public interface IArticleService {
    /**
     * 查询所有文章类型列表
     * @return
     * @throws TreeHoleException
     */
    List<Kind> getKinds() throws TreeHoleException;

    /**
     * 查询所有文章
     *
     * @return
     * @throws TreeHoleException
     */
    List<Article> getArticles() throws TreeHoleException;

    /**
     * 查询所有已发布文章
     *
     * @return
     * @throws TreeHoleException
     */
    List<Article> getPosts() throws TreeHoleException;

    /**
     * 根据subpath查询文章
     * @param subpath
     * @return
     */
    Article getArticleBySubPath(String subpath) throws TreeHoleException;

    /**
     * 根据subpath查询已发布文章
     * @param subpath
     * @return
     */
    Article getPostBySubPath(String subpath) throws TreeHoleException;

    /**
     * 根据 title 查询文章
     * @param title
     * @return
     */
    Article getArticleByTitle(String title) throws TreeHoleException;

    /**
     * 根据id查询文章
     * @param id
     * @return
     * @throws Exception
     */
    Article getArticleById(String id) throws TreeHoleException;

    /**
     * 根据id查询已发布文章
     * @param id
     * @return
     * @throws Exception
     */
    Article getPostById(String id) throws TreeHoleException;

    /**
     * 新增文章
     * @param article
     * @throws TreeHoleException
     */
    void addArticle(Article article) throws TreeHoleException;

    /**
     * 通过id 发布文章
     * 修改状态为发布状态即可
     * @param id
     */
    void addPublishArticle(String id) throws TreeHoleException;

    /**
     * 删除文章
     * @param id
     */
    void deleteArticle(String id,boolean state) throws TreeHoleException;

    /**
     * 编辑文章
     * @param article
     */
    void editArticle(Article article) throws TreeHoleException;

    /**
     * 获取发布文章总数
     * @return
     * @throws TreeHoleException
     */
    Integer getPostCount() throws TreeHoleException;
}
