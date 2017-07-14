package com.zhangyingwei.treehole.common.utils;

import com.zhangyingwei.treehole.log.filter.LogFilter;
import com.zhangyingwei.treehole.log.model.Agent;
import com.zhangyingwei.treehole.log.model.LogModel;
import com.zhangyingwei.treehole.admin.model.Article;
import com.zhangyingwei.treehole.admin.model.Menu;
import com.zhangyingwei.treehole.admin.model.User;
import com.zhangyingwei.treehole.common.TreeHoleEnum;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.install.model.BlogConf;
import com.zhangyingwei.treehole.install.model.DbConf;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/4/21.
 */
public class TreeHoleUtils {

    private static Logger logger = LoggerFactory.getLogger(TreeHoleUtils.class);
    private static final int LOGIN_TIMEOUT = 1000*60*60*2;//登录状态保存时间 2个小时
    private static  PegDownProcessor pegDownProcessor = new PegDownProcessor(Extensions.ALL_WITH_OPTIONALS);

    /**
     * 判断是否已经安装
     * @return
     */
    public static boolean isInstalled(){
        return FileUtils.exists(TreeHoleEnum.INSTALL_LOCK.getValue());
    }
    public static void markAsInstall() throws IOException {
        FileUtils.newFile(TreeHoleEnum.INSTALL_LOCK.getValue());
    }

    /**
     * 创建数据库
     * @param dbConf
     */
    public static void makeTables(DbConf dbConf) throws TreeHoleException {
        List<String> sqls = null;
        try {
            sqls = readSql();
            if(sqls!=null && sqls.size()>0){
                Connection connection = DbUtils.getConnection(dbConf);
                for (String sql : sqls) {
                    DbUtils.execute(connection,sql);
                }
            }
        } catch (TreeHoleException e) {
            e.printStackTrace();
            throw new TreeHoleException("初始化数据表错误", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TreeHoleException(e);
        }
    }

    /**
     * 根据注释读取sql语句
     * @return
     */
    private static List<String> readSql() throws TreeHoleException {
        List<String> sqlList = new ArrayList<String>();
        File sqlFile = new File(TreeHoleEnum.RES_BASEPATH.getValue() + TreeHoleEnum.CONF_INSTALL_SQL.getValue());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
            sqlList = reader.lines().filter(line -> {
                return StringUtils.isNotEmpty(line) && !line.startsWith("--");
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new TreeHoleException("未找到数据库脚本文件", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TreeHoleException("打开数据库脚本文件错误", e);
        }
        return sqlList;
    }

    /**
     * encode password by md5
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodePasswordByMD5(String password) throws NoSuchAlgorithmException {
        return EncodeUtils.getMD5String(password);
    }

    /**
     * 检查是否已登录
     * @param session
     * @return
     */
    public static boolean isLogin(HttpSession session) {
        Object loginUser = session.getAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
        if (loginUser == null) {
            return false;
        }else {
            User user = (User) loginUser;
            return StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword());
        }
    }

    /**
     * 将已登录用户信息保存到session中
     * @param session
     * @param user
     */
    public static void markAsLogin(HttpSession session, User user) {
        session.setAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue(), user);
        session.setMaxInactiveInterval(LOGIN_TIMEOUT);
    }

    /**
     * 获取保存的登录用户信息
     * @param session
     */
    public static User getLoginUser(HttpSession session) {
        return (User) session.getAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
    }

    /**
     * 获取菜单信息
     * @return
     */
    public static Menu getMenu(){
        Menu root = new Menu("仪表盘").setRoot(true);
        root.addChild(
                new Menu("博客管理", "index1","fa-home")
                        .addChild(new Menu("基础信息","/admin/blog/basic"))
                        .addChild(new Menu("统计信息","/admin/blog/statistic"))
                        .addChild(new Menu("系统设置","/admin/blog/settings"))
        );
        root.addChild(
                new Menu("文章管理", "index2","fa-edit")
                        .addChild(new Menu("新建发布","/admin/articles/publish"))
                        .addChild(new Menu("历史管理","/admin/articles/history"))
                        .addChild(new Menu("评论管理","/admin/articles/commonts"))
                        .addChild(new Menu("分类管理","/admin/articles/kinds"))
        );
        root.addChild(
                new Menu("素材管理", "index3","fa-desktop")
                        .addChild(new Menu("素材管理","/admin/files"))
        );
        return root;
    }

    /**
     * 登出
     * 销毁 session中的user信息与menu信息
     * @param session
     */
    public static void logout(HttpSession session) {
        session.removeAttribute(TreeHoleEnum.LOGIN_MENU_KEY.getValue());
        session.removeAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue());
    }

    /**
     * 模拟登陆
     * 用户测试
     * @param session
     */
    public static void login(HttpSession session) {
        User user = new User();
        user.setId(1);
        user.setUsername("张英伟");
        user.setPassword("123456");
        Menu menu = TreeHoleUtils.getMenu();
        session.setAttribute(TreeHoleEnum.LOGIN_USER_KEY.getValue(), user);
        session.setAttribute(TreeHoleEnum.LOGIN_MENU_KEY.getValue(), menu);
    }

    /**
     * 把 \n 之类的 转换成 <br/> 之类的
     * @param desc
     * @return
     */
    public static String trans2Html(String desc) {
        if (StringUtils.isNotEmpty(desc)){
            desc = desc.replaceAll("\\n", "<br/>");
        }
        return desc;
    }

    /**
     * 获取系统参数
     * @return
     */
    public static Map<String,String> systemInfo(){
        Map<String, String> map = new HashMap<String,String>();
        map.putAll(osInfo());
        return map;
    }

    private static Map<String,String> osInfo(){
        Properties prop = System.getProperties();
        Map<String, String> map = new HashMap<String,String>();
        map.put("iosname", prop.getProperty("os.name"));
        map.put("iosdesktop", prop.getProperty("sun.desktop"));
        map.put("ioscup", prop.getProperty("sun.cpu.isalist"));
        map.put("ijdkversion", prop.getProperty("java.version"));
        return map;
    }

    /**
     * 获取ip地址对应的物理地址
     * @param ip
     * @return
     */
    public static String ipLocal(String ip){
        if(!isIpv4(ip)){
            return "";
        }
        IPUtils.load(TreeHoleEnum.RES_BASEPATH.getValue() + "17monipdb.dat");
        String[] res = IPUtils.find(ip);
        String result = "";
        for (String re : res) {
            if(StringUtils.isNotEmpty(re)){
                result += re;
                result += ",";
            }
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * 判断ip是不是ipv4
     * 因为ipip库貌似不识别ipv6
     * @param ip
     * @return
     */
    private static boolean isIpv4(String ip) {
        return ip.contains(".");
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取当前日期+时间
     * @return
     */
    public static String getCurrentDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取统一数据状态数据字典
     */
    public static Map<String,String> getGolbleStateDic(){
        return new HashMap<String, String>(){
            {
                put("0", "保存未发布");
                put("1", "已发布");
                put("9", "已删除");
            }
        };
    }

    /**
     * 获取浏览器类型
     * @param request
     * @return
     */
    public static String getBorwe(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 获取上传文件的位置
     * @param originalFilename
     * @return
     */
    public static String getFilePath(String originalFilename) {
        return TreeHoleEnum.UPLOAD_FILE_BASEPATH.getValue() + originalFilename;
    }

    /**
     * 保存上传的文件
     * @param originalFilename
     * @param bytes
     */
    public static void saveUploadFile(String originalFilename, byte[] bytes) throws TreeHoleException {
        try{
            //创建上传文件文件夹
            createUploadBasePath();
            FileUtils.saveFile(getFilePath(originalFilename), bytes);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            throw new TreeHoleException(e.getLocalizedMessage());
        }
    }

    /**
     * 创建文件上传文件夹
     */
    private static void createUploadBasePath() {
        FileUtils.createDir(TreeHoleEnum.UPLOAD_FILE_BASEPATH.getValue());
    }

    /**
     * 判断素材是否已经存在
     * @param originalFilename
     */
    public static boolean fileExits(String originalFilename) {
        return FileUtils.exists(getFilePath(originalFilename));
    }

    /**
     * 删除已经上传的文件
     * @param path
     */
    public static void deleteUploadFile(String path) {
        FileUtils.deleteFile(path);
    }

    /**
     * 生成feed文件
     * @return
     */
    public static String createFeed(BlogConf blog, List<Article> posts, Map<String,Object> site) throws TreeHoleException, IOException, ParseException {
        Document feed = createDocument();
        Element root = createRootElement();
        Element channel = createChannel(blog,posts,site);
        root.add(channel);
        feed.setRootElement(root);
        return feed.asXML();
    }

    /**
     * 创建 channel 节点
     *
     * @param blog
     * @param posts
     * @param site
     * @return
     */
    private static Element createChannel(BlogConf blog, List<Article> posts, Map<String, Object> site) throws ParseException {
        Date date = new Date();
        Element channel = DocumentHelper.createElement("channel");
        //title
        Element title = DocumentHelper.createElement("title");
        title.setText(blog.getName());
        channel.add(title);
        //description
        Element description = DocumentHelper.createElement("description");
        description.addCDATA(blog.getDesc());
        channel.add(description);
        //link
        Element link = DocumentHelper.createElement("link");
        link.setText(blog.getUrl());
        channel.add(link);
        //atom:link
        Element atomLink = DocumentHelper.createElement("atom:link");
        atomLink.addAttribute("href", blog.getUrl() + "/feed");
        channel.add(atomLink);
        //pubDate
        Element pubDate = DocumentHelper.createElement("pubDate");
        pubDate.setText(date.toString());
        channel.add(pubDate);
        //lastBuildDate
        Element lastBuildDate = DocumentHelper.createElement("lastBuildDate");
        lastBuildDate.setText(date.toString());
        channel.add(lastBuildDate);
        //generator
        Element generator = DocumentHelper.createElement("generator");
        generator.setText("TreeHole v0.0.1");
        channel.add(generator);
        //items
        List<Element> items = createItems(blog, posts);
        items.stream().forEach(item -> channel.add(item));
        return channel;
    }

    private static List<Element> createItems(BlogConf blog, List<Article> posts) throws ParseException {
        List<Element> items = new ArrayList<Element>();
        for (Article post : posts) {
            Element item = DocumentHelper.createElement("item");
            Element title = DocumentHelper.createElement("title");
            title.setText(post.getTitle());
            Element description = DocumentHelper.createElement("description");
            description.addCDATA(post.getContentHtml());
            Element pubDate = DocumentHelper.createElement("pubDate");
            pubDate.setText(DateUtils.toRFC822(DateUtils.formate(post.getDate())));
            Element link = DocumentHelper.createElement("link");
            link.setText(blog.getUrl() + "/articles/" + post.getId());
            Element guid = DocumentHelper.createElement("guid");
            guid.setText(blog.getUrl() + "/articles/" + post.getId());
            String tags = post.getTags();
            item.add(title);
            item.add(description);
            item.add(pubDate);
            item.add(link);
            item.add(guid);
            if(post.getCategories()!=null){
                Element category = DocumentHelper.createElement("category");
                category.setText(post.getCategories());
                item.add(category);
            }
            if(StringUtils.isNoneEmpty(tags)){
                Arrays.stream(tags.split(",")).forEach(tag -> {
                    Element tagEl = DocumentHelper.createElement("category");
                    tagEl.setText(tag);
                    item.add(tagEl);
                });
            }
            items.add(item);
        }
        return items;
    }

    /**
     * 创建 reed 文档对象
     * @return
     */
    private static Document createDocument() {
        Document doc = DocumentHelper.createDocument();
        doc.setXMLEncoding(TreeHoleEnum.FEED_ENCODING.getValue());
        return doc;
    }

    /**
     * 创建reed根节点
     * @return
     */
    private static Element createRootElement() {
        Element element = DocumentHelper.createElement("rss");
        element.addAttribute("version", "2.0");
        element.addAttribute("xmlns:atom", "http://www.w3.org/2005/Atom");
        return element;
    }

    /**
     * 过滤日志
     * @param visits
     */
    public static void filterLog(List<LogModel> visits, LogFilter logFilter) {
        Iterator<LogModel> it = visits.iterator();
        while(it.hasNext()){
            LogModel log = it.next();
            if(logFilter.access(log)){
                it.remove();
            }
        }
    }

    /**
     * 获取浏览器类型
     * @param agent
     * @return
     */
    public static String getExplore(String agent) {
        if(StringUtils.isEmpty(agent)){
            return "未识别";
        }
        Agent agentObj = new Agent(agent);
        return agentObj.getBrowser();
    }

    /**
     * 解析 markdown
     * @return
     */
    public static String markdown(String markdown){
        return pegDownProcessor.markdownToHtml(markdown);
    }
}
