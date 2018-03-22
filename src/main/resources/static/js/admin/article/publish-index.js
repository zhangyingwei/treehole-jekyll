$(function(){

    var switchery = new Switchery($(".js-switch")[0], {
        color: '#26B99A'
    });
    $(".js-switch").on("click", function () {
        if (switchery.isChecked()) {
            $("#usecommont").val("on");
        } else {
            $("#usecommont").val("off");
        }
    });

    /**
     * markdown编辑器
     * @type {{model: string, spliter: string, make: make, init: init, getContext: getContext, getIntro: getIntro, hasIntro: hasIntro, formateArticle: formateArticle, getArticle: getArticle, validArticle: validArticle}}
     */
    var MD = {
        model: "",
        spliter: "<!-- more -->",
        make: function () {
            const self = this;
            this.model = new SimpleMDE({
                element: $("#editor")[0],
                hideIcons: ["guide", "heading"],
                toolbar: [{
                    name: "Insert Image",
                    action: function customFunction(editor) {
                        self.showImages(editor);
                    },
                    className: "fa fa-picture-o",
                    title: "Image Button"
                }, "link", "|", "table", "code", "|", "preview", "side-by-side", "fullscreen"],
                tabSize: 4
            });
            return this;
        },
        init: function () {
            if (!this.model.value()) {
                this.model.value("这里是文章简介 \n\n<!-- more --> \n\n这里是文章内容")
            }
            return this;
        },
        getContext: function () {
            return this.model.value()
        },
        getHtml: function () {
            return this.model.markdown(this.getContext());
        },
        getIntro: function (content) {
            if (this.hasIntro(content)) {
                return content.split(this.spliter)[0]
            }
            return content;
        },
        hasIntro: function (content) {
            return content.indexOf(this.spliter) != -1;
        },
        formateArticle: function (content) {
            var intro = this.getIntro(content);
            return {
                intro: intro,
                content: content
            };
        },
        getArticle: function () {
            var title = $("#title").val();
            var subpath = $("#subpath").val();
            var tags = $("#a-tags").val();
            var kind = $("#a-categories").val();
            var article = this.formateArticle(this.getContext())
            article.title = title;
            article.subpath = subpath;
            article.tags = tags;
            article.categories = kind;
            return article;
        },
        validArticle: function (article) {
            if (!article.title && article.title.trim().length === 0) {
                $("#title").addClass("parsley-error");
                layer.msg("请填写标题");
                return false;
            } else if (!article.categories && article.categories.trim().length === 0) {
                $("#a-kind").addClass("parsley-error");
                layer.msg("请选择类别");
                return false;
            } else if (!article.content && article.content.trim().length === 0) {
                layer.msg("请输入文章内容");
                return false;
            }
            return true;
        },
        showImages: function (editor) {
            layer.notifyImage = function (text) {
                editor.drawImage(text);
            };
            layer.open({
                type: 2,
                area: ['700px', '560px'],
                fixed: false, //不固定
                maxmin: true,
                content: '/admin/files/inner'
            });
        }
    };

    MD.make().init();
    $("#a-tags").tagsInput({
        width: 'auto'
    })
    $("#save").click(function () {
        var article = MD.getArticle();
        var form = $("#mdform");
        $("#submitType").val("save");
        $("#editorHtml").text(MD.getHtml());
        if(MD.validArticle(article)){
            form.submit();
        }
    });
    if($("#error-msg").text()){
        layer.msg($("#error-msg").text());
    }
    $("#save_publish").click(function(){
        var article = MD.getArticle();
        var form = $("#mdform");
        $("#submitType").val("publish");
        $("#editorHtml").text(MD.getHtml());
        if(MD.validArticle(article)){
            form.submit();
        }
    })
})