package com.zhangyingwei.treehole.admin.dao;

import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhangyw on 2017/5/9.
 */
@Mapper
public interface InstallInfoDao {
    @Select("select * from installinfo limit 1")
    InstallConf select() throws TreeHoleException;
}
