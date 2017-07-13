package com.zhangyingwei.treehole.blog.dao;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.blog.model.Paginator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/10.
 */
@Mapper
public interface PageDao {

    /**
     * 获取 post 的总数
     * @return
     */
    @Select("select count(*) from article where flag=1")
    Integer count();

    /**
     * 查询一个按照时间倒叙排列的 post 集合
     * @return
     */
    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 order by date desc")
    List<Article> listPostsOrderByDate() throws Exception;

    /**
     * 查询一个按照时间倒叙排列的 post 集合
     * @param paginator 分页参数
     * @return
     */
    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 order by date desc limit #{paginator.start},#{paginator.perPage} ")
    List<Article> listPostsOrderByDateWithPaginator(@Param("paginator") Paginator paginator) throws Exception;

    /**
     * 根据 id 查询文章
     *
     * @param id
     * @return
     */
    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.id=#{id} and a.flag=1")
    Article getArticleById(@Param("id") Integer id) throws Exception;

    /**
     * 根据 subpath 查询文章
     *
     * @param subpath
     * @return
     */
    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 and a.subpath=#{subpath}")
    Article getArticleBySubpath(@Param("subpath") String subpath) throws Exception;

    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 and k.name like #{categories} order by a.date desc")
    List<Article> listPostsByCategories(@Param("categories") String categories) throws Exception;

    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 order by a.categories")
    List<Article> listPostsOrderByCategories() throws Exception;

    @Select("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,k.name as categories,a.usecommont,a.flag,a.date from article as a left join kind k on a.categories = k.id where a.flag=1 and k.name like #{categories} order by a.date desc limit 6")
    List<Article> listRelatedPosts(String categories) throws Exception;
}

