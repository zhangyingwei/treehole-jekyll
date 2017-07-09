$(function () {
    $("#edit-profile").click(function(){
        layer.open({
            type: 1 //此处以iframe举例
            ,title: '修改博客基础信息'
            ,area: ['700px', '320px']
            ,shade: 0
            ,maxmin: true
            ,content: $("#bloginfo-profile-edit")
            ,btn: ['提交', '取消']
            ,yes: function(){
                const layerWin = $(".layui-layer-content");
                let name = layerWin.find("#blogname").val();
                let url = layerWin.find("#blogurl").val();
                let desc = layerWin.find("#blogdesc").val();
                layer.load(1);
                $.ajax({
                    url: "/admin/blog/basic",
                    type: "PUT",
                    data: {
                        name:name,
                        url: url,
                        desc: desc
                    },
                    dataType: "json",
                    success: function (data) {
                        layer.closeAll();
                        if(data){
                            if(data.code === 200){
                                layer.msg("修改博客基础信息成功",{},function () {
                                    window.location.href = "/admin/blog/basic";
                                })
                            }else{
                                layer.msg(data.message);
                            }
                        }else{
                            layer.msg("修改博客基础信息失败");
                        }
                    },
                    error: function (data) {
                        console.log(data);
                        layer.closeAll();
                        if(data){
                            layer.msg(data.message)
                        }else{
                            layer.msg("修改博客基础信息失败");
                        }
                    }
                });
                return false;
            }
            ,btn2: function(){
                layer.closeAll();
            }
            ,zIndex: layer.zIndex
            ,success: function(layero){
                layer.setTop(layero);

                let name = $("#blog-name").html();
                let url = $("#blog-url").html();
                let desc = $("#blog-desc").html();

                const layerWin = $(".layui-layer-content");
                layerWin.find("#blogname").val(name);
                layerWin.find("#blogurl").val(url);
                layerWin.find("#blogdesc").text(desc);
            }
        });
        // var win = window.newWindow($("#bloginfo-profile-edit"), {
        //     width: '700px',
        //     height: '350px'
        // },function () {
        //     var name = $("#name").val();
        //     var url = $("#url").val();
        //     var desc = $("#desc").text();
        //     layer.load(1);
        //     $.ajax({
        //         url: "/admin/blog/basic",
        //         type: "PUT",
        //         data: {
        //             name:name,
        //             url: url,
        //             desc: desc
        //         },
        //         dataType: "json",
        //         success: function (data) {
        //             console.log(data);
        //             layer.closeAll();
        //         },
        //         error: function (data) {
        //             console.log(data);
        //             layer.closeAll();
        //         }
        //     });
        //     return false;
        // },function () {
        //     console.log("2");
        //     return false;
        // });
    })
})