package com.graduate.project.TeacherManage.teacher_controller;

import com.graduate.project.PublicConfig.DatetimeConfig;
import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Info_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Api(tags = "教师创建课程信息管理专用的 Controller")
@RestController
public class Teacher_Course_Controller {
    @Autowired
    private I_Teacher_Course_Service i_teacher_course_service;

    @Autowired
    private I_Teacher_Info_Service i_teacher_info_service;

    /***  1、查询课程  根据教师编号
     * @param TeacherNo***/
    @ApiOperation(value = "教师查询构建的课程信息 ")
    @RequestMapping(value = "/teacher/course/select", method = RequestMethod.GET)
    public Object TeacherCourseSelect(@RequestParam("教师编号") String TeacherNo, HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_Table> list = new ArrayList<Teacher_Course_Table>();
        list = i_teacher_course_service.Teacher_Course_Select(TeacherNo);
        return Public_Result.success("查询成功！！",list);
    }


    /***  1.2、查询课程  根据教师编号,,未开始的
     * @param TeacherNo***/
    @ApiOperation(value = "教师查询构建的尚未开始课程信息 ")
    @RequestMapping(value = "/teacher/course/select/notdo", method = RequestMethod.GET)
    public Object TeacherCourseSelectNotdo(@RequestParam("教师编号") String TeacherNo, HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_Table> list = new ArrayList<Teacher_Course_Table>();
        list = i_teacher_course_service.Teacher_Course_Select(TeacherNo);

        List<Teacher_Course_Table> listnotdo = new ArrayList<Teacher_Course_Table>();
        Date now = new Date();  //  获取当前时间
        for (Teacher_Course_Table info:list){
            if (info.getCourseStartTime().getTime() > now.getTime()){
                listnotdo.add(info);
            }
        }
        return Public_Result.success("查询成功！！",listnotdo);
    }

    /***  1.3、查询课程  根据教师编号,,开始中的
     * @param TeacherNo***/
    @ApiOperation(value = "教师查询构建的正在进行中课程信息 ")
    @RequestMapping(value = "/teacher/course/select/doing", method = RequestMethod.GET)
    public Object TeacherCourseSelectDoing(@RequestParam("教师编号") String TeacherNo, HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_Table> list = new ArrayList<Teacher_Course_Table>();
        list = i_teacher_course_service.Teacher_Course_Select(TeacherNo);

        List<Teacher_Course_Table> listdoing = new ArrayList<Teacher_Course_Table>();
        Date now = new Date();  //  获取当前时间
        for (Teacher_Course_Table info:list){
            if ((info.getCourseStartTime().getTime() < now.getTime()) && (info.getCourseStopTime().getTime() > now.getTime())){
                listdoing.add(info);
            }
        }
        return Public_Result.success("查询成功！！",listdoing);
    }

    /***  1.4、查询课程  根据教师编号,,已结束的
     * @param TeacherNo***/
    @ApiOperation(value = "教师查询构建的已经结束了的课程信息 ")
    @RequestMapping(value = "/teacher/course/select/done", method = RequestMethod.GET)
    public Object TeacherCourseSelectDone(@RequestParam("教师编号") String TeacherNo, HttpSession session){
        /******  构建 返回结果 列表 list  ******/
        List<Teacher_Course_Table> list = new ArrayList<Teacher_Course_Table>();
        list = i_teacher_course_service.Teacher_Course_Select(TeacherNo);

        List<Teacher_Course_Table> listdone = new ArrayList<Teacher_Course_Table>();
        Date now = new Date();  //  获取当前时间
        for (Teacher_Course_Table info:list){
            if (info.getCourseStopTime().getTime() < now.getTime()){
                listdone.add(info);
            }
        }
        return Public_Result.success("查询成功！！",listdone);
    }




    /***  都能 修改 成功了，，但在 时间处理上还存在 转换与判断得问题*/

    /***  2、 创建课程  直接插入创建课程的实体类
    、 * @param teacher_course****/
    @ApiOperation(value = "教师新增课程信息 ")
    @RequestMapping(value = "/teacher/course/insert", method = RequestMethod.POST)
    public Object TeacherCourseInsert(@RequestParam("课程编号") String CourseNo,
                                      @RequestParam("课程名称") String CourseName,
                                      @RequestParam("教师编号") String CourseCreateNo,
                                      @RequestParam("教师名称") String CourseCreateName,
                                      @RequestParam("课程开始时间")  String CourseStartTime,
                                      @RequestParam("课程结束时间") String CourseStopTime,
                                      @RequestParam("课程及格最低要求") Integer PassRequired,
                                      HttpSession session){

        /****  获取开始与结束时间，并进行时间转换，判断该转换过程是否存在问题  ******/

        /***  开始时间判断，，是否存在问题，存在则 报错    Begin   ****/
        Map Start = (Map) DatetimeConfig.Timedes((CourseStartTime));
        Date StartTime = (Date) Start.get("Time");
        String StartError = (String) Start.get("error");
        if (StartError != null){
            return Public_Result.error(StartError);
        }
        /*********   Stop   *************/

        /***  结束时间判断，，是否存在问题，存在则 报错    Begin   ****/
        Map Stop = (Map) DatetimeConfig.Timedes((CourseStopTime));
        Date StopTime = (Date) Stop.get("Time");
        String StopError = (String) Stop.get("error");
        if (StopError != null){
            return Public_Result.error(StopError);
        }
        /*********   Stop   *************/

        /*********   进行开始，结束与当前时间的比较，判断时间输入是否合理  ******/
        //  1、开始 与 当前 比较
        Date now = new Date();  //  获取当前时间
        if (StartTime.getTime() < now.getTime()){
            return Public_Result.error("开始时间不能在当前时间之前，不能在历史中创建课程");
        }
        //  2、开始 与 结束 比较
        if (StartTime.getTime() >= StopTime.getTime()){
            return Public_Result.error("开始时间不能在结束时间之前！");
        }
        /*********   Stop    **********/

        /********   时间转换处理过程结束    Stop   *******/


        /*******  对输入的教师编号与其姓名进行检测，判断该教师是否存在  *****/
        boolean Teacher_TrueOrFalse = i_teacher_info_service.Teacher_Info_Judge(CourseCreateNo, CourseCreateName);
        if (Teacher_TrueOrFalse == false){
            return Public_Result.error("该教师不存在");
        }
        /******   Stop   *********/

        /******  判断 输入的课程编号是否 存在，主键，不能重复  ******/
        boolean CourseNo_TrueOrFalse = i_teacher_course_service.Teacher_Course_JudgeCourseNo(CourseNo);
        if (CourseNo_TrueOrFalse == true){
            return Public_Result.error("该课程编号已经存在");
        }
        /*******  Stop    *********/


        /********  构建参数实体并赋值   **********/
        Teacher_Course_Table teacherCourseTable = new Teacher_Course_Table();
        teacherCourseTable.setCourseNo(CourseNo);
        teacherCourseTable.setCourseName(CourseName);
        teacherCourseTable.setCourseCreatorNo(CourseCreateNo);
        teacherCourseTable.setCourseCreatorName(CourseCreateName);
        teacherCourseTable.setCourseStartTime(StartTime);
        teacherCourseTable.setCourseStopTime(StopTime);
        teacherCourseTable.setPassRequired(PassRequired);

        /*********   传入实体 数据，判断是否新增课程成功  *******/
        boolean flag = i_teacher_course_service.Teacher_Course_Insert(teacherCourseTable);
        if (flag){
            return Public_Result.success("创建课程成功",teacherCourseTable);
        }
        return Public_Result.error("课程创建失败");
    }


    /***  3、 修改课程 插入新的实体类
    、 * @param teacher_course***/
    @ApiOperation(value = "教师修改课程信息 ")
    @RequestMapping(value = "/teacher/course/update", method = RequestMethod.POST)
    public Object TeacherCourseUpdate(@RequestParam("原始的课程编号") String CourseNo,
                                      @RequestParam("新的课程名称") String CourseName,
                                      @RequestParam("原始教师编号") String CourseCreateNo,
                                      @RequestParam("原始教师名称") String CourseCreateName,
                                      @RequestParam("新的课程开始时间") String CourseStartTime,
                                      @RequestParam("新的课程结束时间") String CourseStopTime,
                                      @RequestParam("新的课程及格最低要求") Integer PassRequired,
                                      HttpSession session){

        /****  获取开始与结束时间，并进行时间转换，判断该转换过程是否存在问题  ******/

        /***  开始时间判断，，是否存在问题，存在则 报错    Begin   ****/
        Map Start = (Map) DatetimeConfig.Timedes((CourseStartTime));
        Date StartTime = (Date) Start.get("Time");
        String StartError = (String) Start.get("error");
        if (StartError != null){
            return Public_Result.error(StartError);
        }
        /*********   Stop   *************/

        /***  结束时间判断，，是否存在问题，存在则 报错    Begin   ****/
        Map Stop = (Map) DatetimeConfig.Timedes((CourseStopTime));
        Date StopTime = (Date) Stop.get("Time");
        String StopError = (String) Stop.get("error");
        if (StopError != null){
            return Public_Result.error(StopError);
        }
        /*********   Stop   *************/

        /*********   进行开始，结束与当前时间的比较，判断时间输入是否合理  ******/
        //  1、开始 与 当前 比较
        Date now = new Date();  //  获取当前时间
        if (StartTime.getTime() < now.getTime()){
            return Public_Result.error("开始时间不能在当前时间之前，不能在历史中创建课程");
        }
        //  2、开始 与 结束 比较
        if (StartTime.getTime() >= StopTime.getTime()){
            return Public_Result.error("开始时间不能在结束时间之前！");
        }
        /*********   Stop    **********/

        /********   时间转换处理过程结束    Stop   *******/


        /*******  对输入的教师编号与其姓名进行检测，判断该教师是否存在  *****/
        boolean Teacher_TrueOrFalse = i_teacher_info_service.Teacher_Info_Judge(CourseCreateNo, CourseCreateName);
        if (Teacher_TrueOrFalse == false){
            return Public_Result.error("该教师不存在");
        }
        /******   Stop   *********/

        /******  判断 输入的课程编号是否 存在，主键，不能重复  ******/
        boolean CourseNo_TrueOrFalse = i_teacher_course_service.Teacher_Course_JudgeCourseNo(CourseNo);
        if (CourseNo_TrueOrFalse == false){
            return Public_Result.error("该课程编号不存在");
        }
        /*******  Stop    *********/


        /********  构建参数实体并赋值   **********/
        Teacher_Course_Table teacherCourseTable = new Teacher_Course_Table();
        teacherCourseTable.setCourseNo(CourseNo);
        teacherCourseTable.setCourseName(CourseName);
        teacherCourseTable.setCourseCreatorNo(CourseCreateNo);
        teacherCourseTable.setCourseCreatorName(CourseCreateName);
        teacherCourseTable.setCourseStartTime(StartTime);
        teacherCourseTable.setCourseStopTime(StopTime);
        teacherCourseTable.setPassRequired(PassRequired);

        /*********   传入实体 数据，判断是否修改课程成功  *******/
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

        /*********   判断是否删除课程成功  *******/
        boolean flag = i_teacher_course_service.Teacher_Course_Delete(TeacherNo, CourseNo);
        if (flag){
            return Public_Result.success("删除课程成功");
        }
        return Public_Result.error("课程删除失败");
    }






}
