package com.zhangyingwei.treehole.api.admin.service;

import com.zhangyingwei.treehole.api.admin.dao.ApiBlogInfoDao;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangyw
 * @date: 2018/3/8
 * @time: 下午8:01
 * @desc:
 */
@Service
public class ApiBlogInfoService {
    private Logger logger = LoggerFactory.getLogger(ApiBlogInfoService.class);

    @Autowired
    private ApiBlogInfoDao apiBlogInfoDao;

    public void updateBlogInfo(BlogConf blogConf) throws TreeHoleApiException {
        try {
            this.apiBlogInfoDao.updateBlogInfo(blogConf);
        } catch (Exception e) {
            throw new TreeHoleApiException(e);
        }
    }
}
