package com.graduate.project.TeacherManage.teacher_controller;

import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Api(tags = "教师创建课程信息管理专用的 Controller")
@RestController
public class Teacher_Course_Controller {
    @Autowired
    private I_Teacher_Course_Service i_teacher_course_service;

    /***  1、查询课程  根据教师编号
     * @param TeacherNo***/
    @ApiOperation(value = "教师查询构建的课程信息 ")
    @RequestMapping(value = "/teacher/course/select", method = RequestMethod.GET)
    public Object TeacherCourseSelect(@RequestParam("教师编号") String TeacherNo, HttpSession session){
        List<Teacher_Course_Table> list = new ArrayList<Teacher_Course_Table>();
        list = i_teacher_course_service.Teacher_Course_Select(TeacherNo);
        return Public_Result.success("查询成功！！",list);
    }


    /***  2、 创建课程  直接插入创建课程的实体类
    、 * @param teacher_course****/
    @ApiOperation(value = "教师新增课程信息 ")
    @RequestMapping(value = "/teacher/course/insert", method = RequestMethod.GET)
    public Object TeacherCourseInsert(@RequestParam("课程编号") String CourseNo,
                                      @RequestParam("课程名称") String CourseName,
                                      @RequestParam("教师编号") String CourseCreateNo,
                                      @RequestParam("教师名称") String CourseCreateName,
                                      @RequestParam("课程开始时间") Timestamp CourseStartTime,
                                      @RequestParam("课程结束时间") Timestamp CourseStopTime,
                                      @RequestParam("课程及格最低要求") Integer PassRequired,
                                      HttpSession session){
        Teacher_Course_Table teacherCourseTable = new Teacher_Course_Table();
        teacherCourseTable.setCourseNo(CourseNo);
        teacherCourseTable.setCourseName(CourseName);
        teacherCourseTable.setCourseCreatorNo(CourseCreateNo);
        teacherCourseTable.setCourseCreatorName(CourseCreateName);
        teacherCourseTable.setCourseStartTime(CourseStartTime);
        teacherCourseTable.setCourseStopTime(CourseStopTime);
        teacherCourseTable.setPassRequired(PassRequired);

        boolean flag = i_teacher_course_service.Teacher_Course_Insert(teacherCourseTable);
        if (flag){
            return Public_Result.success("创建课程成功",teacherCourseTable);
        }
        return Public_Result.error("课程创建失败");
    }


    /***  3、 修改课程 插入新的实体类
    、 * @param teacher_course***/
    @ApiOperation(value = "教师修改课程信息 ")
    @RequestMapping(value = "/teacher/course/update", method = RequestMethod.GET)
    public Object TeacherCourseUpdate(@RequestParam("课程编号") String CourseNo,
                                      @RequestParam("新的课程名称") String CourseName,
                                      @RequestParam("教师编号") String CourseCreateNo,
                                      @RequestParam("教师名称") String CourseCreateName,
                                      @RequestParam("新的课程开始时间") Timestamp CourseStartTime,
                                      @RequestParam("新的课程结束时间") Timestamp CourseStopTime,
                                      @RequestParam("新的课程及格最低要求") Integer PassRequired,
                                      HttpSession session){
        Teacher_Course_Table teacherCourseTable = new Teacher_Course_Table();
        teacherCourseTable.setCourseNo(CourseNo);
        teacherCourseTable.setCourseName(CourseName);
        teacherCourseTable.setCourseCreatorNo(CourseCreateNo);
        teacherCourseTable.setCourseCreatorName(CourseCreateName);
        teacherCourseTable.setCourseStartTime(CourseStartTime);
        teacherCourseTable.setCourseStopTime(CourseStopTime);
        teacherCourseTable.setPassRequired(PassRequired);

        boolean flag = i_teacher_course_service.Teacher_Course_Update(teacherCourseTable);
        if (flag){
            return Public_Result.success("修改课程成功",teacherCourseTable);
        }
        return Public_Result.error("修改课程失败");
    }



    /***  4、删除课程  根据教师编号和课程编号
     * @param TeacherNo
     * @param CourseNo***/
    @ApiOperation(value = "教师删除构建的课程信息 ")
    @RequestMapping(value = "/teacher/course/delete", method = RequestMethod.GET)
    public Object TeacherCourseDelete(@RequestParam("教师编号") String TeacherNo,
                                      @RequestParam("课程编号") String CourseNo,
                                      HttpSession session){
        boolean flag = i_teacher_course_service.Teacher_Course_Delete(TeacherNo, CourseNo);
        if (flag){
            return Public_Result.success("删除课程成功");
        }
        return Public_Result.error("课程删除失败");
    }
}
