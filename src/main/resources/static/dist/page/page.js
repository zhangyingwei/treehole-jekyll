/**
 * Created by zhangyw on 2017/6/16.
 */

var PageInfo = function PageInfo(options) {
    this.pageSize = options.pageSize;
    this.total = options.total;
    this.current = options.current;
    this.el = options.el;
    this.actions = {};
    this.actions.change = options.actions.change;
    this.init();
};

PageInfo.prototype.init = function () {
    if(!this.current) this.current = 1;
    this.getTotalPage();
    this.createPages();
    this.loadCss();
    this.addListener();
    this.select(this.current,true);
};
PageInfo.prototype.getTotalPage = function () {
    if(this.total%this.pageSize==0){
        this.totalPage = this.total / this.pageSize;
    }else{
        this.totalPage = Math.floor(this.total / this.pageSize) + 1;
    }
};
PageInfo.prototype.createPages = function () {
    this.el.html("");
    this.el.append(this.bulidHtml());
    this.el.append("<div class='clearfix'></div>");
};
PageInfo.prototype.bulidHtml = function () {
    var html = "<ul class='treehole-page'>";
    html += "<li class='before'>&lt;</li>";
    var flag = false;
    var current = this.current;
    for(var i = 1;i<=this.totalPage;i++){
        // html += this.bulidItem(i);
        // if(i<=3||i>this.pageSize-3){
        //     html += this.bulidItem(i);
        // }else{
        //     if(!flag){
        //         html += "<li class='noclick'> ... </li>";
        //         flag = true;
        //     }
        // }
        if(i == this.totalPage || (current-i>-2 && current-1<2)){
            html += this.bulidItem(i);
        }else{
            if(!flag){
                html += "<li class='noclick'> ... </li>";
                flag = true;
            }
        }
    }
    html += "<li class='after'>&gt;</li>";
    return html;
};
PageInfo.prototype.bulidItem = function (index) {
    return "<li>"+index+"</li>";
};
/**
 * 加载css
 */
PageInfo.prototype.loadCss = function () {
    $("<link>")
        .attr({ rel: "stylesheet",
            type: "text/css",
            href: "/dist/page/page.css"
        })
        .appendTo("head");
};
PageInfo.prototype.addListener = function () {
    var self = this;
    $(".treehole-page").find("li").click(function () {
        var index = $(this).text();
        if(index!="..." && index != "<" && index!=">"){
            self.current = index;
            self.select(index);
        }else if(index === "<"){
            self.before();
        }else if(index === ">"){
            self.after();
        }
    })
};
PageInfo.prototype.change = function (index) {
    this.actions.change(index);
};
PageInfo.prototype.before = function () {
    if(this.current === 1){
        layer.msg("已经是第一页");
    }else{
        this.current = this.current - 1;
        this.select(this.current);
    }
};
PageInfo.prototype.after = function () {
    if(this.current>=this.totalPage){
        layer.msg("已经是最后一页");
    }else{
        this.current = this.current + 1;
        this.select(this.current);
    }
};

/**
 * 选中
 */
PageInfo.prototype.select = function (index,init) {
    var self = this;
    if(typeof index === "number") index = index + "";
    $.each($(".treehole-page").find("li"), function () {
        if($(this).text()===index){
            if(!($(this).hasClass("noclick")||$(this).hasClass("before")||$(this).hasClass("after"))){
                self.unSelectAll();
                $(this).addClass("select");
                if(!init){
                    self.change($(this).text())
                }
            }
        }
    });
};
/**
 * 取消全部选中
 */
PageInfo.prototype.unSelectAll = function () {
    $.each($(".treehole-page").find("li.select"),function () {
        $(this).removeClass("select");
    })
};