package com.zhangyingwei.treehole.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyw on 2017/5/5.
 * 菜单
 */
public class Menu implements Serializable{
    private boolean isRoot;
    /**
     * 名称
     */
    private String name;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 子节点
     */
    private List<Menu> child;
    /**
     * 下一个兄弟节点
     */
    private Menu next;

    public Menu() {}

    public Menu(String name, String url,String icon) {
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getChild() {
        return child;
    }

    public Menu setChild(List<Menu> child) {
        this.child = child;
        return this;
    }

    public Menu addChild(Menu child){
        if (this.getChild() == null){
            this.child = new ArrayList<Menu>();
        }
        this.child.add(child);
        return this;
    }

    public Menu getNext() {
        return next;
    }

    public Menu setNext(Menu next) {
        this.next = next;
        return this;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public Menu setRoot(boolean root) {
        isRoot = root;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
