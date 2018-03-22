$(function () {

})

window.newWindow = function (dom, option, ok, esc) {
    return layer.open({
        title: $(dom).attr("title"),
        type: 1,
        maxmin: true,
        area: [option.width, option.height], //宽高
        btn: ["确定", "返回"],
        yes: function () {
            return ok()
        },
        btn2: function () {
            esc ? esc : function () {
                layer.closeAll();
            }
        },
        content: $(dom).prop("outerHTML")
    });
};

window.copyMsg = function(inputEl){
    $(this).parent().find("input").select();
    document.execCommand("Copy");
    layer.msg("小主，内容已经复制到剪贴板");
}