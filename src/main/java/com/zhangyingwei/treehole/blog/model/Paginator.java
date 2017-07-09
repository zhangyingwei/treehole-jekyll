package com.zhangyingwei.treehole.blog.model;

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
    private int perPage;
    /**
     * 这一页可用的Posts。
     */
    private Post post;
    /**
     * Posts 的总数。
     */
    private int totalPosts;

    /**
     * Pages 的总数。
     */
    private int totalPages;

    /**
     * 当前页号。
     */
    private int page;

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
}
