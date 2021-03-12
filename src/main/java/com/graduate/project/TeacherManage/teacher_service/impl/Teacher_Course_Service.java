package com.graduate.project.TeacherManage.teacher_service.impl;

import com.graduate.project.TeacherManage.teacher_dao.I_Teacher_Course_Dao;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacher_Course_Service implements I_Teacher_Course_Service {
    @Autowired
    private I_Teacher_Course_Dao i_teacher_course_dao;
    /***  1、查询课程  根据教师编号
     * @param TeacherNo***/
    @Override
    public List<Teacher_Course_Table> Teacher_Course_Select(String TeacherNo) {
        //return null;
        return i_teacher_course_dao.Teacher_Course_Select(TeacherNo);
    }

    /***  2、 创建课程  直接插入创建课程的实体类
     * @param teacher_course****/
    @Override
    public boolean Teacher_Course_Insert(Teacher_Course_Table teacher_course) {
        //return false;
        return i_teacher_course_dao.Teacher_Course_Insert(teacher_course);
    }

    /***  3、 修改课程 插入新的实体类
     * @param teacher_course***/
    @Override
    public boolean Teacher_Course_Update(Teacher_Course_Table teacher_course) {
        //return false;
        return i_teacher_course_dao.Teacher_Course_Update(teacher_course);
    }

    /***  4、删除课程  根据教师编号和课程编号
     * @param TeacherNo
     * @param CourseNo***/
    @Override
    public boolean Teacher_Course_Delete(String TeacherNo, String CourseNo) {
        //return false;
        return i_teacher_course_dao.Teacher_Course_Delete(TeacherNo, CourseNo);
    }
}
