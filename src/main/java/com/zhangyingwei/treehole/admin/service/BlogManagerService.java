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
@Service
public class BlogManagerService implements IBlogManagerService{
    private Logger logger = LoggerFactory.getLogger(BlogManagerService.class);

    @Autowired
    private BlogInfoDao blogInfoDao;
    @Autowired
    private InstallInfoDao installInfoDao;

    @Override
    public BlogConf getBlogConf() throws TreeHoleException {
        BlogConf blogConf = new BlogConf();
        try {
            blogConf = this.blogInfoDao.select();
            blogConf.setDesc(TreeHoleUtils.trans2Html(blogConf.getDesc()));
        } catch (Exception e) {
            throw new TreeHoleException("查询博客基础信息错误", e);
        }
        return blogConf;
    }
    @Override
    public InstallConf getInstallinfo() throws TreeHoleException {
        InstallConf installConf = null;
        try{
            installConf = this.installInfoDao.select();
        }catch (Exception e){
            throw new TreeHoleException("查询安装信息错误", e);
        }
        return installConf;
    }
    @Override
    public void updateBlogInfo(BlogConf blogConf) throws TreeHoleException{
        try {
            this.blogInfoDao.updateBlogInfo(blogConf);
        } catch (Exception e) {
            logger.error("更新博客信息错误");
            throw new TreeHoleException("更新基础信息错误", e);
        }
    }
}
