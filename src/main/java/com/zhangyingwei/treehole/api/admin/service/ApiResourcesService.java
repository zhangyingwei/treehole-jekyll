package com.zhangyingwei.treehole.api.admin.service;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.api.admin.dao.ApiResourcesDao;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2018/3/13.
 */
@Service
public class ApiResourcesService {
    private Logger logger = LoggerFactory.getLogger(ApiResourcesService.class);
    @Autowired
    private ApiResourcesDao apiResourcesDao;

    public List<FileRes> listFilesWithPage(PageInfo pageInfo,FileRes fileRes) throws TreeHoleApiException {
        try {
            pageInfo.setTotal(this.apiResourcesDao.count(fileRes));
            return this.apiResourcesDao.listFilesWithPage(pageInfo, fileRes);
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }

    public void deleteById(Integer id) throws TreeHoleApiException {
        FileRes fileRes = new FileRes();
        fileRes.setId(id);
        try {
            FileRes old = this.apiResourcesDao.findFileById(fileRes);
            FileUtils.renameTo(old.getPath(), old.getName() + ".del");
            this.apiResourcesDao.deleteById(fileRes);
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }
}