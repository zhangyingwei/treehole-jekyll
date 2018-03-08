package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.install.model.BlogConf;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午7:45
 * @desc:
 */
@Mapper
public interface ApiBlogInfoDao {
    Logger logger = LoggerFactory.getLogger(ApiBlogInfoDao.class);
    @UpdateProvider(type = BlogInfoProvider.class, method = "updateBlogConf")
    void updateBlogInfo(@Param("blog") BlogConf blogConf) throws Exception;

    class BlogInfoProvider{
        public String updateBlogConf(BlogConf blogConf){
            StringBuffer sql = new StringBuffer("update bloginfo set ");
            if (StringUtils.isNotEmpty(blogConf.getNickname())) {
                sql.append(" nickname=#{blog.nickname},");
            }
            if (StringUtils.isNotEmpty(blogConf.getEmail())) {
                sql.append(" email=#{blog.email},");
            }
            if (StringUtils.isNotEmpty(blogConf.getWeibo())) {
                sql.append(" weibo=#{blog.weibo},");
            }
            if (StringUtils.isNotEmpty(blogConf.getWeixin())) {
                sql.append(" weixin=#{blog.weixin},");
            }
            if (StringUtils.isNotEmpty(blogConf.getZhihu())) {
                sql.append(" zhihu=#{blog.zhihu},");
            }
            if (StringUtils.isNotEmpty(blogConf.getFacebook())) {
                sql.append("facebook=#{blog.facebook},");
            }
            if (StringUtils.isNotEmpty(blogConf.getTwitter())) {
                sql.append("twitter=#{blog.twitter},");
            }
            sql.delete(sql.length() - 1, sql.length());
            logger.info(sql.toString());
            return sql.toString();
        }
    }
}