package com.zhangyingwei.treehole.install.service;

import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import com.zhangyingwei.treehole.install.dao.AdminInitDao;
import com.zhangyingwei.treehole.install.model.AdminConf;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyw on 2017/4/26.
 */
@Service
public class AdminInitService implements IAdminInitService {
    private Logger logger = LoggerFactory.getLogger(AdminInitService.class);
    @Autowired
    private AdminInitDao adminInitDao;

    @Override
    public Boolean login(User user) throws TreeHoleException{
        try {
            User login = this.adminInitDao.selectOne(user);
            if (login != null && login.getUsername() != null && login.getPassword() != null) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("用户名或密码错误", e);
        }
    }

    @Override
    public void adminInit(AdminConf adminConf) throws TreeHoleException {
        String msg;
        try {
            adminConf.setPassword(TreeHoleUtils.encodePasswordByMD5(adminConf.getPassword()));
            adminInitDao.insertOne(adminConf);
            msg = "初始化管理端信息成功";
            logger.info(msg);
        } catch (Exception e) {
            msg = "初始化管理端信息失败";
            logger.error(msg);
            throw new TreeHoleException(msg,e);
        }
    }

    @Override
    public void blogInfoInit(BlogConf blogConf) throws TreeHoleException{
        String msg;
        try {
            adminInitDao.insertBlogInfo(blogConf);
            msg = "初始化博客信息成功";
            logger.info(msg);
        } catch (Exception e) {
            msg = "初始化博客信息错误";
            logger.error(msg);
            throw new TreeHoleException(msg, e);
        }
    }

    @Override
    public void installInfoInit(InstallConf installConf) throws TreeHoleException {
        adminInitDao.insertInstallInfo(installConf);
    }
}
