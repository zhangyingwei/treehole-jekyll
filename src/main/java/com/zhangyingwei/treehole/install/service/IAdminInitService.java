package com.zhangyingwei.treehole.install.service;

import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.AdminConf;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.InstallConf;

/**
 * @author: zhangyw
 * @date: 2017/5/4
 * @time: 下午9:46
 * @desc:
 */
public interface IAdminInitService {
    Boolean login(User user) throws TreeHoleException;
    void adminInit(AdminConf adminConf) throws TreeHoleException;
    void blogInfoInit(BlogConf blogConf) throws TreeHoleException;
    void installInfoInit(InstallConf installConf) throws TreeHoleException;
}
