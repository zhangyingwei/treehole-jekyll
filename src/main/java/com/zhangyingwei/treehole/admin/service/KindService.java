package com.zhangyingwei.treehole.admin.service;

import com.zhangyingwei.treehole.admin.dao.ArticleDao;
import com.zhangyingwei.treehole.admin.dao.KindDao;
import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/5/25
 * @time: 下午9:46
 * @desc:
 */
@Service
public class KindService implements IKindService {
    private Logger logger = Logger.getLogger(KindService.class);
    @Autowired
    private KindDao kindDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Kind> getKinds() throws TreeHoleException {
        try {
            return this.kindDao.selectKinds();
        } catch (Exception e) {
            logger.info(e);
            throw new TreeHoleException("查询分类信息错误");
        }
    }

    /**
     * 删除分类信息
     *
     * 修改状态
     * 彻底删除
     *
     * @param id
     * @param type
     * @throws TreeHoleException
     */
    @Override
    public void deleteKindById(String id, String type) throws TreeHoleException {
        try {
            switch (type){
                case "state":
                    this.kindDao.deleteById(id);
                    break;
                case "any":
                    List<Article> articles = this.articleDao.selectArticleByKind(id);
                    if(articles!=null && articles.size()>0){
                        throw new TreeHoleException("分类信息被引用");
                    }
                    this.kindDao.deleteByIdAny(id);
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("删除分类信息错误:"+e.getLocalizedMessage());
        }
    }

    /**
     * 编辑分类信息
     * @param kind
     * @throws TreeHoleException
     */
    @Override
    public void editKindInfo(Kind kind) throws TreeHoleException {
        try {
            this.kindDao.updateById(kind);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("编辑分类信息错误");
        }
    }

    /**
     * 添加分类信息
     * @param kind
     */
    @Override
    public void addKindInfo(Kind kind) throws TreeHoleException {
        try {
            this.kindDao.insert(kind);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException("添加分类信息错误");
        }
    }
}
