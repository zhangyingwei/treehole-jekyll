package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.log.model.PageVisitView;
import com.zhangyingwei.treehole.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/3/7
 * @time: 下午9:03
 * @desc:
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class SysLogController {
    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public Map listLogs(PageInfo pageInfo) throws TreeHoleException {
        List<PageVisitView> logs = this.logService.getVisitActionsByPage(pageInfo);
        Map result = new HashMap();
        result.put("page", pageInfo);
        result.put("logs", logs);
        return Ajax.success(result);
    }

}
