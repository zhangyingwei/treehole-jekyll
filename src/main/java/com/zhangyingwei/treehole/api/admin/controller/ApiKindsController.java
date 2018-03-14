package com.zhangyingwei.treehole.api.admin.controller;

import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.api.admin.service.ApiKindsService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.PageInfo;
import com.zhangyingwei.treehole.common.annotation.Auth;
import com.zhangyingwei.treehole.common.exception.TreeHoleApiException;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2018/3/11
 * @time: 下午8:15
 * @desc:
 */
@RestController
@RequestMapping("/api/admin/kinds")
@CrossOrigin
@Auth
public class ApiKindsController {

    private Logger logger = LoggerFactory.getLogger(ApiKindsController.class);

    @Autowired
    private ApiKindsService apiKindsService;

    @GetMapping
    public Map listKinds(PageInfo pageInfo) throws TreeHoleApiException {
        List<Kind> kinds = this.apiKindsService.listKindWithPage(pageInfo);
        Map result = new HashMap();
        result.put("kinds", kinds);
        result.put("page", pageInfo);
        return Ajax.success(result);
    }

    @PostMapping
    public Map addKind(Kind kind) throws TreeHoleApiException{
        this.apiKindsService.addKind(kind);
        return Ajax.success("添加文章分类成功");
    }

    @PostMapping("/update")
    public Map updateKind(Kind kind) throws TreeHoleApiException {
        this.apiKindsService.updateKind(kind);
        return Ajax.success("修改文章分类成功");
    }

    @DeleteMapping("/{id}")
    public Map deleteOneKind(@PathVariable("id") Integer id) throws TreeHoleApiException {
        this.apiKindsService.deleteOneKind(id);
        return Ajax.success("删除文章分类成功");
    }
}