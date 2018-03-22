package com.zhangyingwei.treehole.api.admin.service;

import com.zhangyingwei.treehole.api.admin.dao.ApiLogDao;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.log.model.LogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2018/3/8.
 */
@Service
public class ApiLogService {
    private Logger logger = LoggerFactory.getLogger(ApiLogService.class);

    @Autowired
    private ApiLogDao apiLogDao;

    public List<LogModel> listLogsWithPage(LogModel logModel, PageInfo pageInfo,String start,String end) throws TreeHoleException{
        try {
            pageInfo.setTotal(this.apiLogDao.total(logModel,start,end));
            return this.apiLogDao.listLogsWithPage(pageInfo, logModel, start, end);
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }

    public Integer visitCount() throws TreeHoleApiException {
        try {
            return this.apiLogDao.visitCount();
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }
}
