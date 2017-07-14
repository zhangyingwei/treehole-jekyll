package com.zhangyingwei.treehole.common.annotation;

import java.lang.annotation.*;

/**
 * Created by zhangyw on 2017/7/14.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
}
