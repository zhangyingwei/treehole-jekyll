$(function () {
    /**
     * 发布
     */
    $(".btn-publish").click(function () {
        var id = $(this).val();
        var index = layer.load(1);
        $.ajax({
            url: "/admin/articles/publish/" + id,
            type: "PUT",
            success: function (data) {
                layer.close(index);
                if (data.code === 200) {
                    layer.msg("发布成功", {icon: 6, time: 500}, function () {
                        window.location.href = "/admin/articles/history";
                    })
                } else {
                    layer.msg("发布失败：" + data.message);
                }
            },
            error: function () {
                layer.close(index);
                layer.msg("发布失败");
            }
        })
    })

    /**
     * 编辑
     */
    $(".btn-edit").click(function () {
        var id = $(this).val();
        window.location.href = "/admin/articles/" + id;
    })

    $(".btn-delete").click(function () {
        var id = $(this).val();
        layer.confirm('要彻底删除吗', {
            btn: ['是的', '当然不']
        }, function () {
            deleteArticle(id, "delete");
        }, function () {
            deleteArticle(id, "state");
        });
    })

    var deleteArticle = function (id, state) {
        var index = layer.load(1);
        $.ajax({
            url: "/admin/articles/" + id + "?state=" + state,
            type: "DELETE",
            success: function (data) {
                layer.close(index);
                if (data.code === 200) {
                    layer.msg("删除成功", {icon: 6, time: 500}, function () {
                        window.location.href = "/admin/articles/history";
                    })
                } else {
                    layer.msg("删除失败：" + data.message);
                }
            },
            error: function () {
                layer.close(index);
                layer.msg("删除失败");
            }
        })
    }

    var toobar = $(".history-toobar");

    /**
     * 查询点击事件
     */
    toobar.find("#query").click(function () {
        var title = toobar.find("#title").val();
        if (title && title.length > 0) {
            findByTitle(title);
        }else{
            var trs = getTableTrs();
            $.each(trs,function () {
                $(this).show();
            })

        }
    });

    toobar.find(".statubtn").click(function () {
        layer.load(1);
        var value = $(this).val();
        $.each(toobar.find(".statubtn"), function () {
            $(this).removeClass("active");
        });
        $(this).addClass("active");
        var trs = getTableTrs();
        var statu = -1;
        if (value === "pub") {
            statu = 1;
        } else if (value === "nopub") {
            statu = 0;
        } else if (value === "del") {
            statu = 9;
        }
        showAllTrs();
        if (statu != -1) {
            $.each(trs, function () {
                var sta = $(this).find("td").find(".statu").attr("value");
                if (sta.toString() != statu.toString()) {
                    $(this).hide();
                }
            });
        }
        layer.closeAll();
    });

    /**
     * 获取所有表格行
     * @returns {*|jQuery}
     */
    var getTableTrs = function () {
        var trs = $("table").find("tbody").find("tr");
        return trs;
    }

    /**
     * 显示全部表格行
     */
    var showAllTrs = function () {
        $.each(getTableTrs(), function () {
            $(this).show();
        });
    }

    /**
     * 根据文章标题查询
     */
    var findByTitle = function (title) {
        var index = layer.load(1);
        var trs = getTableTrs();
        $.each(trs, function () {
            var artTitle = $(this).find(".art-title").text();
            $(this).show();
            if(artTitle.indexOf(title) == -1){
                $(this).hide();
            }
        });
        layer.close(index);
    }

    $(".kind-all").click(function () {
        layer.msg("all");
    })
    $(".kind-item").click(function () {
        var name = $(this).text();
        layer.msg("item"+name);
    })
});