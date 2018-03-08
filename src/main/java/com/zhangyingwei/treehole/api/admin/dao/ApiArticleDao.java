package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.admin.model.Article;
import org.apache.ibatis.annotations.*;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午9:07
 * @desc:
 */
@Mapper
public interface ApiArticleDao {
    @InsertProvider(type = ArticleProvider.class, method = "save")
    void saveArticle(@Param("article") Article article) throws Exception;

    @Select("select * from article where id=#{id}")
    Article queryArticle(@Param("id") String id) throws Exception;

    @Update("update article set title=#{article.title},subpath=#{article.subpath},tags=#{article.tags},excerpt=#{article.excerpt},excerpthtml=#{article.excerptHtml},content=#{article.content},contenthtml=#{article.contentHtml},categories=#{article.categories},usecommont=#{article.usecommont},flag=#{article.flag},date=#{article.date} where id=#{article.id}")
    void updateArticle(@Param("article") Article article) throws Exception;

    class ArticleProvider{
        public String save(){
            String sql = "insert into article (id,title,path,subpath,tags,excerpt,excerpthtml,content,contenthtml,categories,usecommont,flag,date) values (#{article.id},#{article.title},#{article.path},#{article.subpath},#{article.tags},#{article.excerpt},#{article.excerptHtml},#{article.content},#{article.contentHtml},#{article.categories},#{article.usecommont},#{article.flag},#{article.date})";
            return sql;
        }
    }
}