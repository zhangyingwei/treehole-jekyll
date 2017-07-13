package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.LinkDao;
import com.zhangyingwei.treehole.admin.model.Link;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyw on 2017/7/13.
 */
@Service
public class LinkService {
    @Autowired
    private LinkDao linkDao;

    public List<Link> listLinks() throws TreeHoleException {
        try {
            return this.linkDao.listLinks();
        } catch (Exception e) {
            throw new TreeHoleException(e);
        }
    }
}
