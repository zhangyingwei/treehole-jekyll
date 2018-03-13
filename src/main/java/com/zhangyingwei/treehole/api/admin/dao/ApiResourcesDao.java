package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.common.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/3/12
 * @time: 下午9:33
 * @desc:
 */
@Mapper
public interface ApiResourcesDao {

    @SelectProvider(type = ResourcesProvider.class, method = "listFiles")
    List<FileRes> listFilesWithPage(@Param("page") PageInfo pageInfo, @Param("file") FileRes fileRes) throws Exception;

    @SelectProvider(type = ResourcesProvider.class, method = "count")
    Integer count(@Param("file") FileRes fileRes) throws Exception;

    @Delete("delete from files where id=#{file.id}")
    void deleteById(@Param("file") FileRes fileRes) throws Exception;

    @Select("select * from files where id=#{file.id}")
    FileRes findFileById(@Param("file") FileRes fileRes) throws Exception;

    class ResourcesProvider {
        public String listFiles(PageInfo pageInfo,FileRes fileRes) {
            StringBuffer sql = new StringBuffer("select * from files where 1=1 and");
            if (StringUtils.isNotEmpty(fileRes.getName())) {
                sql.append(" name like '%").append(fileRes.getName()).append("%' and");
            }
            if (StringUtils.isNotEmpty(fileRes.getContentType())) {
                sql.append(" contenttype=#{file.contentType} and");
            }
            sql.delete(sql.length() - 4, sql.length());
            sql.append(" order by id desc limit #{page.start},#{page.pageSize}");
            return sql.toString();
        }

        public String count(FileRes fileRes){
            StringBuffer sql = new StringBuffer("select count(*) from files where 1=1 and");
            if (StringUtils.isNotEmpty(fileRes.getName())) {
                sql.append(" name like '%").append(fileRes.getName()).append("%' and");
            }
            if (StringUtils.isNotEmpty(fileRes.getContentType())) {
                sql.append(" contenttype=#{file.contentType} and");
            }
            sql.delete(sql.length() - 4, sql.length());
            return sql.toString();
        }
    }
}
