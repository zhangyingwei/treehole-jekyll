package com.zhangyingwei.treehole.common;

/**
 * Created by zhangyw on 2017/6/16.
 * 分页信息
 */
public class PageInfo {
    private Integer pageSize = 50;
    private Integer total;
    private Integer current = 1;
    private Integer totalPages = 0;
    private Integer start;
    private Integer end;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getStart() {
        return (this.current - 1) * this.pageSize + 1;
    }

    public Integer getEnd() {
        return this.pageSize * this.current;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getTotalPages() {
        if(this.total%this.pageSize==0){
            return this.total / this.pageSize;
        }else{
            return Math.abs(this.total / this.pageSize) + 1;
        }
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageSize=" + pageSize +
                ", total=" + total +
                ", current=" + current +
                ", start=" + this.getStart() +
                ", end=" + this.getEnd() +
                '}';
    }
}
