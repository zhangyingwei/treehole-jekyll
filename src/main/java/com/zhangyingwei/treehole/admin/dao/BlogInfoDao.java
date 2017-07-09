package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.install.model.BlogConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by zhangyw on 2017/5/8.
 */

@Mapper
public interface BlogInfoDao {

    @Select("select * from bloginfo")
    BlogConf select();

    @Update("update bloginfo set name=#{bloginfo.name},url=#{bloginfo.url},desc=#{bloginfo.desc}")
    void updateBlogInfo(@Param("bloginfo") BlogConf blogConf) throws Exception;
}
