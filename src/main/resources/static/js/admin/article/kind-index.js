$(function () {
    kindFlag();
    function kindFlag(){
        var map = {
            0:"正常",
            9:"删除"
        }
        var flags = $(".kind-flag");
        $.each(flags,function(){
            const value = $.trim($(this).text());
            var className = "";
            if(value === "9"){
                className = "danger";
            }
            $(this).html("<span class='tag "+className+"'>"+map[value]+"</span>");
        })
    }

    $(".handEdit").click(function () {
        var index = $(this).attr("index");
        var box = this;
        //多窗口模式，层叠置顶
        var win = layer.open({
            type: 1
            ,title: '文章类别编辑'
            ,skin: 'kind-edit-window'
            ,area: ['500px', '260px']
            ,shade: 0
            ,maxmin: true
            ,content: $("#kind-edit-form-panel").html()
            ,btn: ['确定', '取消']
            ,yes: function(){
                var id = $(".kind-edit-window #kind-edit-form").find("#id").val();
                var name = $(".kind-edit-window #kind-edit-form").find("#name").val();
                var status = $(".kind-edit-window #kind-edit-form").find("#status").val();
                if(name&&(status>=0)){
                    $.ajax({
                        url: "/admin/articles/kinds",
                        type: "PUT",
                        data: {
                            id: id,
                            name: name,
                            flag: status
                        },
                        success: function(data){
                            layer.close(win);
                            if(data){
                                if(data.code===200){
                                    layer.msg("编辑分类信息成功",{
                                        icon: 6,
                                        zIndex: layer.zIndex,
                                        time: 1000
                                    },function () {
                                        window.location.href = "/admin/articles/kinds";
                                    });
                                }else{
                                    layer.alert(data.message,{
                                        zIndex: layer.zIndex
                                    },function () {
                                        window.location.href = "/admin/articles/kinds";
                                    })
                                }
                            }
                        },
                        error: function(){
                            layer.alert("编辑分类信息失败",{
                                zIndex: layer.zIndex
                            })
                        }
                    })
                }else{
                    layer.msg("验证未通过",{
                        zIndex: layer.zIndex
                    });
                }
            }
            ,btn2: function(){
                layer.closeAll();
            }
            ,zIndex: layer.zIndex
            ,success: function(layero){
                layer.setTop(layero);
                var id = $(box).parent().prev().prev().prev().text();
                var name = $(box).parent().prev().prev().text();
                var statu = $(box).parent().prev().attr("value");
                var id = $(".kind-edit-window #kind-edit-form").find("#id").val(id);
                $(".kind-edit-window #kind-edit-form").find("#name").val(name);
                $(".kind-edit-window #kind-edit-form").find("#status").val(statu);
            }
        });
    });
    /**
     * 删除
     */
    $(".handDelete").click(function () {
        var index = $(this).attr("index");
        layer.confirm('要彻底删除吗', {
            btn: ['是的','当然不是']
        }, function(){
            deleteByType(index,"any");
        }, function(){
            deleteByType(index,"state");
        });
    })

    function deleteByType(index,type) {
        layer.closeAll();
        var load = layer.load(1);
        $.ajax({
            url: "/admin/articles/kinds/"+index+"?type="+type,
            type: "DELETE",
            success: function(data){
                if(data){
                    if(data.code===200){
                        layer.close(load);
                        window.location.href = "/admin/articles/kinds";
                    }else{
                        layer.alert(data.message)
                    }
                }else{
                    layer.msg("无返回值");
                }
                layer.close(load)
            },
            error:function () {
                layer.msg("请求出错啦");
                layer.close(load);
            }
        })
    }

    /**
     * 添加
     */
    $("#addKindBtn").click(function () {
        var win = layer.open({
            type: 1
            ,title: '添加分类'
            ,skin: 'kind-edit-window'
            ,area: ['500px', '260px']
            ,shade: 0
            ,maxmin: true
            ,content: $("#kind-edit-form-panel").html()
            ,btn: ['确定', '取消']
            ,yes: function(){
                var name = $(".kind-edit-window #kind-edit-form").find("#name").val();
                var status = $(".kind-edit-window #kind-edit-form").find("#status").val();
                if(name&&(status>=0)){
                    $.ajax({
                        url: "/admin/articles/kinds",
                        type: "POST",
                        data: {
                            name: name,
                            flag: status
                        },
                        success: function(data){
                            layer.close(win);
                            if(data){
                                if(data.code===200){
                                    layer.msg("添加分类信息成功",{
                                        icon: 6,
                                        zIndex: layer.zIndex,
                                        time: 1000
                                    },function () {
                                        window.location.href = "/admin/articles/kinds";
                                    });
                                }else{
                                    layer.alert(data.message,{
                                        zIndex: layer.zIndex
                                    },function () {
                                        window.location.href = "/admin/articles/kinds";
                                    })
                                }
                            }
                        },
                        error: function(){
                            layer.alert("添加分类信息失败",{
                                zIndex: layer.zIndex
                            })
                        }
                    })
                }else{
                    layer.msg("验证未通过",{
                        zIndex: layer.zIndex
                    });
                }
            }
            ,btn2: function(){
                layer.closeAll();
            }
            ,zIndex: layer.zIndex
            ,success: function(layero){
                layer.setTop(layero);
                $(".kind-edit-window #kind-edit-form").find("#status").val(0);
                $(".kind-edit-window #kind-edit-form").find("#name").focus();
                $(".layui-layer").keydown(function (event) {
                    if(event.key==="Enter"){
                        $(".layui-layer-btn0").click();
                    }
                })
            }
        });
    })
})