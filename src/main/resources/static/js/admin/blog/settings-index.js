$(function () {
    $("#edit-userinfo").on("click",function () {
        var btnValue = $(this).attr("value");
        if(btnValue === "edit"){
            $(this).attr("value","save");
            $(this).html("<i class='fa fa-save m-right-xs'></i>保存");
            $("#user_info").find("input").attr("disabled", false);
        }else if(btnValue === "save"){
            var userInfo = getUserInfo();
            layer.msg(JSON.stringify(userInfo));
            $(this).attr("value","edit");
            $(this).html("<i class='fa fa-edit m-right-xs'></i>编辑");
            $("#user_info").find("input").attr("disabled", "disabled");
        }
    })
});

var getUserInfo = function () {
    var ins = $("#user_info").find("input");
    var result = {};
    $.each(ins, function () {
        var id = $(this).attr("id");
        var value = $(this).val();
        result[id] = value;
    });
    return result;
}