package com.graduate.project.TeacherManage.teacher_dao;

import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Table;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface I_Teacher_Course_Dao {
    /***  1、查询课程  根据教师编号***/
    public List<Teacher_Course_Table> Teacher_Course_Select(String TeacherNo);

    /***  2、 创建课程  直接插入创建课程的实体类****/
    public boolean Teacher_Course_Insert(Teacher_Course_Table teacher_course);

    /***  3、 修改课程 插入新的实体类***/
    public boolean Teacher_Course_Update(Teacher_Course_Table teacher_course);

    /***  4、删除课程  根据教师编号和课程编号***/
    public boolean Teacher_Course_Delete(String TeacherNo, String CourseNo);

    /***  5、判断 输入的课程编号是否 存在，主键，不能重复***/
    public boolean Teacher_Course_JudgeCourseNo(String CourseNo);

    /***  6、判断 输入的 课程编号 与 教师编号，判断该课程 是否 存在   用于创建班级用***/
    public boolean Teacher_Course_JudgeCourseTrue(String CourseNo, String CourseName, String TeacherNo);

}
