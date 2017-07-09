package com.zhangyingwei.treehole.admin.model;

/**
 * Created by zhangyw on 2017/5/28.
 * 素材资源实体类
 */
public class FileRes {
    private Integer id;
    private String name;
    private String path;
    private String alias;
    private String contentType;

    public FileRes() {}

    public FileRes(String name, String path, String alias,String contentType) {
        this.name = name;
        this.path = path;
        this.alias = alias;
        this.contentType = contentType;
    }

    public FileRes(Integer id, String name, String path, String alias,String contentType) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.alias = alias;
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "FileRes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", alias='" + alias + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
