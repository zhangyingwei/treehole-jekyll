package com.zhangyingwei.treehole.admin.log.service;

import com.zhangyingwei.treehole.admin.log.dao.LogDao;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.admin.log.model.PageVisitView;
import com.zhangyingwei.treehole.admin.log.model.PieView;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import com.zhangyingwei.treehole.common.utils.CollectionUtils;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Service
public class LogService {

    private Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogDao logDao;

    /**
     * 添加一条日志
     * @param log
     * @throws TreeHoleException
     */
    public void addLog(LogModel log) throws TreeHoleException {
        try {
            this.logDao.insert(log);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new TreeHoleException("写入日志错误",e);
        }
    }

    /**
     * 访问统计
     * @return
     * @throws TreeHoleException
     */
    public Integer getVisitCount() throws TreeHoleException {
        try {
            return this.logDao.getVisitCount();
        } catch (Exception e) {
            throw new TreeHoleException("获取访问总人数错误");
        }
    }

    /**
     * 按天统计
     * @return
     * @throws TreeHoleException
     */
    public Object[] listCountByDay() throws TreeHoleException {
        Long timestamp = DateUtils.getTimeStampBefore(7);
        try {
            Map<String, Integer> countMap = new HashMap<String,Integer>(){
                {
                    put(DateUtils.getDateBeforeBy(1, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(2, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(3, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(4, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(5, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(6, "yyyy-MM-dd"), 0);
                    put(DateUtils.getDateBeforeBy(7, "yyyy-MM-dd"), 0);
                }
            };
            List<LogModel> logs = this.logDao.listCountByDay(timestamp);
            for (LogModel log:logs) {
                String date = DateUtils.getDateBy(log.getTimestamp(),"yyyy-MM-dd");
                if(countMap.containsKey(date)){
                    countMap.put(date,countMap.get(date)+1);
                }
            }
            countMap = CollectionUtils.sortByKey(countMap);
            return new Object[]{countMap.keySet(), countMap.values()};
        } catch (Exception e) {
            throw new TreeHoleException("按天统计访问情况错误");
        }
    }

    /**
     * 访问来源
     * @return
     * @throws TreeHoleException
     */
    public List listVisitSources() throws TreeHoleException {
        List<LogModel> referers = this.logDao.listVisits();
        Map<String, Integer> refererMap = new HashMap<String, Integer>();
        try {
            for (LogModel log : referers) {
                String host = this.formateReferer(log.getReferer());
                if(refererMap.containsKey(host)){
                    refererMap.put(host,refererMap.get(host) + 1);
                }else{
                    refererMap.put(host, 1);
                }
            }
        } catch (MalformedURLException e) {
            throw new TreeHoleException("地址格式化错误");
        }
        Set<Map.Entry<String, Integer>> eneity = refererMap.entrySet();
        List list = new ArrayList();
        for (Map.Entry<String, Integer> ent : eneity) {
            list.add(new HashMap<String, Object>() {
                {
                    put("name",ent.getKey());
                    put("value", ent.getValue());
                }
            });
        }
        return list;
    }

    /**
     * 获取 host
     * @param referer
     * @return
     * @throws MalformedURLException
     */
    private String formateReferer(String referer) throws MalformedURLException {
        URL url = new URL(referer);
        return url.getHost();
    }

    /**
     * 访问 来源分布
     */
    public List<PieView> visitLocations() throws TreeHoleException {
        try {
            return this.logDao.getVisitBlogUris();
        } catch (Exception e) {
            throw new TreeHoleException("查询来源分布错误");
        }
    }

    /**
     * 访问博客页面统计
     * @param pageInfo
     * @return
     * @throws TreeHoleException
     */
    public List<PageVisitView> getVisitActionsByPage(PageInfo pageInfo) throws TreeHoleException {
        try {
            logger.info(pageInfo.toString());
            List<LogModel> visits = this.logDao.listVisitBlogsByPage(pageInfo);
            Integer count = this.logDao.getVisitBlogCount(pageInfo);
            pageInfo.setTotal(count);
            List<PageVisitView> pageVisits = new ArrayList<PageVisitView>();
            for (LogModel visit : visits) {
                pageVisits.add(new PageVisitView(visit.getId(), visit.getIp(), visit.getUri(), DateUtils.getDateBy(visit.getTimestamp(), "yyyy-MM-dd HH:mm:ss")));
            }
            return pageVisits;
        } catch (Exception e) {
            throw new TreeHoleException("访问页面查询错误");
        }
    }

    public List<Map> getVisitExplores() throws TreeHoleException {
        try {
            Map<String, Integer> exMap = new HashMap<String,Integer>();
            List result = new ArrayList();
            List<LogModel> visits = this.logDao.listVisitBlogs();
            for (LogModel visit : visits) {
                visit.setAgent(TreeHoleUtils.getExplore(visit.getAgent()));
                if(exMap.containsKey(visit.getAgent())){
                    exMap.put(visit.getAgent(), exMap.get(visit.getAgent()) + 1);
                }else{
                    exMap.put(visit.getAgent(), 1);
                }
            }
            for (String key : exMap.keySet()) {
                result.add(new HashMap(){
                    {
                        put("name", key);
                        put("value", exMap.get(key));
                    }
                });
            }
            return result;
        } catch (Exception e) {
            throw new TreeHoleException("浏览器使用情况统计错误");
        }
    }

    /**
     * 访问动作统计
     * @return
     * @throws TreeHoleException
     */
    public List<PieView> getVisitActions() throws TreeHoleException {
        try {
            return this.logDao.getVisitBlogActions();
        } catch (Exception e) {
            throw new TreeHoleException("查询访问动作统计错误");
        }
    }
}
