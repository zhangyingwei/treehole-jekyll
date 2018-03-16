PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE admin(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,username VARCHAR(20) NOT NULL,password VARCHAR(50) NOT NULL );
CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name VARCHAR (100) NOT NULL, path VARCHAR (200) NOT NULL, alias VARCHAR (50) NOT NULL, contenttype VARCHAR (20));
CREATE TABLE installinfo (id INTEGER PRIMARY KEY UNIQUE NOT NULL, idate VARCHAR (20) NOT NULL,iosname VARCHAR (20),iosdesktop  VARCHAR (20),ilocal VARCHAR (50),ibower VARCHAR (50),ioscpu VARCHAR (20),ijdkversion VARCHAR (20));
CREATE TABLE kind (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR (20) NOT NULL UNIQUE, flag INTEGER (2) NOT NULL DEFAULT (0));
INSERT INTO kind VALUES(1,'默认分类',0);
CREATE TABLE links (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR, url VARCHAR);
CREATE TABLE log (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, ip VARCHAR (20), reqtype varchat (10), ip_location VARCHAR (30), referer VARCHAR (100), url VARCHAR (100), uri VARCHAR (100), agent VARCHAR (200), "action" VARCHAR (50), timestamp BIGINT NOT NULL);
CREATE TABLE IF NOT EXISTS "bloginfo"
(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    url VARCHAR(50),
    desc TEXT(500),
    nickname VARCHAR(10),
    email VARCHAR(50),
    weibo VARCHAR(20),
    weixin VARCHAR(20),
    zhihu VARCHAR(50),
    facebook VARCHAR(50),
    twitter VARCHAR(50)
);
CREATE TABLE article (id VARCHAR (50) PRIMARY KEY UNIQUE NOT NULL, title VARCHAR (20) NOT NULL, path VARCHAR, subpath VARCHAR (100), tags VARCHAR (200), excerpt VARCHAR (500), excerpthtml, content VARCHAR (5000), contenthtml, categories INTEGER NOT NULL, usecommont VARCHAR (10) NOT NULL DEFAULT off, flag INTEGER NOT NULL DEFAULT (0), date VARCHAR (20) NOT NULL DEFAULT (datetime()), preview VARCHAR NULL);
INSERT INTO article VALUES('1','第一篇文章','/articles/1','','示例标签,test','这里是来自TreeHole的问候','<p>这里是来自TreeHole的问候</p>',replace('这里是来自TreeHole的问候\n\n<!-- more -->\n\n亲爱的用户你好，感谢你选择了treehole博客系统，这是我的荣幸。在使用的过程中如果有任何疑问，请联系我 zhangyw001@gmail.com。','\n',char(10)),replace('<p>这里是来自TreeHole的问候</p>\n<!-- more -->\n<p>亲爱的用户你好，感谢你选择了treehole博客系统，这是我的荣幸。在使用的过程中如果有任何疑问，请联系我 <a href="mailto:&#x7a;&#104;&#x61;&#x6e;&#103;&#121;w&#x30;&#x30;1&#64;&#x67;&#x6d;&#97;&#105;&#108;&#46;&#99;&#x6f;&#x6d;&#12290;">&#x7a;&#104;&#x61;&#x6e;&#103;&#121;w&#x30;&#x30;1&#64;&#x67;&#x6d;&#97;&#105;&#108;&#46;&#99;&#x6f;&#x6d;&#12290;</a></p>','\n',char(10)),1,'on',1,'2018-03-11 19:17:04',NULL);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('kind',1);
INSERT INTO sqlite_sequence VALUES('log',1);
INSERT INTO sqlite_sequence VALUES('admin',1);
INSERT INTO sqlite_sequence VALUES('files',1);
INSERT INTO sqlite_sequence VALUES('bloginfo',1);
COMMIT;
