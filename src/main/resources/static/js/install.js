/**
 * Created by zhangyw on 2017/4/23.
 */
$(function(){
    var data = {}
    var index = 0;
    var canNext = true;
    var dbName = $("#dbselect").val();//当前选择数据库
    const steps = getSetps();//select all steps
    console.log("find "+ steps.length +" steps")
    showSteps(steps,index)
    firstStep()
    addValieListener();
    addDbSelectLinstener();
    $("#prew").click(function () {
        prew(prewStep)
    })

    $("#next").click(function () {
        next(nextStep)
    })

    $("#submit").click(function(){
        submit()
    })

    function addValieListener(){
        $(".not-empty").blur(function(){
            const text = $(this).val()
            if(!$(this).hasClass("hidden")){
                if(is.empty(text)){
                    layer.tips("必填哦",$(this))
                    $(this).parent().addClass("has-error")
                    canNext = false
                }else{
                    $(this).parent().removeClass("has-error")
                    canNext = true
                }
            }
        })
    }

    /**
     * change the database we will use
     */
    function addDbSelectLinstener(){
        console.log("addDbSelectLinstener");
        $("#dbselect").change(function(){
            dbName = $(this).val();
            changeDatabase();
        })
    }

    /**
     *
     * 如果数据库使用sqlite，则需要隐藏掉一些输入框
     */
    function changeDatabase(){
        console.log("change database:" + dbName);
        if(dbName === 'sqlite'){
            $("#input-group-username").addClass("hidden");
            $("#input-group-password").addClass("hidden");
        }else if(dbName === 'mysql'){
            $("#input-group-username").removeClass("hidden");
            $("#input-group-password").removeClass("hidden");
        }
    }

    /**
     * show next step
     */
    function next(callBack){
        console.log("next",index)
        console.log(index<steps.length-1)
        var validToNext = true;
        if(index<steps.length-1){
            validToNext = validStep(steps[index]) && callBack(steps[index],index)
            if(validToNext){
                closeStep(steps,index)
                index++
                showSteps(steps,index)
                notFirstStep()
            }
        }
        if(index>=steps.length-1){
            if(validToNext){
                lastStep()
            }
        }
    }

    /**
     * show prew step
     */
    function prew(callBack){
        console.log("prew")
        if(index>0){
            callBack(steps[index])
            closeStep(steps,index)
            index--
            showSteps(steps,index)
            notLastStep()
        }
        if(index<=0){
            firstStep()
        }
    }

    function firstStep(){
        $("#prew").addClass("disabled")
    }
    function notFirstStep(){
        $("#prew").removeClass("disabled")
    }
    function lastStep() {
        $("#next").addClass("disabled")
        $("#submit").removeClass("hidden")
    }
    function notLastStep() {
        $("#next").removeClass("disabled")
        $("#submit").addClass("hidden")
    }

    /**
     * get all step divs
     * @returns {jQuery|HTMLElement}
     */
    function getSetps(){
        return $(".steps").find(".step")
    }

    /**
     * show one step
     */
    function showSteps() {
        var text = $(steps[index]).children(".msg").text()
        var component;
        if(index === 0){
            $("#dbselect").css("display", "inline");
            $("#dbselect").show();
            changeDatabase(dbName)
            component = $(steps[index]).find(".component").children();
        }else{
            $("#dbselect").hide();
        }
        if(component){
            $("#step-tip").append(component);
        }else{
            $("#step-tip").text("");
        }
        $("#step-tip").prepend("第 " + (index + 1) + " 步" +" ["+" "+text+" "+"] ")
        $(steps[index]).show(0)
    }

    /**
     * close one step
     */
    function closeStep(){
        $(steps[index]).hide(0)
    }

    /**
     * call when click prew button
     * @param step
     */
    function prewStep(step){
        console.log("prewStep")
    }

    /**
     * call when click next button
     * @param step
     * @returns {boolean}
     */
    function nextStep(step,index){
        var type = $(step).find(".info").text();
        var resultFlag = true;
        if(type === 'database'){
            var db = {}
            var inputs = $(step).find("input")
            db.url = $(inputs[0]).val();
            db.username = $(inputs[1]).val();
            db.password = $(inputs[2]).val();
            var loadIndex = layer.load(1);
            $.ajax({ //valid database infomation
                type: "POST",
                async:false,
                url:"/install/db/"+dbName,
                data:db,
                success:function(data){
                    layer.close(loadIndex)
                    if(data.message === "验证成功"){
                        resultFlag = true;
                    }else{
                        layer.msg("数据库验证失败");
                        resultFlag = false;
                    }
                }
            })
        }
        return resultFlag;
    }

    /**
     * valid one step infomation
     * @param step
     */
    function validStep(step){
        var flag = true;
        var notEmptys = $(step).find(".not-empty")
        for (var i = 0;i<notEmptys.length;i++){
            if((!$(notEmptys[i]).parent().hasClass("hidden")) && is.empty($(notEmptys[i]).val())){
                $(notEmptys[i]).parent().addClass("has-error")
                flag = false;
            }else{
                flag = true;
            }
        }
        if(!flag){
            layer.msg("请按要求完善信息")
        }
        return flag;
    }

    /**
     * infomation submit function
     *
     * 1 valid admin infomation
     * 2 submit all info
     */
    function submit(){
        validStep(steps[steps.length-1])
        validPasswd()
        if(!canNext){
            return;
        }
        var loading = layer.load(1)
        var data = {}
        data.url = $("input[name=dburl]").val();
        data.username = $("input[name=dbusername]").val();
        data.password = $("input[name=dbpassword]").val();
        data.blogname = $("input[name=blogname]").val();
        data.blogurl = $("input[name=blogurl]").val();
        data.blogdesc = $("textarea[name=blogdesc]").val();
        data.adminusername = $("input[name=adminusername]").val();
        data.adminpassword = $("input[name=adminpassword]").val();

        /**
         * dbinfo
         * @type {{}}
         */
        var dbinfo = {}
        dbinfo.url = data.url;
        dbinfo.username = data.username;
        dbinfo.password = data.password;
        /**
         * bloginfo
         * @type {{}}
         */
        var bloginfo = {};
        bloginfo.name = data.blogname;
        bloginfo.url = data.blogurl;
        bloginfo.desc = data.blogdesc;

        /**
         * admininfo
         * @type {{}}
         */
        var admininfo = {};
        admininfo.username = data.adminusername;
        admininfo.password = data.adminpassword;

        /**
         * init blog system step
         *  1 init database
         *  2 init bloginfo
         *  3 init admininfo
         */
        if(dbMake(dbinfo)){
            if(blogInit(bloginfo)){
                if(adminInit(admininfo)){
                    layer.alert("treehole博客安装成功,请开始你的博客之旅吧!",function(){
                        //跳转到管理端
                        window.location.href = "/admin";
                    });
                }
            }
        }
        layer.close(loading);
    }

    /**
     * 初始化数据库
     * @param dbinfo
     * @returns {boolean}
     */
    function dbMake(dbinfo){
        layer.msg("正在初始化数据库")
        var initsucc = false;
        $.ajax({
            type: "POST",
            async:false,
            url:"/install/db/make",
            data:dbinfo,
            success: function(data){
                layer.msg("初始化数据库成功")
                initsucc = true;
            },
            error: function (data) {
                layer.msg("初始化数据库失败")
                initsucc = false;
            }
        })
        return initsucc;
    }
    /**
     * 初始化博客信息
     * @param bloginfo
     * @returns {boolean}
     */
    function blogInit(bloginfo){
        layer.msg("正在初始化博客")
        var initsucc = false;
        $.ajax({
            type: "POST",
            async:false,
            url:"/install/blog/init",
            data:bloginfo,
            dataType: 'json',
            success: function(data){
                if(data.code == 200){
                    initsucc = true;
                }else{
                    initsucc = false;
                }
                layer.msg(data.message);
            },
            error: function (data) {
                layer.msg("初始化博客失败")
                initsucc = false;
            }
        })
        return initsucc;
    }

    /**
     * 初始化管理信息
     * @param admininfo
     */
    function adminInit(admininfo){
        layer.msg("正在初始化管理端")
        var initsucc = false;
        $.ajax({
            type: "POST",
            async:false,
            url:"/install/admin/init",
            data:admininfo,
            dataType: 'json',
            success: function(data){
                if(data.code == 200){
                    initsucc = true;
                }else{
                    initsucc = false;
                }
                layer.msg(data.message)
            },
            error: function (data) {
                layer.msg("初始化管理端失败")
                initsucc = false;
            }
        })
        return initsucc;
    }

    /**
     * valid passwd and qrpasswd
     */
    function validPasswd(){
        var pass = $("#passwd").val()
        var qrpass = $("#qrpasswd").val()
        console.log(pass,qrpass)
        if(!is.equal(pass,qrpass)){
            canNext = false
            layer.msg("两次密码不相同")
        }
    }
})