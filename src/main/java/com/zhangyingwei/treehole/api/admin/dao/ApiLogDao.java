package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.log.model.LogModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhangyw on 2018/3/8.
 */

@Mapper
public interface ApiLogDao {
    Logger logger = LoggerFactory.getLogger(ApiLogDao.class);
    @SelectProvider(type = ListLogsProvider.class,method = "listLogs")
    List<LogModel> listLogsWithPage(@Param("page") PageInfo pageInfo,@Param("log") LogModel logModel,@Param("start") String start,@Param("end") String end) throws Exception;

    @SelectProvider(type = ListLogsProvider.class,method = "countLogs")
    Integer total(@Param("log") LogModel logModel,@Param("start") String start,@Param("end") String end) throws Exception;

    class ListLogsProvider {
        public String listLogs(@Param("page") PageInfo pageInfo,@Param("log") LogModel logModel,@Param("start") String start,@Param("end") String end){
            StringBuffer sql = new StringBuffer("select * from log where 1=1 and ");
            if (StringUtils.isNotEmpty(logModel.getIp())) {
                sql.append("ip like '%" + logModel.getIp() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getIp_location())) {
                sql.append("ip_location like '%"+logModel.getIp_location()+"%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getReqtype())) {
                sql.append("reqtype like '%"+logModel.getReqtype()+"%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getAction())) {
                sql.append("action =#{log.action}");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getReferer())) {
                sql.append("referer like '%" + logModel.getReferer() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getUri())) {
                sql.append("uri like '%" + logModel.getUri() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getAgent())) {
                sql.append("agent like '%" + logModel.getAgent() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
                sql.append("timestamp > #{start} and timestamp < #{end}");
            }
            if (sql.toString().endsWith(" and ")) {
                sql.delete(sql.length() - 5, sql.length() - 1);
            }
            sql.append(" order by id desc limit #{page.start},#{page.pageSize}");
            logger.info(sql.toString());
            return sql.toString();
        }

        public String countLogs(@Param("log") LogModel logModel,@Param("start") String start,@Param("end") String end){
            StringBuffer sql = new StringBuffer("select count(*) from log where 1=1 and ");
            if (StringUtils.isNotEmpty(logModel.getIp())) {
                sql.append("ip like '%" + logModel.getIp() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getIp_location())) {
                sql.append("ip_location like '%"+logModel.getIp_location()+"%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getReqtype())) {
                sql.append("reqtype like '%"+logModel.getReqtype()+"%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getAction())) {
                sql.append("action =#{log.action}");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getReferer())) {
                sql.append("referer like '%" + logModel.getReferer() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getUri())) {
                sql.append("uri like '%" + logModel.getUri() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(logModel.getAgent())) {
                sql.append("agent like '%" + logModel.getAgent() + "%'");
                sql.append(" and ");
            }
            if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
                sql.append("timestamp > #{start} and timestamp < #{end}");
            }
            if (sql.toString().endsWith(" and ")) {
                sql.delete(sql.length() - 5, sql.length() - 1);
            }
            return sql.toString();
        }
    }
}
