# treehole[树洞]

## 2017-04-25
### 完善了install页面
### 实现了
* 安装时声称install.lock标识文件
* 安装时第一步验证数据库可用性

## 2017-05-08  下一步设想
### 博客基础信息页
* 用户信息
    * 用户名 
    * 密码 
    * 邮箱
* 安装信息
    * 安装日期
    * 博客版本
    * 系统型号
* 友链信息
    * 名称
    * 链接
    * 添加日期
    
    
> mvn clean package assembly:single -Pprod -Dmaven.test.skip=true

## 2017-06-12

### 基本上完成一大半了，现在可以出一个初级的版本部署运行了


## 计划
* 日志统计部分还需要完善
* 系统设置部分
* 第一个大版本完善
* 适配jekyll模板 预计是做一个导入模板功能，程序中做转换

## 评论管理


## 主题收集
[jekyll默认主题] https://github.com/jekyll/minima.git
[H2O] https://github.com/kaeyleo/jekyll-theme-H2O.git
[vno] https://github.com/onevcat/vno-jekyll.git

https://github.com/thien/blueface.git

需要添加每篇文章下边的版权声明插件

tools 里边 对 page。。。.posts 之类的做一下转换

下一步
集成邮件功能



sqlite修改字段名

PRAGMA foreign_keys = 0;

CREATE TABLE sqlitestudio_temp_table AS SELECT *
                                          FROM article;

DROP TABLE article;

CREATE TABLE article (
    id          INTEGER        PRIMARY KEY AUTOINCREMENT
                               UNIQUE
                               NOT NULL,
    title1      VARCHAR (20)   NOT NULL
                               UNIQUE,
    path        VARCHAR,
    subpath     VARCHAR (100),
    tags        VARCHAR (200),
    excerpt     VARCHAR (500),
    excerpthtml,
    content     VARCHAR (5000),
    contenthtml,
    categories  INTEGER        NOT NULL,
    usecommont  VARCHAR (10)   NOT NULL
                               DEFAULT off,
    flag        INTEGER        NOT NULL
                               DEFAULT (0),
    date        VARCHAR (20)   NOT NULL
                               DEFAULT (datetime() ) 
);

INSERT INTO article (
                        id,
                        title1,
                        path,
                        subpath,
                        tags,
                        excerpt,
                        excerpthtml,
                        content,
                        contenthtml,
                        categories,
                        usecommont,
                        flag,
                        date
                    )
                    SELECT id,
                           title,
                           '',
                           subpath,
                           tags,
                           intro,
                           introhtml,
                           article,
                           articlehtml,
                           kind,
                           usecommont,
                           flag,
                           date
                      FROM sqlitestudio_temp_table;

DROP TABLE sqlitestudio_temp_table;

PRAGMA foreign_keys = 1;


# 接下来的任务

1. 登录验证还是有问题
2. 来访统计数据格式验证有问题 日志 地址格式化错误
3. 基础信息中安装信息获取有问题
4. 系统设置里边 用户信息设置 还需要把用户信息显示到博客页面中 最好在关于我页面中 需要有个头像设置
5. md编辑器中，图片的快捷添加
6. 新建文章中，文章头图片添加
7. 编辑文章中，评论开关初始化有问题
8. 文章查询的时候 查询按钮与查询文本框分开了，我很无奈
9. 历史文章列表中需要添加分页
10. 表格超长需要变成省略号，然后鼠标滑过显示全部 tip
11. 分类列表中需要分页
12. 素材管理也需要分页
13. 素材预览是个bug
14. js 文件需要整理，该压缩的压缩
15. 上边新信息提示需要完善
16. 博客分页上一页下一页功能完善
17. 重写启动脚本，必要情况下可以采用 python，但是还是 shell 比较好一点  done
18. 自动更新程序
19. 修改密码
20. ctrl+s 保存
21. 没啥就是添加一行