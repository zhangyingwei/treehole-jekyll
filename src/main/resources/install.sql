--
-- 由SQLiteStudio v3.1.1 产生的文件 周五 七月 14 10:25:04 2017
--
-- 文本编码：UTF-8
--
BEGIN TRANSACTION;

-- 表：admin
DROP TABLE IF EXISTS admin;
CREATE TABLE admin(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,username VARCHAR(20) NOT NULL,password VARCHAR(50) NOT NULL );

-- 表：article
DROP TABLE IF EXISTS article;
CREATE TABLE article (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, title VARCHAR (20) NOT NULL UNIQUE, path VARCHAR, subpath VARCHAR (100), tags VARCHAR (200), excerpt VARCHAR (500), excerpthtml, content VARCHAR (5000), contenthtml, categories INTEGER NOT NULL, usecommont VARCHAR (10) NOT NULL DEFAULT off, flag INTEGER NOT NULL DEFAULT (0), date VARCHAR (20) NOT NULL DEFAULT (datetime()));

-- 表：bloginfo
DROP TABLE IF EXISTS bloginfo;
CREATE TABLE bloginfo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name VARCHAR (50) NOT NULL, url VARCHAR (50), "desc" TEXT (500));

-- 表：files
DROP TABLE IF EXISTS files;
CREATE TABLE files (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name VARCHAR (100) NOT NULL, path VARCHAR (200) NOT NULL, alias VARCHAR (50) NOT NULL, contenttype VARCHAR (20));

-- 表：installinfo
DROP TABLE IF EXISTS installinfo;
CREATE TABLE installinfo (id INTEGER PRIMARY KEY UNIQUE NOT NULL, idate VARCHAR (20) NOT NULL,iosname VARCHAR (20),iosdesktop  VARCHAR (20),ilocal VARCHAR (50),ibower VARCHAR (50),ioscpu VARCHAR (20),ijdkversion VARCHAR (20));

-- 表：kind
DROP TABLE IF EXISTS kind;
CREATE TABLE kind (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR (20) NOT NULL UNIQUE, flag INTEGER (2) NOT NULL DEFAULT (0));

-- 表：links
DROP TABLE IF EXISTS links;
CREATE TABLE links (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR, url VARCHAR);

-- 表：log
DROP TABLE IF EXISTS log;
CREATE TABLE log (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, ip VARCHAR (20), reqtype varchat (10), ip_location VARCHAR (30), referer VARCHAR (100), url VARCHAR (100), uri VARCHAR (100), agent VARCHAR (200), "action" VARCHAR (50), timestamp BIGINT NOT NULL);

COMMIT TRANSACTION;

INSERT INTO kind (id, name, flag) VALUES (1, '默认分类', 0);
INSERT INTO article(id, title, path, subpath, tags, excerpt, excerpthtml, content, contenthtml, categories, usecommont, flag, date) VALUES (1, '第一篇文章', '/articles/1', '', '示例标签', '这里是来自TreeHole的问候', '<p>这里是来自TreeHole的问候</p>', '这里是来自TreeHole的问候<!-- more -->亲爱的用户你好，感谢你选择了treehole博客系统，这是我的荣幸。在使用的过程中如果有任何疑问，请联系我 zhangyw001@gmail.com。', '<p>这里是来自TreeHole的问候</p><!-- more --><p>亲爱用户你好，感谢你选择了treehole博客系统，这是我的荣幸。在使用的过程中如果有任何疑问，请联系我 zhangyw001@gmail.com。</p>', 1, 'on', 1, '2016-05-01 07:32:01');
