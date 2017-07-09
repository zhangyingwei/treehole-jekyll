package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 * 文章操作 dao
 */
@Mapper
public interface ArticleDao {
    @Select("select a.id,a.title,a.subpath,a.tags,a.intro,a.introhtml,a.article,a.articlehtml,k.name as kind,a.usecommont,a.flag,a.date from article as a left join kind k on a.kind = k.id order by a.date desc")
    List<Article> selectArticles() throws Exception;

    @Select("select * from article where flag = 0 order by date desc")
    List<Article> selectActiveArticles() throws Exception;

    @Select("select a.id,a.title,a.subpath,a.tags,a.intro,a.introhtml,a.article,a.articlehtml,k.name as kind,a.usecommont,a.flag,a.date from article as a left join kind k on a.kind = k.id where a.flag=1 order by a.date desc")
    List<Article> selectPosts() throws Exception;

    @Insert("insert into article(title,subpath,tags,intro,introhtml,article,articlehtml,kind,usecommont,flag) values(#{article.title},#{article.subpath},#{article.tags},#{article.intro},#{article.introHtml},#{article.article},#{article.articleHtml},#{article.kind},#{article.usecommont},#{article.flag})")
    void insertArticle(@Param("article") Article article) throws Exception;

    @Select("select * from article where subpath=#{subpath}")
    Article selectArticleBySubpath(@Param("subpath") String subpath) throws Exception;

    @Select("select * from article where subpath=#{subpath} and flag=1")
    Article selectPostBySubpath(@Param("subpath") String subpath) throws Exception;

    @Select("select * from article where title=#{title}")
    Article selectArticleByTitle(String title) throws Exception;

    @Select("select * from article where id=#{id}")
    Article selectArticleById(String id) throws Exception;

    @Select("select a.id,a.title,a.subpath,a.tags,a.intro,a.introhtml,a.article,a.articlehtml,k.name as kind,a.usecommont,a.flag,a.date from article as a left join kind k on a.kind = k.id where a.id=#{id} and a.flag=1 order by a.date desc")
    Article selectPostById(String id) throws Exception;

    @Select("select * from article where kind=#{id}")
    List<Article> selectArticleByKind(String id) throws Exception;

    @Select("select * from article where kind=#{id} and id=1")
    List<Article> selectPostByKind(String id) throws Exception;

    @Update("update article set flag=1,date=datetime('now','localtime') where id=#{id}")
    void publish(String id) throws  Exception;

    @Delete("delete from article where id=#{id}")
    void deleteById(String id) throws Exception;

    @Update("update article set flag=9 where id=#{id}")
    void deleteStateById(String id) throws Exception;

    @Update("update article set title=#{article.title},subpath=#{article.subpath},tags=#{article.tags},intro=#{article.intro},introHtml=#{article.introHtml},article=#{article.article},articleHtml=#{article.articleHtml},usecommont=#{article.usecommont},flag=#{article.flag},date=datetime('now','localtime') where id=#{article.id}")
    void updateArticleById(@Param("article") Article article) throws  Exception;

    @Select("select count(*) from article")
    Integer getPostCount() throws Exception;
}
