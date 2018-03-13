package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.model.FileRes;
import com.zhangyingwei.treehole.api.admin.service.ApiResourcesService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/3/12
 * @time: 下午9:28
 * @desc:
 */
@RestController
@RequestMapping("/api/admin/resources")
@CrossOrigin
public class ApiResourcesController {

    private Logger logger = LoggerFactory.getLogger(ApiResourcesController.class);
    @Autowired
    private ApiResourcesService apiResourcesService;

    @GetMapping
    public Map listResourcesWithPage(PageInfo pageInfo, FileRes fileRes) throws TreeHoleApiException {
        List<FileRes> resources = this.apiResourcesService.listFilesWithPage(pageInfo, fileRes);
        Map result = new HashMap();
        result.put("resources", resources);
        result.put("page", pageInfo);
        return Ajax.success(result);
    }

    @DeleteMapping("/{id}")
    public Map deleteResourcesById(@PathVariable("id") Integer id) throws TreeHoleApiException {
        this.apiResourcesService.deleteById(id);
        return Ajax.success("删除成功");
    }

}
