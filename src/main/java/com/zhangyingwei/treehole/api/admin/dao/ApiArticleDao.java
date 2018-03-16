package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.common.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午9:07
 * @desc:
 */
@Mapper
public interface ApiArticleDao {
    Logger logger = LoggerFactory.getLogger(ApiArticleDao.class);
    @InsertProvider(type = ArticleProvider.class, method = "save")
    void saveArticle(@Param("article") Article article) throws Exception;

    @Select("select * from article where id=#{id}")
    Article queryArticle(@Param("id") String id) throws Exception;

    @Update("update article set title=#{article.title},subpath=#{article.subpath},tags=#{article.tags},excerpt=#{article.excerpt},excerpthtml=#{article.excerptHtml},content=#{article.content},contenthtml=#{article.contentHtml},categories=#{article.categories},usecommont=#{article.usecommont},flag=#{article.flag},date=#{article.date},preview=#{article.preview} where id=#{article.id}")
    void updateArticle(@Param("article") Article article) throws Exception;

    @Update("update article set flag=#{article.flag} where id=#{article.id}")
    void changeFlag(@Param("article") Article article) throws Exception;

    @SelectProvider(type = ArticleProvider.class, method = "count")
    Integer count(@Param("article") Article article) throws Exception;

    @SelectProvider(type = ArticleProvider.class,method = "listArticles")
    List<Article> listArticles(@Param("page") PageInfo pageInfo, @Param("article") Article article) throws Exception;

    class ArticleProvider{
        public String save(){
            String sql = "insert into article (id,title,path,subpath,tags,excerpt,excerpthtml,content,contenthtml,categories,usecommont,flag,date,preview) values (#{article.id},#{article.title},#{article.path},#{article.subpath},#{article.tags},#{article.excerpt},#{article.excerptHtml},#{article.content},#{article.contentHtml},#{article.categories},#{article.usecommont},#{article.flag},#{article.date},#{article.preview})";
            return sql;
        }
        public String count(Article article){
            StringBuffer sql = new StringBuffer("select count(*) from article where 1=1 and ");
            if (StringUtils.isNotEmpty(article.getTitle())) {
                sql.append(" title like '%"+article.getTitle()+"%' and ");
            }
            if (article.getFlag() != null) {
                sql.append("flag=#{article.flag} and ");
            }
            sql.delete(sql.length() - 4, sql.length());
            return sql.toString();
        }
        public String listArticles(PageInfo pageInfo,Article article){
            StringBuffer sql = new StringBuffer("select a.id,a.title,a.path,a.subpath,a.tags,a.excerpt,a.excerpthtml,a.content,a.contenthtml,a.categories,k.name as categoriesText,a.usecommont,a.flag,a.date,a.preview from article as a left join kind as k  on k.id=a.categories where 1=1 and ");
            if (StringUtils.isNotEmpty(article.getTitle())) {
                sql.append(" a.title like '%"+article.getTitle()+"%' and ");
            }
            if (article.getFlag() != null) {
                sql.append("a.flag=#{article.flag} and ");
            }
            sql.delete(sql.length() - 4, sql.length());
            sql.append(" order by a.date desc limit #{page.start},#{page.pageSize}");
            logger.info(pageInfo.getStart() + "," + pageInfo.getPageSize());
            logger.info(sql.toString());
            return sql.toString();
        }
    }
}