package com.zhangyingwei.treehole.admin.model;

import com.zhangyingwei.treehole.blog.model.Post;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author: zhangyw
 * @date: 2017/5/14
 * @time: 下午9:38
 * @desc: 文章
 */
public class Article {
    private String id;
    @NotNull(message = "文章标题不能为空")
    private String title;
    private String subpath;
    private String path;
    private String preview;
    private String tags;
    @NotNull(message = "文章类别不能为空")
    private String categories;
    private String categoriesText;
    @NotNull(message = "文章内容不能为空")
    private String content;
    private String contentHtml;
    private String excerpt;
    private String excerptHtml;
    private String usecommont;
    private String date;
    /**
     * 0 保存
     * 1 发布
     * 9 删除
     */
    public static final Integer FLAG_SAVE = 0;
    public static final Integer FLAG_PUBLISH = 1;
    public static final Integer FLAG_DEL = 9;
    private Integer flag;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubpath() {
        return subpath;
    }

    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public String getCategoriesText() {
        return categoriesText;
    }

    public void setCategoriesText(String categoriesText) {
        this.categoriesText = categoriesText;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        this.bulidIntro();//构造 文章简介
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getPath() {
        this.path = "/articles/" + this.id;
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void bulidIntro(){
        if(StringUtils.isNotEmpty(this.content)){
            if(this.content.contains("<!-- more -->")){
                this.excerpt = this.content.split("<!-- more -->")[0].trim();
            }
        }
    }

    private void bulidIntroHtml(){
        if(StringUtils.isNotEmpty(this.contentHtml)){
            if(this.contentHtml.contains("<!-- more -->")){
                this.excerptHtml = this.contentHtml.split("<!-- more -->")[0].trim();
            }
        }
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    /**
     * 经测试 如果不勾选的话，表单提交不会有这个字段
     * 如果勾选的话就会有这个字段
     * 所以这里暂时这么写
     * @return
     */
    public String getUsecommont() {
        return "on".equals(this.usecommont) ? "on" : "off";
    }

    public void setUsecommont(String usecommont) {
        this.usecommont = usecommont;
    }

    public Integer getFlag() {
        if(StringUtils.isNotEmpty(this.type)){
            return bulidFlag(type);
        }
        return this.flag;
    }

    /**
     * 根据 type 生成 flag
     * save 0
     * save_publish 1
     * 9
     * @param type
     * @return
     */
    private Integer bulidFlag(String type) {
        switch (type){
            case "save":
                return 0;
            case "publish":
                return 1;
            default:
                return 9;
        }
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getExcerptHtml() {
        this.bulidIntroHtml();
        return excerptHtml;
    }

    public void setIntroHtml(String introHtml) {
        this.excerptHtml = introHtml;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subpath='" + subpath + '\'' +
                ", path='" + path + '\'' +
                ", preview='" + preview + '\'' +
                ", tags='" + tags + '\'' +
                ", categories='" + categories + '\'' +
                ", categoriesText='" + categoriesText + '\'' +
                ", content='" + content + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", excerptHtml='" + excerptHtml + '\'' +
                ", usecommont='" + usecommont + '\'' +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                ", type='" + type + '\'' +
                '}';
    }

    public Article bulid(){
        this.contentHtml = TreeHoleUtils.markdown(this.content);
        this.bulidIntro();
        this.bulidIntroHtml();
        return this;
    }

    /**
     * 转换对象为 page
     * @return
     */
    public Post toPage() {
        Post page = new Post();
        page.setId(this.getId());
        page.setTitle(this.getTitle());
        page.setPreview(this.getPreview());
        page.setPath(this.getPath());
        page.setCategories(this.getCategories());
        page.setTags(this.getTags() == null?new ArrayList(): Arrays.asList(this.getTags().split(",")));
        page.setContent(this.getContentHtml());
        page.setExcerpt(this.excerptHtml);
        page.setDate(this.getDate());
        page.setComment("on".equals(this.getUsecommont()));
        return page;
    }
}
