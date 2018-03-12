package com.zhangyingwei.treehole.api.admin.dao;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.common.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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
    List<FileRes> listFilesWithPage() throws Exception;

    class ResourcesProvider {
        public String listFiles(FileRes fileRes, PageInfo pageInfo) {
            StringBuffer sql = new StringBuffer("select * from files where 1=1 and");
            if (StringUtils.isNotEmpty(fileRes.getName())) {
                sql.append(" name like %").append(fileRes.getName()).append("% and");
            }
            if (StringUtils.isNotEmpty(fileRes.getContentType())) {
                sql.append(" contenttype=#{contenttype} and");
            }
            sql.delete(sql.length() - 4, sql.length());
            return sql.toString();
        }
    }
}
