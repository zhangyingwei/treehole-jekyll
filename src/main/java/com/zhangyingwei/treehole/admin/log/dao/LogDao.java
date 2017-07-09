package com.zhangyingwei.treehole.admin.log.dao;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.admin.log.model.PieView;
import com.zhangyingwei.treehole.common.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Mapper
public interface LogDao {
    @Insert("insert into log (ip,reqtype,ip_location,referer,url,uri,agent,action,timestamp) values (#{log.ip},#{log.reqType},#{log.ip_location},#{log.referer},#{log.url},#{log.uri},#{log.agent},#{log.action},#{log.timestamp})")
    void insert(@Param("log") LogModel log) throws Exception;
    @Select("select count(*) from (select * from log group by ip)")
    Integer getVisitCount() throws Exception;
    @Select("select * from log where timestamp>#{timestamp}")
    List<LogModel> listCountByDay(Long timestamp) throws Exception;
    @Select("select referer from log where referer is not null")
    List<LogModel> listVisits();
    @Select("select id,ip,uri,timestamp from log where uri not like '/admin%' and uri not like '/log%' order by id desc limit #{page.start},#{page.pageSize}")
    List<LogModel> listVisitBlogsByPage(@Param("page") PageInfo pageInfo) throws Exception;
    @Select("select count(*) from log where uri not like '/admin%' and uri not like '/log%'")
    Integer getVisitBlogCount(@Param("page") PageInfo pageInfo) throws Exception;
    @Select("select ip_location as name,count(*) as value from log where ip_location is not null and ip_location !='' group by ip_location")
    List<PieView> getVisitBlogUris() throws Exception;
    @Select("select * from log where uri not like '/admin%' and uri not like '/log%'")
    List<LogModel> listVisitBlogs() throws Exception;
    @Select("select action as name,count(*) as value from log where uri like '/articles%' and action is not null and action !='' group by action")
    List<PieView> getVisitBlogActions() throws Exception;
}
