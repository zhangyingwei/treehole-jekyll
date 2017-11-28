layui.use(['upload','table','layer'],function () {
    var $ = layui.jquery;
    var upload = layui.upload;
    var table = layui.table;
    var layer = layui.layer
    var fileContainer = $("#inner_file_container");
    //多图片上传
    upload.render({
        elem: '#file_upload_button'
        ,url: '/admin/files/upload'
        ,multiple: true
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#file_show').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            });
        }
        ,done: function(res){
            //上传完毕
            layer.msg("上传完毕")
            loadImageTable();
        }
    });

    table.render({
        id:'file_upload_button',
        elem: '#file_content_table'
        ,height: 315
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:50, sort: true, fixed: 'left'}
            ,{title: "预览",templet: "#showTemplate",width: 120}
            ,{field: 'name', title: '名称', width:200}
            // ,{field: 'contentType',title: "类型",width: 120}
            ,{field: 'path', title: '地址', width:300}
            // ,{field: "alias", title: "别名",width: 200}
        ]]
    });

    function loadImageTable(){
        $.ajax({
            url: '/admin/files/list',
            type: 'GET',
            success: function (data) {
                var data = data.result.data;
                var url = window.location.origin;
                data = data.map(function (item) {
                    var path = item.path;
                    var alias = item.alias;
                    path = url+'/files/' + alias;
                    item.path = path
                    return item
                });
                table.reload('file_upload_button', {
                    data: data
                })
            },
            error: function (res) {
                console.log(res)
            }
        });
    }

    loadImageTable()
});