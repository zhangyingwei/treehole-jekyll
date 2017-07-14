package com.zhangyingwei.treehole.blog.service;

import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.blog.dao.PageDao;
import com.zhangyingwei.treehole.blog.model.Post;
import com.zhangyingwei.treehole.blog.model.Paginator;
import com.zhangyingwei.treehole.blog.model.Tag;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/7/10.
 */
@Service
public class PageService implements IPageService {
    private Logger logger = LoggerFactory.getLogger(PageService.class);
    @Autowired
    private PageDao pageDao;

    @Override
    public List<Post> listPostsOrderByDate() throws TreeHoleException {
        try {
            List<Article> posts = this.pageDao.listPostsOrderByDate();
            return posts.stream().map(post -> post.toPage()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Post> listPostsOrderByDate(Paginator paginator) throws TreeHoleException {
        try {
            Integer count = this.pageDao.count();
            List<Article> posts = this.pageDao.listPostsOrderByDateWithPaginator(paginator);
            paginator.setTotalPosts(count);
            List<Post> resultPost = posts.stream().map(post -> post.toPage()).collect(Collectors.toList());
            return resultPost;
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Post> listPostOrderByCategorie() throws TreeHoleException {
        try {
            List<Article> articles = this.pageDao.listPostsOrderByCategories();
            return articles.stream().map(article -> article.toPage()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Post> listPostByTag(String tag) throws TreeHoleException {
        String tagl = "%," + tag + "%";
        String tagr = "%" + tag + "，%";
        try {
            List<Article> posts = this.pageDao.listPostsByTag(tagl, tagr);
            return posts.stream().map(article -> article.toPage()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Post> listPostsByCategories(String categories) throws TreeHoleException {
        try {
            categories = "%" + categories + "%";
            List<Article> articles = this.pageDao.listPostsByCategories(categories);
            return articles.stream().map(article -> article.toPage()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public Post getPageById(Integer id) throws TreeHoleException {
        try {
            Article article = this.pageDao.getArticleById(id);
            return article.toPage();
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public Post getPageBySubpath(String subpath) throws TreeHoleException {
        try {
            Article articie = this.pageDao.getArticleBySubpath(subpath);
            return articie.toPage();
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Post> listRelatedPosts(String categories) throws TreeHoleException {
        try {
            List<Article> articles = this.pageDao.listRelatedPosts(categories);
            return articles.stream().map(article -> article.toPage()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    @Override
    public List<Tag> listPostOrderByTags() throws TreeHoleException {
        try {
            List<Article> articles = this.pageDao.listPostsOrderByDate();
            Map<String, List> map = new HashMap<String,List>();
            articles.stream().map(article -> {
                return new Object[]{article.getTags(), article};
            }).filter(objects -> {
                return objects[0] != null && (objects+"").length() >0;
            }).map(objects -> {
                return new Object[]{objects[0].toString().split(","),objects[1]};
            }).forEach(objects -> {
                Object[] tags = (Object[]) objects[0];
                Arrays.stream(tags).forEach(tag ->{
                    List list = new ArrayList();
                    if(map.containsKey(tag)){
                        list = map.get(tag);
                    }
                    list.add(objects[1]);
                    map.put(tag.toString(), list);
                });
            });
            List<Tag> tagList = new ArrayList<Tag>();
            for (String key : map.keySet()) {
                tagList.add(new Tag(key,map.get(key)));
            }
            return tagList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Tag>();
    }

    @Override
    public List<String> listTags() throws TreeHoleException {
        try {
            List<String> tags = this.pageDao.listTags();
            return tags;
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }
}
