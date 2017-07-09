package com.zhangyingwei.treehole.install.dao;

import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.install.model.AdminConf;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhangyw on 2017/4/26.
 */

@Mapper
public interface AdminInitDao {
    @Insert("insert into admin (username,password) values (#{admin.username},#{admin.password})")
    void insertOne(@Param("admin")AdminConf adminConf);
    @Insert("insert into bloginfo (name,url,desc) values (#{blog.name},#{blog.url},#{blog.desc})")
    void insertBlogInfo(@Param("blog") BlogConf blogConf);
    @Insert("insert into installinfo(idate,iosname,iosdesktop,ilocal,ibower,ioscpu,ijdkversion) values (#{install.idate},#{install.iosname},#{install.iosdesktop},#{install.ilocal},#{install.ibower},#{install.ioscpu},#{install.ijdkversion})")
    void insertInstallInfo(@Param("install") InstallConf installConf);
    @Select("select * from admin where username=#{user.username} and password=#{user.password}")
    User selectOne(@Param("user") User user) throws Exception;
}
