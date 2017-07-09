package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.admin.model.Kind;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by zhangyw on 2017/5/15.
 */
@Mapper
public interface KindDao {
    @Select("select * from kind order by id desc")
    List<Kind> selectKinds() throws Exception;
    @Select("select * from kind where flag <> 9 order by id desc")
    List<Kind> selectActiveKinds() throws Exception;
    @Update("update kind set flag = 9 where id=#{id}")
    void deleteById(String id) throws Exception;
    @Update("update kind set name=#{kind.name},flag=#{kind.flag} where id=#{kind.id}")
    void updateById(@Param("kind")Kind kind) throws Exception;
    @Insert("insert into kind (name,flag) values(#{kind.name},#{kind.flag})")
    void insert(@Param("kind") Kind kind) throws Exception;
    @Delete("delete from kind where id=#{id}")
    void deleteByIdAny(String id);
}
