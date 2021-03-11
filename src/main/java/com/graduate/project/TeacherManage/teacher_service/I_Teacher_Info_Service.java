package com.graduate.project.TeacherManage.teacher_service;

import com.graduate.project.TeacherManage.teacher_entity.Teacher_Info_Table;

public interface I_Teacher_Info_Service {
    /***  1、教师 登录  *****/
    public boolean Teacher_Info_Login(String TeacherNo, String TeacherPassword);

    /***  2、教师 查询 个人信息 *****/
    public Teacher_Info_Table Teacher_Info_SelectHimself(String TeacherNo);

    /***  3、教师 修改 个人信息  *****/
    public boolean Teacher_Info_UpdateInfo(String TeacherNo, String OldTeacherPassword, String NewTeacherPassword);

}
