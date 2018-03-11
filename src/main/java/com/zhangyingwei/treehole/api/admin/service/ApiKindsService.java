package com.zhangyingwei.treehole.api.admin.service;

import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.api.admin.dao.ApiKindsDao;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/3/11
 * @time: 下午8:19
 * @desc:
 */
@Service
public class ApiKindsService {
    private Logger logger = LoggerFactory.getLogger(ApiKindsService.class);

    @Autowired
    private ApiKindsDao apiKindsDao;

    public List<Kind> listKindWithPage(PageInfo pageInfo) throws TreeHoleApiException {
        try {
            pageInfo.setTotal(this.apiKindsDao.count());
            return this.apiKindsDao.listKindsWithPage(pageInfo);
        } catch (Exception e) {
            throw new TreeHoleApiException(e);
        }
    }

    public void addKind(Kind kind) throws TreeHoleApiException {
        try {
            Kind old = this.apiKindsDao.findByName(kind);
            if (null == old) {
                this.apiKindsDao.addKind(kind);
            }else {
                throw new Exception(kind.getName() + " - 已经存在");
            }
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }

    public void updateKind(Kind kind) throws TreeHoleApiException {
        try {
            if (null == kind.getId()) {
                throw new Exception("kind.id 获取失败");
            }
            this.apiKindsDao.updateKind(kind);
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }

    public void deleteOneKind(Integer id) throws TreeHoleApiException {
        try {
            Kind kind = new Kind();
            kind.setId(id);
            this.apiKindsDao.deleteKind(kind);
        } catch (Exception e) {
            throw new TreeHoleApiException(e.getLocalizedMessage());
        }
    }
}
