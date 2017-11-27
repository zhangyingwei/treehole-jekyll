layui.use(['upload','table'],function () {
    var $ = layui.jquery;
    var upload = layui.upload;
    var table = layui.table;
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
        }
    });

    table.render({
        id:'file_upload_button',
        elem: '#file_content_table'
        ,height: 315
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
            ,{field: 'name', title: '用户名', width:80}
            ,{field: 'sex', title: '性别', width:80, sort: true}
            ,{field: 'city', title: '城市'}
        ]]
    });

    $.ajax({
        url: '/admin/files/list',
        type: 'GET',
        success: function (data) {
            table.reload('file_upload_button',{
                data: data.result.data
            })
        },
        error: function (res) {
            console.log(res)
        }
    })
});