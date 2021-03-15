package com.graduate.project.TeacherManage.teacher_service.impl;

import com.graduate.project.TeacherManage.teacher_dao.I_Teacher_Info_Dao;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Info_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Info_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Teacher_Info_Service implements I_Teacher_Info_Service {
   @Autowired
   private I_Teacher_Info_Dao i_teacher_info_dao;

    /***  1、教师 登录
     * @param TeacherNo
     * @param TeacherPassword*****/
    @Override
    public boolean Teacher_Info_Login(String TeacherNo, String TeacherPassword) {
        //return false;
        return i_teacher_info_dao.Teacher_Info_Login(TeacherNo, TeacherPassword);
    }

    /***  2、教师 查询 个人信息
     * @param TeacherNo*****/
    @Override
    public Teacher_Info_Table Teacher_Info_SelectHimself(String TeacherNo) {
        //return null;
        return i_teacher_info_dao.Teacher_Info_SelectHimself(TeacherNo);
    }

    /***  3、教师 修改 个人信息
     * @param TeacherNo
     * @param OldTeacherPassword
     * @param NewTeacherPassword*****/

    @Override
    public boolean Teacher_Info_UpdateInfo(String TeacherNo, String OldTeacherPassword, String NewTeacherPassword) {
        //return false;
        return i_teacher_info_dao.Teacher_Info_UpdateInfo(TeacherNo, OldTeacherPassword, NewTeacherPassword);
    }

    /**** 4、创建课程使用，判断教师输入的 教师编号 与其 名称 是否对应
     * @param TeacherNo
     * @param TeacherName******/
    @Override
    public boolean Teacher_Info_Judge(String TeacherNo, String TeacherName) {
        //return false;
        return i_teacher_info_dao.Teacher_Info_Judge(TeacherNo, TeacherName);
    }
}
