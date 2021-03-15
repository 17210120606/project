package com.graduate.project.TeacherManage.teacher_service.impl;

import com.graduate.project.TeacherManage.teacher_dao.I_Teacher_Course_Class_Dao;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Class_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Class_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Teacher_Course_Class_Service implements I_Teacher_Course_Class_Service {
    @Autowired
    private I_Teacher_Course_Class_Dao i_teacher_course_class_dao;

    /***  1、查询课程下 所有的班级 根据  教师编号 和 课程编号
     * @param CourseCreatorNo
     * @param CourseNo***/
    @Override
    public List<Teacher_Course_Class_Table> Teacher_Course_Class_Select(String CourseCreatorNo, String CourseNo) {
        //return null;
        return i_teacher_course_class_dao.Teacher_Course_Class_Select(CourseCreatorNo, CourseNo);
    }

    /***  2、 创建课程下的班级  直接插入创建课程下班级的实体类
     * @param teacher_course_class_table****/
    @Override
    public boolean Teacher_Course_Class_Insert(Teacher_Course_Class_Table teacher_course_class_table) {
        //return false;
        return i_teacher_course_class_dao.Teacher_Course_Class_Insert(teacher_course_class_table);
    }

    /***  3、 修改课程下的班级 ，插入 新的 课程下班级的实体类
     * @param teacher_course_class_table***/
    @Override
    public boolean Teacher_Course_Class_Update(Teacher_Course_Class_Table teacher_course_class_table) {
        //return false;
        return i_teacher_course_class_dao.Teacher_Course_Class_Update(teacher_course_class_table);
    }

    /***  4、删除课程下的班级  根据 教师编号 和 课程编号 以及 班级号
     * @param CourseCreatorNo
     * @param CourseNo
     * @param CourseClassNo***/
    @Override
    public boolean Teacher_Course_Class_Delete(String CourseCreatorNo, String CourseNo, String CourseClassNo) {
        //return false;
        return i_teacher_course_class_dao.Teacher_Course_Class_Delete(CourseCreatorNo, CourseNo, CourseClassNo);
    }

    /***  5、判断 输入的课程编号,教师编号和班级号 ，查看该班级是否 存在，主键
     * @param CourseCreatorNo
     * @param CourseNo
     * @param CourseClassNo***/
    @Override
    public boolean Teacher_Course_Class_JudgeClassNo(String CourseCreatorNo, String CourseNo, String CourseClassNo) {
        //return false;
        return i_teacher_course_class_dao.Teacher_Course_Class_JudgeClassNo(CourseCreatorNo, CourseNo, CourseClassNo);
    }
}
