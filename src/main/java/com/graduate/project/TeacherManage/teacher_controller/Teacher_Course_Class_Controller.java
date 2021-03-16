package com.graduate.project.TeacherManage.teacher_controller;

import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Class_Table;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Class_Service;
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
/***  创建 课程 的时候，请增加 判断，教师存在，课程是否存在，是否属于该教师*/
/****  增删查改 完成，，加入后续 判断细节即可，，删除无需加入判断技能返回 成功与 失败*/
@RestController
@Api(tags = "教师课程下管理班级信息管理专用的 Controller")
public class Teacher_Course_Class_Controller {
    @Autowired
    private I_Teacher_Course_Class_Service i_teacher_course_class_service;

    /*** 1、查询课程下 所有的班级 根据  教师编号 和 课程编号
     * @param CourseCreatorNo
     * @param CourseNo***/
    @ApiOperation(value = "教师查询课程下构建的班级信息 ")
    @RequestMapping(value = "/teacher/course/class/select", method = RequestMethod.GET)
    public Object TeacherCourseClassSelect(@RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("课程编号") String CourseNo,
                                           HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_Class_Table> list = new ArrayList<Teacher_Course_Class_Table>();
        list = i_teacher_course_class_service.Teacher_Course_Class_Select(CourseCreatorNo, CourseNo);
        return Public_Result.success("查询成功！！",list);
    }


    /***  2、 创建课程下的班级  直接插入创建课程班级的实体类
     、 * @param teacher_course****/
    @ApiOperation(value = "教师新增班级课程信息 ")
    @RequestMapping(value = "/teacher/course/class/insert", method = RequestMethod.POST)
    public Object TeacherCourseClassInsert(@RequestParam("课程编号") String CourseNo,
                                           @RequestParam("课程名称") String CourseName,
                                           @RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("教师名称") String CourseCreatorName,
                                           @RequestParam("课程班级编号") String CourseClassNo,
                                           @RequestParam("课程班级名称") String CourseClassName,
                                           HttpSession session){
        /********  构建参数实体并赋值   **********/
        Teacher_Course_Class_Table teacher_course_class_table = new Teacher_Course_Class_Table();
        teacher_course_class_table.setCourseName(CourseName);
        teacher_course_class_table.setCourseNo(CourseNo);
        teacher_course_class_table.setCourseCreatorName(CourseCreatorName);
        teacher_course_class_table.setCourseCreatorNo(CourseCreatorNo);
        teacher_course_class_table.setCourseClassName(CourseClassName);
        teacher_course_class_table.setCourseClassNo(CourseClassNo);

        /*********   传入实体 数据，判断是否新增课程成功  *******/
        boolean flag = i_teacher_course_class_service.Teacher_Course_Class_Insert(teacher_course_class_table);
        if (flag){
            return Public_Result.success("班级创建成功",teacher_course_class_table);
        }
        return Public_Result.error("班级创建失败");
    }



    /***  3、 修改课程下的班级  直接插入新的创建课程班级的实体类
     、 * @param teacher_course****/
    @ApiOperation(value = "教师修改班级课程信息 ")
    @RequestMapping(value = "/teacher/course/class/update", method = RequestMethod.POST)
    public Object TeacherCourseClassUpdate(@RequestParam("课程编号") String CourseNo,
                                           @RequestParam("班级号") String CourseClassNo,
                                           @RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("新的课程班级名称") String CourseClassName,
                                           HttpSession session){

        /********  构建参数实体并赋值   **********/
        Teacher_Course_Class_Table teacher_course_class_table = new Teacher_Course_Class_Table();

        teacher_course_class_table.setCourseNo(CourseNo);
        teacher_course_class_table.setCourseCreatorNo(CourseCreatorNo);
        teacher_course_class_table.setCourseClassNo(CourseClassNo);

        teacher_course_class_table.setCourseClassName(CourseClassName);

        /*********   传入实体 数据，判断是否新增课程成功  *******/
        boolean flag = i_teacher_course_class_service.Teacher_Course_Class_Update(teacher_course_class_table);
        if (flag){
            return Public_Result.success("班级修改成功");
        }
        return Public_Result.error("班级修改失败");
    }




    /***  4、 删除课程下的班级  直接插入创建课程班级的实体类
     、 * @param teacher_course****/
    @ApiOperation(value = "教师删除班级课程信息 ")
    @RequestMapping(value = "/teacher/course/class/delete", method = RequestMethod.POST)
    public Object TeacherCourseClassDelete(@RequestParam("课程编号") String CourseNo,
                                           @RequestParam("教师编号") String CourseCreatorNo,
                                           @RequestParam("班级编号") String CourseClassNo,
                                           HttpSession session) {
        boolean flag = i_teacher_course_class_service.Teacher_Course_Class_Delete(CourseCreatorNo, CourseNo, CourseClassNo);
        if (flag) {
            return Public_Result.success("班级删除成功");
        }
        return Public_Result.error("班级删除失败");
    }

}
