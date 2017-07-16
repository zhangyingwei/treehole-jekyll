package com.zhangyingwei.treehole.blog.service;

import com.zhangyingwei.treehole.blog.model.Post;
import com.zhangyingwei.treehole.blog.model.Paginator;
import com.zhangyingwei.treehole.blog.model.Tag;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/10.
 */
public interface IPageService {
    /**
     * 查询 post
     * 通过日期排序
     * @return
     */
    List<Post> listPostsOrderByDate() throws TreeHoleException;

    /**
     * 查询 post
     * 通过日期排序
     * @return
     */
    List<Post> listPostsOrderByDate(Paginator paginator) throws TreeHoleException;

    /**
     * 查询 post
     * 通过类别排序
     * @return
     */
    List<Post> listPostOrderByCategorie() throws TreeHoleException;

    /**
     * 查询 post
     * 通过 tag 排序
     * @return
     */
    List<Post> listPostByTag(String tag) throws TreeHoleException;

    /**
     * 查询 指定 categories 的 psot
     * @param categories
     * @return
     */
    List<Post> listPostsByCategories(String categories) throws TreeHoleException;

    /**
     * 根据 id 获取文章
     * @return
     * @throws TreeHoleException
     */
    Post getPageById(Integer id) throws TreeHoleException;

    /**
     * 根据 subpath 获取文章
     * @return
     * @throws TreeHoleException
     */
    Post getPageBySubpath(String subpath) throws TreeHoleException;


    /**
     * 根据文章的 类型 推荐相关文章
     * @param categories
     * @return
     */
    List<Post> listRelatedPosts(String categories) throws TreeHoleException;

    /**
     * 查询 Post
     * 通过 tag 排序
     * @return
     * @throws TreeHoleException
     */
    List<Tag> listPostOrderByTags() throws TreeHoleException;

    /**
     * 列出所有 categories
     * @return
     */
    List<String> listCategories() throws TreeHoleException;

    /**
     * 列出所有 tags
     * @return
     */
    List<String> listTags() throws TreeHoleException;
}
