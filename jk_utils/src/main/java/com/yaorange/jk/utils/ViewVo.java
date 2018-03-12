package com.yaorange.jk.utils;

/**
 * 用于jsonview 注解 区分哪些字段转json
 * Created by coach-tam on 2018/3/12.
 */
public class ViewVo {
    //用于列表
    public interface Simple{}
    //用于详情
    public interface Details extends  Simple{}
}
