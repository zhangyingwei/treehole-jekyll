$(function () {
    var charts = echartsInit();
    getVisitLocations(charts);
    getVisitExplore(charts);
    getVisitAction(charts);
    getVisitPage();
});
function echartsInit(){
    var charts = {
        visit:{
            pie:{
                chart:'',
                option:'',
                init:function(optiondata){
                    this.echartInit();
                    this.optionInit(optiondata);
                    return this;
                },
                echartInit:function(){
                    this.chart = echarts.init(document.getElementById("chart-pie-visit"));
                },
                optionInit:function(data){
                    this.option = {
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    }
                },
                create:function(){
                    this.chart.setOption(this.option)
                }
            },
            pie_explore:{
                chart:'',
                option:'',
                init:function(optiondata){
                    this.echartInit();
                    this.optionInit(optiondata);
                    return this;
                },
                echartInit:function(){
                    this.chart = echarts.init(document.getElementById("chart-pie-explore"));
                },
                optionInit:function(data){
                    this.option = {
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '浏览器',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    }
                },
                create:function(){
                    this.chart.setOption(this.option)
                }
            },
            pie_actoin:{
                chart:'',
                option:'',
                init:function(optiondata){
                    this.echartInit();
                    this.optionInit(optiondata);
                    return this;
                },
                echartInit:function(){
                    this.chart = echarts.init(document.getElementById("chart-pie-action"));
                },
                optionInit:function(data){
                    this.option = {
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '访问动作',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    }
                },
                create:function(){
                    this.chart.setOption(this.option)
                }
            }
        }
    }
    return charts;
}

/**
 * 获取用户分部信息
 * @param charts
 */
function getVisitLocations(charts){
    var pie_data = [
        {value:335, name:'中国,北京,北京'},
        {value:310, name:'中国,山西,大同'},
        {value:234, name:'中国,浙江,杭州'},
        {value:135, name:'中国,广西,桂林'},
        {value:1548, name:'局域网,局域网'}
    ];
    $.ajax({
        url: "/log/visits/location",
        type: "GET",
        success: function (data) {
            if(data.code === 200){
                charts.visit.pie.init(data.result.data).create();
            }else{
                charts.visit.pie.init(pie_data).create();
            }
        },
        error: function () {
            charts.visit.pie.init(pie_data).create();
        }
    })
}

/**
 * 访问浏览器统计数据获取
 * @param charts
 */
function getVisitExplore(charts){
    var pie_data = [
        {value:335, name:'ie'},
        {value:310, name:'chrome'},
        {value:234, name:'edge'}
    ];
    $.ajax({
        url: "/log/visits/explore",
        type: "GET",
        success: function (data) {
            if (data.code === 200) {
                charts.visit.pie_explore.init(data.result.data).create();
            } else {
                charts.visit.pie_explore.init(pie_data).create();
            }
        },
        error: function () {
            charts.visit.pie_explore.init(pie_data).create();
        }
    });
}

/**
 * 访问动作统计数据获取
 * @param charts
 */
function getVisitAction(charts){
    var pie_data = [
        {value:335, name:'打开管理端首页'},
        {value:310, name:'访问来源统计'},
        {value:234, name:'按天统计访问量'}
    ];
    $.ajax({
        url: "/log/visits/actions/blog",
        type: "GET",
        success: function (data) {
            if (data.code === 200) {
                charts.visit.pie_actoin.init(data.result.data).create();
            } else {
                charts.visit.pie_actoin.init(pie_data).create();
            }
        },
        error: function () {
            charts.visit.pie_actoin.init(pie_data).create();
        }
    });
}

/**
 * 获取访问页面信息
 */
function getVisitPage(){
    var data = [{
        id: 1, ip: 2, uri: 3, timestamp: 4
    }];
    $.ajax({
        url:"/log/visits/actions",
        data: {
            pageSize:10,
        },
        type: "GET",
        success: function (data) {
            if(data.code === 200){
                var tableHtml = bulidTableHtml(data.result.data.data);
                $(".uri-table").find("table").append(tableHtml);
                bulidPages(data.result.data.page);
            }else{
                layer.msg(data.message);
            }
        },
        error: function () {
            layer.msg("加载页面访问信息错误");
        }
    })
}

function bulidPages(page){
    $.jqPaginator('#page', {
        totalPages: page.totalPages,
        visiblePages: 5,
        currentPage: page.current,
        // prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        // next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        // page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (index, type) {
            var load = layer.load(1);
            $.ajax({
                url:"/log/visits/actions",
                data: {
                    pageSize:10,
                    current: index
                },
                type: "GET",
                success: function (data) {
                    if(data.code === 200){
                        var tableHtml = bulidTableHtml(data.result.data.data);
                        $(".uri-table").find("table").find("tbody").remove();
                        $(".uri-table").find("table").append(tableHtml);
                        // bulidPages(data.result.data.page);
                    }else{
                        layer.msg(data.message);
                    }
                    layer.close(load);
                },
                error: function () {
                    layer.msg("加载页面访问信息错误");
                    layer.close(load);
                }
            })
        }
    });
    // new PageInfo({
    //     el: $("#page"),
    //     pageSize:page.pageSize,
    //     total:page.total,
    //     current:page.current,
    //     actions:{
    //         change: function(index){
    //             var load = layer.load(1)
    //             $.ajax({
    //                 url:"/log/visits/actions",
    //                 data: {
    //                     pageSize:10,
    //                     current: index
    //                 },
    //                 type: "GET",
    //                 success: function (data) {
    //                     if(data.code === 200){
    //                         var tableHtml = bulidTableHtml(data.result.data.data);
    //                         $(".uri-table").find("table").find("tbody").remove();
    //                         $(".uri-table").find("table").append(tableHtml);
    //                         bulidPages(data.result.data.page);
    //                     }else{
    //                         layer.msg(data.message);
    //                     }
    //                     layer.close(load);
    //                 },
    //                 error: function () {
    //                     layer.msg("加载页面访问信息错误");
    //                     layer.close(load);
    //                 }
    //             })
    //         }
    //     }
    // });
}

function bulidTableHtml(data){
    if(!data){
        return "";
    }
    var html = "";
    html += "<tbody>";
    $.each(data,function(){
        html += "<tr>";
        const id = this.id;
        const ip = this.ip;
        const uri = this.uri;
        const datetime = this.datetime;
        html += "<td style='max-width: 20px;'>" + id + "</td>";
        html += "<td>" + ip + "</td>";
        html += "<td>" + uri + "</td>";
        html += "<td>" + datetime + "</td>";
        html+="</tr>"
    })
    html += "</tbody>";
    return html;
}