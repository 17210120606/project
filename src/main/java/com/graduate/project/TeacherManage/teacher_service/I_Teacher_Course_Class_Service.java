package com.graduate.project.TeacherManage.teacher_service;

import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Class_Table;

import java.util.List;

public interface I_Teacher_Course_Class_Service {
    /***  1、查询课程下 所有的班级 根据  教师编号 和 课程编号   ***/
    public List<Teacher_Course_Class_Table> Teacher_Course_Class_Select(String CourseCreatorNo, String CourseNo);

    /***  2、 创建课程下的班级  直接插入创建课程下班级的实体类****/
    public boolean Teacher_Course_Class_Insert(Teacher_Course_Class_Table teacher_course_class_table);

    /***  3、 修改课程下的班级 ，插入 新的 课程下班级的实体类***/
    public boolean Teacher_Course_Class_Update(Teacher_Course_Class_Table teacher_course_class_table);

    /***  4、删除课程下的班级  根据 教师编号 和 课程编号 以及 班级号   ***/
    public boolean Teacher_Course_Class_Delete(String CourseCreatorNo, String CourseNo, String CourseClassNo);

    /***  5、判断 输入的课程编号,教师编号和班级号 ，查看该班级是否 存在，主键   ***/
    public boolean Teacher_Course_Class_JudgeClassNo(String CourseCreatorNo, String CourseNo, String CourseClassNo);
}
