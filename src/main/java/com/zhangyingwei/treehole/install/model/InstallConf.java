package com.zhangyingwei.treehole.install.model;

/**
 * Created by zhangyw on 2017/5/9.
 * 安装信息
 */

public class InstallConf {
    private Integer id;
    private String idate;
    private String iosname;
    private String iosdesktop;
    private String ilocal;
    private String ibower;
    private String ioscpu;
    private String ijdkversion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }

    public String getIosname() {
        return iosname;
    }

    public void setIosname(String iosname) {
        this.iosname = iosname;
    }

    public String getIosdesktop() {
        return iosdesktop;
    }

    public void setIosdesktop(String iosdesktop) {
        this.iosdesktop = iosdesktop;
    }

    public String getIlocal() {
        return ilocal;
    }

    public void setIlocal(String ilocal) {
        this.ilocal = ilocal;
    }

    public String getIbower() {
        return ibower;
    }

    public void setIbower(String ibower) {
        this.ibower = ibower;
    }

    public String getIoscpu() {
        return ioscpu;
    }

    public void setIoscup(String ioscpu) {
        this.ioscpu = ioscpu;
    }

    public String getIjdkversion() {
        return ijdkversion;
    }

    public void setIjdkversion(String ijdkversion) {
        this.ijdkversion = ijdkversion;
    }
}
