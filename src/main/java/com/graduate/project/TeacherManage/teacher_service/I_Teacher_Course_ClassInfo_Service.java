package com.graduate.project.TeacherManage.teacher_service;

import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_ClassInfo_Table;

import java.util.List;

public interface I_Teacher_Course_ClassInfo_Service {
    /*** 1、查询  课程班级下 所有学生 的信息  [课程编号，教师编号，班级号]****/
    public List<Teacher_Course_ClassInfo_Table> Teacher_Course_ClassInfo_SelectAll(String CourseNo, String CourseCreatorNo, String CourseClassNo);

    /*** 2、查询  课程班级下 具体 组中 的 所有学生 的信息  [课程编号，教师编号，班级号，小组号]****/
    public List<Teacher_Course_ClassInfo_Table> Teacher_Course_ClassInfo_SelectGroup(String CourseNo, String CourseCreatorNo, String CourseClassNo, String CourseClassGroupNo);

    /*** 3、新增  课程班级下 具体学生的信息， 其中 组别 可以为空  后续也会涉及 批量新增  ****/
    public boolean Teacher_Course_ClassInfo_Insert(Teacher_Course_ClassInfo_Table teacher_course_classInfo_table);

    /*** 4、修改  课程班级下 具体学生 的信息   ，按理来说，修改应该是不用 批量修改的，因为前端处理时，
     * 都是一个学生信息的去修改，，倘若真的涉及批量修改，充其量也是  先批量删除，在批量导入！
     * 不过  删除应该也不会出现 批量删除，只不过是前端 传入数据后，循环调用 删除功能的接口并进行操作罢了  ****/
    public boolean Teacher_Course_ClassInfo_Update(Teacher_Course_ClassInfo_Table teacher_course_classInfo_table);

    /*** 5、删除  课程班级下 具体学生 的信息  [课程编号，教师编号，班级号，学生学号]****/
    public boolean Teacher_Course_ClassInfo_Delete(String CourseNo, String CourseCreatorNo, String CourseClassNo, String CourseClassStudentNo);

    /***  6、新增和修改时会用到的判断
     *    6.1、判断 课程 是否存在且属于 该教师创建的  ，CourseNo，CourseName，CourseCreatorNo；；属于 TeacherCourse 表
     *    6.2、判断该班级是否存在，CourseNo，CourseCreatorNo，CourseClassNo；；属于 TeacherCourseClass 表
     *    6.3、判断在此课程班级下的学生学号是否 唯一  CourseNo，CourseCreatorNo，CourseClassNo，CourseClassStudentNo；；；属于 TeacherCourseClassInfo 表
     *    6.4、判断  组别 是否  与 组号一一对应  ；；属于 TeacherCourseClassInfo 表
     *    其实还缺少对学生信息的判断，只不过当前还没有学生信息表 后续加上***/
    //  6.3、判断在此课程班级下的学生学号是否 唯一  CourseNo，CourseCreatorNo，CourseClassNo，CourseClassStudentNo；；；属于 TeacherCourseClassInfo 表
    public boolean Teacher_Course_ClassInfo_JudgeStudentTrue(String CourseNo, String CourseCreatorNo, String CourseClassNo, String CourseClassStudentNo);
    //  6.4、判断  组别 是否  与 组号一一对应  ；；属于 TeacherCourseClassInfo 表
    //  难点，不好判断，因为 同一组，会有多个数据;;可以没有该组，然后创建了，，可以有，多个
    //  最终决定，取消此判断
    //public boolean Teacher_Course_ClassInfo_JudgeGroupTrue(String CourseNo, String CourseCreatorNo, String CourseClassNo, String CourseClassGroupNo, String CourseClassGroupName);

    /***  7、其实 在修改 信息时，是不需要 检查信息的 正确性的，因为前端都是通过表格进行数据展示与查看传输的，
     * 数据都是以选中的方式进行操作，没有手动输入的情况，一般不会出现问题，但为了保障 系统的完整性，决定还是加上吧 ，
     * 但也仅仅是 对插入的新数据进行判断，其余数据都用来定位数据信息了，如果错误会找不到信息，导致修改失败的*/
}
