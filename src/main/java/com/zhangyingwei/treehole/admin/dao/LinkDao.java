package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.Link;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/13.
 */
public interface LinkDao {

    @Insert("insert into links (name,url) values(#{link.name},#{link.url})")
    void save(@Param("link") Link link)  throws Exception;
    @Delete("delete from links where id=#{id}")
    void deleteById(@Param("id") Integer id)  throws Exception;
    @Select("select * from links")
    List<Link> listLinks() throws Exception;
}
