-- treehole db init script
-- db create
CREATE DATABASE IF NOT EXISTS treehole
-- table create
create table IF NOT EXISTS admin(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,username VARCHAR(20) NOT NULL,password VARCHAR(50) NOT NULL )
CREATE TABLE IF NOT EXISTS bloginfo (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,name VARCHAR (50) NOT NULL,url VARCHAR (50),desc TEXT (500))
CREATE TABLE IF NOT EXISTS installinfo (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, idate VARCHAR (20) NOT NULL,iosname VARCHAR (20),iosdesktop  VARCHAR (20),ilocal VARCHAR (50),ibower VARCHAR (50),ioscpu VARCHAR (20),ijdkversion VARCHAR (20))
CREATE TABLE IF NOT EXISTS kind (id   INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, name VARCHAR (20) NOT NULL UNIQUE,flag INTEGER (2) NOT NULL DEFAULT (0))
INSERT INTO kind (name) values ("默认分类")
CREATE TABLE IF NOT EXISTS article (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, title VARCHAR (20) NOT NULL UNIQUE, subpath VARCHAR (100),tags VARCHAR (200),intro VARCHAR (500), introhtml VARCHAR (5000), article VARCHAR (5000),articlehtml VARCHAR (5000),kind NOT NULL,usecommont  VARCHAR (10) NOT NULL DEFAULT off,flag INTEGER NOT NULL DEFAULT (0), date VARCHAR (20) NOT NULL DEFAULT (datetime()))
INSERT INTO article (title,tags,intro,introhtml,article,articlehtml,kind,flag) VALUES ("第一篇文章","demo","这里是treehole博客","这里是treehole博客","亲爱的用户你好 , 感谢你选择了treehole博客系统 , 这是我的荣幸。在使用的过程中如果有任何疑问 , 请联系我 zhangyw001@gmail.com。","<p>亲爱的用户你好，感谢你选择了treehole博客系统，这是我的荣幸。在使用的过程中如果有任何疑问，请联系我 zhangyw001@gmail.com。</p>",1,1)
CREATE TABLE IF NOT EXISTS files ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name VARCHAR (100) NOT NULL, path VARCHAR (200) NOT NULL, alias VARCHAR (50)  NOT NULL, contenttype VARCHAR (20))
CREATE TABLE log ( id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, ip VARCHAR (20),reqType VARCHAT (10),ip_location VARCHAR (30),referer VARCHAR (100),url VARCHAR (100),uri VARCHAR (100),agent VARCHAR (200),[action] VARCHAR (50),timestamp BIGINT NOT NULL)
