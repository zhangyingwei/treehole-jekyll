package com.zhangyingwei.treehole.admin.controller;

import com.zhangyingwei.treehole.admin.model.Kind;
import com.zhangyingwei.treehole.admin.service.ArticleService;
import com.zhangyingwei.treehole.admin.service.KindService;
import com.zhangyingwei.treehole.common.Ajax;
import com.zhangyingwei.treehole.common.Pages;
import com.zhangyingwei.treehole.common.annotation.TreeHoleAtcion;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyw
 * @date: 2017/5/25
 * @time: 下午9:31
 * @desc:
 */
@Controller
@RequestMapping("/admin/articles/kinds")
public class KindController {
    @Autowired
    private KindService kindService;

    /**
     * 路由到分类页
     * @param model
     * @return
     * @throws TreeHoleException
     */
    @RequestMapping
    @TreeHoleAtcion("打开分类信息页面")
    public String indexKind(Map<String,Object> model) throws TreeHoleException {
        List<Kind> kinds = this.kindService.getKinds();
        model.put("kinds", kinds);
        return Pages.ADMIN_KIND;
    }

    /**
     * 通过id删除分类信息
     * @param id
     * @return
     * @throws TreeHoleException
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    @TreeHoleAtcion("删除分类信息")
    public Map<String,Object> deleteById(@PathVariable("id") String id,String type) throws TreeHoleException {
        if(type!=null){
            this.kindService.deleteKindById(id, type);
            return Ajax.success("删除分类信息成功");
        }else{
            return Ajax.error("请求参数错误");
        }
    }

    /**
     * 修改分类信息
     * @param kind
     * @return
     * @throws TreeHoleException
     */
    @PutMapping
    @ResponseBody
    @TreeHoleAtcion("修改分类信息")
    public Map<String,Object> editKindInfo(@Valid Kind kind) throws TreeHoleException {
        this.kindService.editKindInfo(kind);
        return Ajax.success("修改分类信息成功");
    }

    /**
     * 添加分类信息
     * @param kind
     * @return
     * @throws TreeHoleException
     */
    @PostMapping
    @ResponseBody
    @TreeHoleAtcion("添加分类信息")
    public Map<String,Object> addKindInfo(@Valid Kind kind) throws TreeHoleException {
        this.kindService.addKindInfo(kind);
        return Ajax.success("添加分类信息成功");
    }
}
