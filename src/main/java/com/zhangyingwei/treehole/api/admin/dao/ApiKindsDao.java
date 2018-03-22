package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.PageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/3/11
 * @time: 下午8:16
 * @desc:
 */
@Mapper
public interface ApiKindsDao {

    @Select("select * from kind order by id desc limit #{page.start},#{page.pageSize}")
    List<Kind> listKindsWithPage(@Param("page") PageInfo pageInfo) throws Exception;

    @Select("select * from kind where flag=0 order by id desc")
    List<Kind> listActiceKinds() throws Exception;

    @Select("select count(*) from kind")
    Integer count();

    @Insert("insert into kind (name,flag) values (#{kind.name},#{kind.flag})")
    void addKind(@Param("kind") Kind kind) throws Exception;

    @Select("select * from kind where name=#{kind.name}")
    Kind findByName(@Param("kind") Kind kind) throws Exception;

    @Update("update kind set name=#{kind.name},flag=#{kind.flag} where id=#{kind.id}")
    void updateKind(@Param("kind") Kind kind) throws Exception;

    @Delete("update kind set flag=9 where id=#{kind.id}")
    void deleteKind(@Param("kind") Kind kind) throws Exception;
}
