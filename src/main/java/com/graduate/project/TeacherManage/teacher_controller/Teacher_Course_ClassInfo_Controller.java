package com.graduate.project.TeacherManage.teacher_controller;

import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_ClassInfo_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_ClassInfo_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Class_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Info_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "教师课程下管理学生基础信息管理专用的 Controller")
public class Teacher_Course_ClassInfo_Controller {
    @Autowired
    private I_Teacher_Course_ClassInfo_Service i_teacher_course_classInfo_service;
    @Autowired
    private I_Teacher_Course_Class_Service i_teacher_course_class_service;
    @Autowired
    private I_Teacher_Course_Service i_teacher_course_service;
    @Autowired
    private I_Teacher_Info_Service i_teacher_info_service;

    /*** 1、查询  课程班级下 所有学生 的信息  [课程编号，教师编号，班级号]
     * @param CourseCreatorNo
     * @param CourseNo
     * @param CourseClassNo
     * ***/
    @ApiOperation(value = "教师查询课程下构建的所有学生信息 ")
    @RequestMapping(value = "/teacher/course/classinfo/selectall", method = RequestMethod.GET)
    public Object TeacherCourseClassInfoSelectAll(@RequestParam("课程编号") String CourseNo,
                                                  @RequestParam("教师编号") String CourseCreatorNo,
                                                  @RequestParam("班级编号") String CourseClassNo,
                                                  HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_ClassInfo_Table> list = new ArrayList<Teacher_Course_ClassInfo_Table>();
        list = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_SelectAll(CourseNo, CourseCreatorNo, CourseClassNo);
        return Public_Result.success("查询成功！！",list);
    }



    /*** 2、查询  课程班级下 具体 组中 的 所有学生 的信息  [课程编号，教师编号，班级号，小组号]
     * @param CourseCreatorNo
     * @param CourseNo
     * @param CourseClassNo
     * @param CourseClassGroupNo
     * ***/
    @ApiOperation(value = "教师查询课程下构建的小组信息 ")
    @RequestMapping(value = "/teacher/course/classinfo/selectgroup", method = RequestMethod.GET)
    public Object TeacherCourseClassInfoSelectGroup(@RequestParam("课程编号") String CourseNo,
                                                    @RequestParam("教师编号") String CourseCreatorNo,
                                                    @RequestParam("班级编号") String CourseClassNo,
                                                    @RequestParam("小组编号") String CourseClassGroupNo,
                                                  HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_ClassInfo_Table> list = new ArrayList<Teacher_Course_ClassInfo_Table>();
        list = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_SelectGroup(CourseNo, CourseCreatorNo, CourseClassNo,CourseClassGroupNo);
        return Public_Result.success("查询成功！！",list);
    }


    /***  3、新增  课程班级下 具体学生的信息， 其中 组别 可以为空  后续也会涉及 批量新增，插入实体
     、 * @param teacher_course****/
    @ApiOperation(value = "教师新增课程下学生的基础信息 ")
    @RequestMapping(value = "/teacher/course/classinfo/insert", method = RequestMethod.POST)
    public Object TeacherCourseClassInfoInsert(@RequestParam("课程编号") String CourseNo,
                                               @RequestParam("课程名称") String CourseName,
                                               @RequestParam("教师编号") String CourseCreatorNo,
                                               @RequestParam("教师名称") String CourseCreatorName,
                                               @RequestParam("课程班级编号") String CourseClassNo,
                                               @RequestParam("课程班级名称") String CourseClassName,
                                               @RequestParam("课程班级小组编号") String CourseClassGroupNo,
                                               @RequestParam("课程班级小组名称") String CourseClassGroupName,
                                               @RequestParam("课程班级小组学生编号") String CourseClassStudentNo,
                                               @RequestParam("课程班级小组学生名称") String CourseClassStudentName,
                                               @RequestParam("学生课程最终成绩") Double StudentFinalGrade,
                                               HttpSession session){
        /*****  开始 检验 输入 信息的正确性     Begin    ****/
        /***  1、先验证 该教师是否存在  ******/
        boolean Teacher_TrueOrFalse = i_teacher_info_service.Teacher_Info_Judge(CourseCreatorNo,CourseCreatorName);
        if (Teacher_TrueOrFalse == false){
            return Public_Result.error("您的身份信息不正确，请重新输入");
        }

        /***  2、再验证 课程编号是否 存在，即该课程是否存在  ******/
        boolean Course_TrueOrFalse = i_teacher_course_service.Teacher_Course_JudgeCourseTrue(CourseNo,CourseName,CourseCreatorNo);
        if (Course_TrueOrFalse == false){
            return Public_Result.error("您尚未创建该课程，该课程不存在");
        }

        /***  3、验证 该班级是否 存在  ****/
        boolean Class_TrueOrFalse = i_teacher_course_class_service.Teacher_Course_Class_JudgeClassNo(CourseCreatorNo, CourseNo, CourseClassNo);
        if (Class_TrueOrFalse == false){
            return Public_Result.error("该班级不存在，请检查输入的信息后，重试！");
        }

        /***  4、验证 该名学生是否已经存在  ****/
        boolean Student_TrueOrFalse = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_JudgeStudentTrue(CourseNo, CourseCreatorNo, CourseClassNo, CourseClassStudentNo);
        if (Student_TrueOrFalse){
            return Public_Result.error("新增失败，该名学生已存在，请检查信息后重试！");
        }
        /*****  经检验，无问题，进行后续操作    Stop      ****/

        /********  构建参数实体并赋值   **********/
        Teacher_Course_ClassInfo_Table teacher_course_classInfo_table = new Teacher_Course_ClassInfo_Table();
        teacher_course_classInfo_table.setCourseNo(CourseNo);
        teacher_course_classInfo_table.setCourseName(CourseName);
        teacher_course_classInfo_table.setCourseCreatorNo(CourseCreatorNo);
        teacher_course_classInfo_table.setCourseCreatorName(CourseCreatorName);
        teacher_course_classInfo_table.setCourseClassNo(CourseClassNo);
        teacher_course_classInfo_table.setCourseClassName(CourseClassName);
        teacher_course_classInfo_table.setCourseClassGroupNo(CourseClassGroupNo);
        teacher_course_classInfo_table.setCourseClassGroupName(CourseClassGroupName);
        teacher_course_classInfo_table.setCourseClassStudentNo(CourseClassStudentNo);
        teacher_course_classInfo_table.setCourseClassStudentName(CourseClassStudentName);
        teacher_course_classInfo_table.setStudentFinalGrade(StudentFinalGrade);

        /*********   传入实体 数据，判断是否新增学生成功  *******/
        boolean flag = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_Insert(teacher_course_classInfo_table);
        if (flag){
            return Public_Result.success("新增学生成功",teacher_course_classInfo_table);
        }
        return Public_Result.error("新增学生失败");
    }



    /***  4、 修改  课程班级下 具体学生 的信息 ，能修改的内容为：  学生的 最终课程成绩
     、 * @param teacher_course****/
    @ApiOperation(value = "教师修改课程下学生的基础课程信息 ")
    @RequestMapping(value = "/teacher/course/classinfo/update", method = RequestMethod.POST)
    public Object TeacherCourseClassInfoUpdate(@RequestParam("课程编号") String CourseNo,
                                           @RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("班级号") String CourseClassNo,
                                           @RequestParam("小组号") String CourseClassGroupNo,
                                           @RequestParam("学生学号") String CourseClassStudentNo,
                                           @RequestParam("学生课程最终成绩") Double StudentFinalGrade,
                                           HttpSession session){

        /********  构建参数实体并赋值   **********/
        Teacher_Course_ClassInfo_Table teacher_course_classInfo_table = new Teacher_Course_ClassInfo_Table();
        teacher_course_classInfo_table.setCourseNo(CourseNo);
        teacher_course_classInfo_table.setCourseCreatorNo(CourseCreatorNo);
        teacher_course_classInfo_table.setCourseClassNo(CourseClassNo);
        teacher_course_classInfo_table.setCourseClassGroupNo(CourseClassGroupNo);
        teacher_course_classInfo_table.setCourseClassStudentNo(CourseClassStudentNo);
        teacher_course_classInfo_table.setStudentFinalGrade(StudentFinalGrade);

        /*********   传入实体 数据，判断是否新增学生成功  *******/
        boolean flag = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_Update(teacher_course_classInfo_table);
        if (flag){
            return Public_Result.success("修改学生成绩成功");
        }
        return Public_Result.error("修改学生成绩失败");
    }



    /***  5、 删除  课程班级下 具体学生 的信息  [课程编号，教师编号，班级号，学生学号]*
     、 * @param teacher_course****/
    @ApiOperation(value = "教师删除课程下学生的基础信息 ")
    @RequestMapping(value = "/teacher/course/classinfo/delete", method = RequestMethod.POST)
    public Object TeacherCourseClassInfoDelete(@RequestParam("课程编号") String CourseNo,
                                           @RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("班级编号") String CourseClassNo,
                                           @RequestParam("学生学号") String CourseClassStudentNo,
                                           HttpSession session) {
        /*********   传入 数据，判断是否  删除班级信息  成功  *******/
        boolean flag = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_Delete(CourseNo, CourseCreatorNo, CourseClassNo, CourseClassStudentNo);
        if (flag) {
            return Public_Result.success("学生删除成功");
        }
        return Public_Result.error("学生删除失败");
    }


}
