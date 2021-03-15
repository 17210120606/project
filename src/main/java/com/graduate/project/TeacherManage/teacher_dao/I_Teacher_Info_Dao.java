package com.graduate.project.TeacherManage.teacher_dao;


import com.graduate.project.TeacherManage.teacher_entity.Teacher_Info_Table;
import org.springframework.stereotype.Repository;

@Repository
public interface I_Teacher_Info_Dao {

    /***  1、教师 登录  *****/
    public boolean Teacher_Info_Login(String TeacherNo, String TeacherPassword);

    /***  2、教师 查询 个人信息 *****/
    public Teacher_Info_Table Teacher_Info_SelectHimself(String TeacherNo);

    /***  3、教师 修改 个人信息  *****/
    public boolean Teacher_Info_UpdateInfo(String TeacherNo, String OldTeacherPassword, String NewTeacherPassword);

    /**** 4、创建课程使用，判断教师输入的 教师编号 与其 名称 是否对应  ******/
    public boolean Teacher_Info_Judge(String TeacherNo, String TeacherName);
}
