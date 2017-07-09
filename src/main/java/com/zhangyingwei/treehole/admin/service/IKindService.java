package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.ArticleDao;
import com.zhangyingwei.treehole.admin.dao.KindDao;
import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/5/25
 * @time: 下午9:46
 * @desc:
 */
public interface IKindService {
    List<Kind> getKinds() throws TreeHoleException;

    void deleteKindById(String id, String type) throws TreeHoleException;

    void editKindInfo(Kind kind) throws TreeHoleException;

    void addKindInfo(Kind kind) throws TreeHoleException;
}
