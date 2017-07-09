package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.BlogInfoDao;
import com.zhangyingwei.treehole.admin.dao.InstallInfoDao;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyw on 2017/5/8.
 */
public interface IBlogManagerService {
    BlogConf getBlogConf() throws TreeHoleException;

    InstallConf getInstallinfo() throws TreeHoleException;

    void updateBlogInfo(BlogConf blogConf) throws TreeHoleException;
}
