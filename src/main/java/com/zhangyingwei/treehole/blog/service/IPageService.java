package com.zhangyingwei.treehole.blog.service;

import com.zhangyingwei.treehole.blog.model.Page;
import com.zhangyingwei.treehole.blog.model.Paginator;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.ibatis.annotations.Select;

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
    List<Page> listPostsOrderByDate() throws TreeHoleException;

    /**
     * 查询 post
     * 通过日期排序
     * @return
     */
    List<Page> listPostsOrderByDate(Paginator paginator) throws TreeHoleException;

    /**
     * 查询 post
     * 通过类别排序
     * @return
     */
    List<Page> listPostOrderByCategorie() throws TreeHoleException;

    /**
     * 查询 post
     * 通过 tag 排序
     * @return
     */
    List<Page> listPostOrderByTag() throws TreeHoleException;

    /**
     * 查询 指定 categories 的 psot
     * @param categories
     * @return
     */
    List<Page> listPostsByCategories(String categories) throws TreeHoleException;

    /**
     * 根据 id 获取文章
     * @return
     * @throws TreeHoleException
     */
    Page getPageById(Integer id) throws TreeHoleException;

    /**
     * 根据 subpath 获取文章
     * @return
     * @throws TreeHoleException
     */
    Page getPageBySubpath(String subpath) throws TreeHoleException;


}
