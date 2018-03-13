package com.yaorange.jk.web.controller;

import com.yaorange.jk.entity.User;
import com.yaorange.jk.entity.vo.ApiResponse;
import com.yaorange.jk.utils.ResponseCode;
import com.yaorange.jk.utils.SysConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller("userinfo")
@RequestMapping("/user")
public class UserCtrl {
    @RequestMapping("/info")
    public @ResponseBody ApiResponse info(String token,HttpSession session){
//        User user = (User) session.getAttribute(token);
//
//
//        if(user!=null)
//        {
//           return new ApiResponse(user);
//        }else
//        {
//            return new ApiResponse(SysConstant.API_MESSAGE_PARAM_ERROR,"500",ResponseCode.API_CODE_CALL_FAIL);
//        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        User user = new User();
        user.setId("1");
        user.setUid("1");
        user.setName(userDetails.getUsername());
        user.setIntroduction("im super pig");
        List<String>  permissions = new ArrayList<>();
        userDetails.getAuthorities().forEach(a->{permissions.add(a.getAuthority());});
        permissions.add("admin");
        permissions.add("首页");
        permissions.add("Dashboard");

        user.setPermissions(permissions);
        user.setToken(UUID.randomUUID().toString());



        String routes = "[\n" +
                "\n" +
                "    {\n" +
                "        path: '/',\n" +
                "        redirect: '/dashboard',\n" +
                "        name: '首页',\n" +
                "        component: Full,\n" +
                "        hidden: false,\n" +
                "        children: [\n" +
                "            {path: '/dashboard', name: 'Dashboard', icon: 'speedometer', component: _import('Dashboard')},\n" +
                "            {path: '/introduction', name: '介绍', icon: 'thumbsup', component: _import('Introduction')},\n" +
                "            {\n" +
                "                path: '/components', name: 'component组件', redirect: '/components/buttons', icon: 'bookmark',\n" +
                "                component: {\n" +
                "                    render(c) {\n" +
                "                        return c('router-view')\n" +
                "                    }\n" +
                "                },\n" +
                "                children: [{\n" +
                "                    path: 'buttons',\n" +
                "                    name: 'Buttons按钮',\n" +
                "                    icon: 'social-youtube',\n" +
                "                    component: _import('components/Buttons'),\n" +
                "                    hidden: false\n" +
                "                },\n" +
                "                    {path: 'hoverbuttons', name: '悬停特效按钮', icon: 'wand', component: _import('components/HoverButtons')},\n" +
                "                    {path: 'alert', name: 'Alert警告提示', icon: 'alert', component: _import('components/Alert')},\n" +
                "                    {path: 'card', name: 'Card卡片', icon: 'ios-browsers-outline', component: _import('components/Card')},\n" +
                "                    {\n" +
                "                        path: 'datepicker',\n" +
                "                        name: 'DatePicker',\n" +
                "                        icon: 'ios-calendar-outline',\n" +
                "                        component: _import('components/DatePicker')\n" +
                "                    },\n" +
                "                    {path: 'form', name: 'Form表单', icon: 'ios-list-outline', component: _import('components/Form')},\n" +
                "                    {\n" +
                "                        path: 'modal',\n" +
                "                        name: 'Modal对话框',\n" +
                "                        icon: 'ios-chatbubble-outline',\n" +
                "                        component: _import('components/Modal')\n" +
                "                    },\n" +
                "                    {\n" +
                "                        path: 'select',\n" +
                "                        name: 'Select选择器',\n" +
                "                        icon: 'ios-arrow-down',\n" +
                "                        component: _import('components/Select')\n" +
                "                    },\n" +
                "                    {path: 'spin', name: 'Spin加载中', icon: 'load-d ', component: _import('components/Spin')},\n" +
                "                    {\n" +
                "                        path: 'steps',\n" +
                "                        name: 'Steps步骤条',\n" +
                "                        icon: 'ios-checkmark-outline',\n" +
                "                        component: _import('components/Steps')\n" +
                "                    },\n" +
                "                    {\n" +
                "                        path: 'timeline',\n" +
                "                        name: 'Timeline时间轴',\n" +
                "                        icon: 'android-more-vertical',\n" +
                "                        component: _import('components/Timeline')\n" +
                "                    },\n" +
                "                    {\n" +
                "                        path: 'transfer',\n" +
                "                        name: 'Transfer穿梭框',\n" +
                "                        icon: 'ios-pause-outline',\n" +
                "                        component: _import('components/Transfer')\n" +
                "                    },\n" +
                "                    {\n" +
                "                        path: 'timepicker',\n" +
                "                        name: 'Timepicker',\n" +
                "                        icon: 'ios-clock-outline',\n" +
                "                        component: _import('components/Timepicker')\n" +
                "                    },\n" +
                "                    {\n" +
                "                        path: 'upload',\n" +
                "                        name: 'Upload上传',\n" +
                "                        icon: 'ios-cloud-upload-outline',\n" +
                "                        component: _import('components/Upload')\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                path: '/charts', name: 'echart图表', redirect: '/charts/shopchart', icon: 'pie-graph',\n" +
                "                component: {\n" +
                "                    render(c) {\n" +
                "                        return c('router-view')\n" +
                "                    }\n" +
                "                },\n" +
                "                children: [{\n" +
                "                    path: 'shopchart',\n" +
                "                    name: '商场统计图表',\n" +
                "                    icon: 'stats-bars',\n" +
                "                    component: _import('charts/ShopChart'),\n" +
                "                    hidden: false\n" +
                "                },\n" +
                "                    {\n" +
                "                        path: 'radarchart',\n" +
                "                        name: '雷达图',\n" +
                "                        icon: 'arrow-graph-up-right',\n" +
                "                        component: _import('charts/RadarChart')\n" +
                "                    },\n" +
                "                    {path: 'cakechart', name: '蛋糕销量图表', icon: 'ios-analytics', component: _import('charts/CakeChart')}\n" +
                "                ]\n" +
                "            },\n" +
                "            {path: '/table', name: '表格综合实例', icon: 'ios-paper', component: _import('Table'), meta: {role: ['admin']}},\n" +
                "            {path: '/jsontree', name: 'JSON视图', icon: 'merge', component: _import('JsonTree')},\n" +
                "            {path: '/tabledetail/:id', name: 'TableDetail', hidden: true, component: _import('TableDetail')},\n" +
                "            {path: '/tinymce', name: 'Tinymce编辑器', icon: 'android-document', component: _import('Tinymce')},\n" +
                "            {path: '/markdown', name: 'Markdown', icon: 'android-list', component: _import('Markdown')},\n" +
                "            {path: '/crud', name: 'Crud', icon: 'android-list', component: _import('Crud')},\n" +
                "            {path: '/elementui', name: 'elementUI', icon: 'android-list', component: _import('ElementUI')}\n" +
                "\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        path: '/system',\n" +
                "        redirect: '/system/dept',\n" +
                "        name: '系统管理',\n" +
                "        component: Full2,\n" +
                "        hidden: false,\n" +
                "        children: [\n" +
                "            {path: '/system/dept', name: '部门管理', icon: 'speedometer', component: _import('system/Dept')},\n" +
                "            {path: '/system/user', name: '用户管理', icon: 'thumbsup', component: _import('system/User')},\n" +
                "            {path: '/system/role', name: '角色管理', icon: 'thumbsup', component: _import('system/Role')},\n" +
                "            {path: '/system/module', name: '模块管理', icon: 'thumbsup', component: _import('system/Module')}\n" +
                "\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        path: '/home1',\n" +
                "        redirect: '/home1/introduction',\n" +
                "        name: '首页2',\n" +
                "        component: Full2,\n" +
                "        hidden: false,\n" +
                "        children: [\n" +
                "            {path: '/home1/dashboard', name: 'Dashboard2', icon: 'speedometer', component: _import('Dashboard2')},\n" +
                "            {path: '/home1/introduction', name: '介绍2', icon: 'thumbsup', component: _import('Introduction')}\n" +
                "\n" +
                "        ]\n" +
                "    },\n" +
                "\n" +
                "    {path: '*', redirect: '/pages/404', hidden: true}\n" +
                "\n" +
                "]";

        return new ApiResponse(user);
    }
}
