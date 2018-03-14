package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.api.admin.service.ApiLogService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.utils.DateUtils;
import com.zhangyingwei.treehole.log.model.LogModel;
import com.zhangyingwei.treehole.log.model.PageVisitView;
import com.zhangyingwei.treehole.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: zhangyw
 * @date: 2018/3/7
 * @time: 下午9:03
 * @desc:
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class ApiLogController {
    @Autowired
    private ApiLogService apiLogService;

    @GetMapping("/logs")
    public Map listLogs(PageInfo pageInfo, LogModel logModel,String start,String end) throws TreeHoleApiException {
        try {
            String startTime = null;
            String endTime = null;
            if (null != start && null != end) {
                startTime = DateUtils.formateData(start).getTime()+"";
                endTime = DateUtils.formateData(end).getTime()+"";
            }
            List<LogModel> logs = this.apiLogService.listLogsWithPage(logModel, pageInfo, startTime, endTime);
            Map result = new HashMap();
            result.put("logs", logs);
            result.put("page", pageInfo);
            return Ajax.success(result);
        } catch (Exception e) {
            throw new TreeHoleApiException(e);
        }
    }

    @GetMapping("/visits/count")
    public Map visitCount() throws TreeHoleApiException {
        Integer count = this.apiLogService.visitCount();
        return Ajax.success(count);
    }

}
