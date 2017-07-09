Dropzone.autoDiscover = false;
$(function () {
    var fileContainer = $("#file_container");
    var table = fileContainer.find("table");
    var refreshBtn = fileContainer.find(".refresh-link");

    $("#fileUpload").dropzone({
        success: function() {
            getFileInfos();
        }
    });

    refreshBtn.click(function () {
        getFileInfos();
    })

    function getFileInfos() {
        var index = layer.load(1);
        $.ajax({
            url: "/admin/files/list",
            type: "GET",
            dataType: "json",
            success: function (data) {
                if (data) {
                    if (data.code === 200) {
                        bulidTable(data.result.data)
                    } else {
                        layer.alert(data.message);
                    }
                } else {
                    layer.alert("获取数据失败")
                }
                layer.close(index);
            },
            error: function () {
                layer.close(index);
            }
        })
    }

    function bulidTable(files) {
        var url = window.location.origin;
        var tbody = fileContainer.find("table tbody")
        //清空表格中的数据
        tbody.html("")
        var bodyHtml = "";
        $.each(files, function () {
            var uurl = url + "/files/" + this.alias;
            bodyHtml += "<tr>";
            bodyHtml += "<td>" + this.id + "</td>";
            bodyHtml += "<td>" + this.name + "</td>";
            bodyHtml += "<td>" + this.contentType + "</td>";
            bodyHtml += "<td><input style='padding: 3px;width: 250px;' readonly='readonly' type='text' value='" + uurl + "'><a class='copyContent'> [复制]<a></td>";
            bodyHtml += "<td><i class='fa fa-eye showDetail'></i></td>";
            bodyHtml += "<td><button class='btn btn-danger btn-sm deleteBtn' value='"+this.id+"'>删除</td>";
            bodyHtml += "</tr>";
        });
        tbody.html(bodyHtml);

        $(".deleteBtn").click(function () {
            var id = $(this).val();
            var index = layer.load(1);
            $.ajax({
                url : "/admin/files/"+ id,
                type: "DELETE",
                success: function(data){
                    if(data.code === 200){
                        layer.msg("删除素材成功");
                        getFileInfos();
                    }else{
                        layer.msg(data.message);
                    }
                    layer.close(index);
                },
                error: function(data){
                    layer.close(index);
                    layer.msg(data.message)
                }
            })
        })

        $(".showDetail").click(function () {
            var url = $(this).parent().prev().find("input").val();
            var contentType = $(this).parent().prev().prev().text();
            var validContentTyope = ["image/png", "image/jpg", "image/jpeg", "image/gif"];
            if($.inArray(contentType,validContentTyope)<0){
                layer.msg("暂时无法预览此类文件");
                return;
            }
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                area: "80%",
                skin: 'img-show',
                content: "<img src='"+url+"'>"
            });
        });

        $(".copyContent").click(function () {
            $(this).parent().find("input").select();
            document.execCommand("Copy");
            layer.msg("小主，内容已经复制到剪贴板");
        });
    }
    getFileInfos();
});