package com.zhangyingwei.treehole.admin.log.model;

/**
 * Created by zhangyw on 2017/6/16.
 */
public class PieView {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VisitLocationView{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

