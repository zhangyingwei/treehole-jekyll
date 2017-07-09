package com.zhangyingwei.treehole.admin.log.controller;

import com.zhangyingwei.treehole.admin.log.model.PageVisitView;
import com.zhangyingwei.treehole.admin.log.model.PieView;
import com.zhangyingwei.treehole.admin.log.service.LogService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/visits/count")
    @ResponseBody
    @TreeHoleAtcion("获取访问总人数")
    public Map<String,Object> getVisitCount() throws TreeHoleException {
        Integer count = this.logService.getVisitCount();
        return Ajax.success(count);
    }

    @GetMapping("/visits/count/days")
    @ResponseBody
    @TreeHoleAtcion("按天统计访问量")
    public Map<String,Object> countByDay() throws TreeHoleException {
        Object[] result = this.logService.listCountByDay();
        return Ajax.success(result);
    }

    @GetMapping("/visits/sources")
    @ResponseBody
    @TreeHoleAtcion("访问来源统计")
    public Map<String,Object> visitsSources() throws TreeHoleException {
        List result = this.logService.listVisitSources();
        return Ajax.success(result);
    }

    @GetMapping("/visits/location")
    @ResponseBody
    @TreeHoleAtcion("访问分部统计")
    public Map<String,Object> visitLocation() throws TreeHoleException {
        List<PieView> locations = this.logService.visitLocations();
        return Ajax.success(locations);
    }

    @GetMapping("/visits/actions")
    @ResponseBody
    @TreeHoleAtcion("访问页面统计")
    public Map<String,Object> visitActions(PageInfo pageInfo) throws TreeHoleException {
        List<PageVisitView> pages = this.logService.getVisitActionsByPage(pageInfo);
        return Ajax.success(new HashMap(){
            {
                put("data", pages);
                put("page", pageInfo);
            }
        });
    }

    @GetMapping("/visits/explore")
    @ResponseBody
    @TreeHoleAtcion("浏览器使用情况统计")
    public Map<String,Object> visitExplores() throws TreeHoleException {
        List<Map> result = this.logService.getVisitExplores();
        return Ajax.success(result);
    }

    @GetMapping("/visits/actions/blog")
    @ResponseBody
    @TreeHoleAtcion("访客动作统计")
    public Map<String,Object> visitActions() throws TreeHoleException {
        List<PieView> result = this.logService.getVisitActions();
        return Ajax.success(result);
    }
}