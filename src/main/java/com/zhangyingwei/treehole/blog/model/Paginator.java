package com.zhangyingwei.treehole.blog.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午2:17
 * @desc: 每当 paginate 配置选项被设置了的时候，这个变量就可用了。详情请看，分页。
 */
public class Paginator {
    /**
     * 每一页Posts的数量。
     */
    private int perPage = 10;
    /**
     * 这一页可用的Posts。
     */
    private List<Page> posts;
    /**
     * Posts 的总数。
     */
    private int totalPosts;

    /**
     * 当前页文章起始位置
     */
    private int start;

    /**
     * Pages 的总数。
     */
    private int totalPages;

    /**
     * 当前页号。
     */
    private int page = 1;

    /**
     * 前一页的页号。
     */
    private int previousPage;

    /**
     * 前一页的地址。
     */
    private String previousPagePath;

    /**
     * 下一页的页号。
     */
    private int nextPage;

    /**
     * 下一页的地址。
     */
    private String nextPagePath;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public List<Page> getPosts() {
        return posts;
    }

    public void setPosts(List<Page> posts) {
        this.posts = posts;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getTotalPages() {
        this.totalPages = this.totalPosts / this.perPage;
        if(this.totalPosts % this.perPage != 0){
            this.totalPages += 1;
        }
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public Paginator setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPreviousPage() {
        if(this.page > 1){
            this.previousPage = this.page - 1;
        }
        return previousPage;
    }


    public String getPreviousPagePath() {
        this.previousPagePath = this.getPath(this.getPreviousPage());
        return previousPagePath;
    }

    /**
     * 构造 指定页面的 地址
     * @param currentPage
     * @return
     */
    private String getPath(int currentPage) {
        return "/articles/page/" + currentPage + "/";
    }

    public int getNextPage() {
        if(this.page < this.getTotalPages()){
            this.nextPage = this.page + 1;
        }
        return nextPage;
    }

    public String getNextPagePath() {
        this.nextPagePath = this.getPath(this.nextPage);
        return nextPagePath;
    }

    public int getStart() {
        this.start = (this.page - 1) * this.perPage;
        return start;
    }

    public void setNextPagePath(String nextPagePath) {
        this.nextPagePath = nextPagePath;
    }

    public Map bulid(){
        Map map = new HashMap();
        map.put("per_page", this.getPerPage());
        map.put("posts", this.getPosts());
        map.put("total_posts", this.getTotalPages());
        map.put("total_pages", this.getTotalPages());
        map.put("page", this.getPage());
        map.put("previous_page", this.getPreviousPage());
        map.put("previous_page_path", this.getPreviousPagePath());
        map.put("next_page", this.getNextPage());
        map.put("next_page_path", this.getNextPagePath());
        return map;
    }
}
