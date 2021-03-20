package com.graduate.project.TeacherManage.teacher_controller;

import com.graduate.project.ExcelTemplateForTeacher.ExcelFormatForTeacherConfigStudentInfo_Util;
import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_ClassInfo_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_ClassInfo_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Class_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Course_Service;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Info_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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




  /**  6、下载学生进行批量导入的课程下学生信息模板   ***/
    // Swagger 无法处理
    @ApiOperation(value = "下载学生进行批量导入的课程下学生信息模板 ")
    @RequestMapping(value = "/teacher/course/classinfo/template", method = RequestMethod.GET)
    public ResponseEntity ClassinfoTemplate() throws Exception{
/*        File file = new File(new File(System.getProperty("user.dir")) + File.separator
        + "src" + File.separator + "main" + File.separator + "java" + File.separator
        + "com"+ File.separator +"graduate" + File.separator + "project1"
        + File.separator + "ExcelTemplate" + File.separator + "批量导入课程学生信息模板.xls");
        System.out.println(file.toString());*/
        FileSystemResource file = new FileSystemResource("E:\\最终毕设整理\\project1\\src\\main\\java\\com\\graduate\\project\\ExcelTemplate\\批量导入课程学生信息模板.xls");
        HttpHeaders headers =new HttpHeaders();
        //  成功解决中文问题
        headers.add("Content-Disposition","attachment; filename=" + new String("课程学生信息导入模板.xls".getBytes("gb2312"), "ISO8859-1" ) );
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))   //application/octet-stream  代表以字节流的方式下载
                .body(new InputStreamResource(file.getInputStream()));
    }




    /*** 7、批量新增 课程下的学生信息*/
    @ApiOperation(value = "批量新增 课程下的学生信息")
    @RequestMapping(value = "/teacher/course/classinfo/insertmore", method = RequestMethod.POST)
    public Object TeacherCourseClassInfoInsertMore(@RequestParam("file") MultipartFile file,
                                                    HttpSession session ) throws Exception{

        /*****************  文件 类型 转换  ****************/
        File GeneralFile = multipartFileToFile(file);
        List<Teacher_Course_ClassInfo_Table> teacher_course_classInfo_excel = ExcelFormatForTeacherConfigStudentInfo_Util.Analysis_Excel_Student(GeneralFile);

        /***************  创建  文件上传读取 后的 内容展示对象并赋值，用于展示 本次文件上传的 内容   **************/
        //List<Teacher_Course_ClassInfo_Table> 学生表List = new ArrayList<学生表>();
        Teacher_Course_ClassInfo_Table teacher_course_classInfo_table = new Teacher_Course_ClassInfo_Table();

        /*****************  遍历 转换后的  教师管理学生上课表 列表，并进行 注意 新增 ****************/
        int tol = 0;
        for (Teacher_Course_ClassInfo_Table ClassInfo : teacher_course_classInfo_excel) {
           /****  批量 新增的过程中，不用判断 该数据是否已经存在，，倘若存在，直接修改即可  ****/

            tol = tol + 1;  // 用于 记录 出现问题的是哪一行
            /***  1、先验证 该教师是否存在  ******/
            boolean Teacher_TrueOrFalse = i_teacher_info_service.Teacher_Info_Judge(ClassInfo.getCourseCreatorNo(),ClassInfo.getCourseCreatorName());
            if (Teacher_TrueOrFalse == false){
                teacher_course_classInfo_table=ClassInfo;
                return Public_Result.error("第"+tol+"行所输入的教师身份信息不正确，请重新输入",teacher_course_classInfo_table);
            }

            /***  2、再验证 课程编号是否 存在，即该课程是否存在  ******/
            boolean Course_TrueOrFalse = i_teacher_course_service.Teacher_Course_JudgeCourseTrue(ClassInfo.getCourseNo(),ClassInfo.getCourseName(),ClassInfo.getCourseCreatorNo());
            if (Course_TrueOrFalse == false){
                teacher_course_classInfo_table=ClassInfo;
                return Public_Result.error("第"+tol+"行所输入的课程信息不正确，该课程不存在",teacher_course_classInfo_table);
            }

            /***  3、验证 该班级是否 存在  ****/
            boolean Class_TrueOrFalse = i_teacher_course_class_service.Teacher_Course_Class_JudgeClassNo(ClassInfo.getCourseCreatorNo(), ClassInfo.getCourseNo(), ClassInfo.getCourseClassNo());
            if (Class_TrueOrFalse == false){
                teacher_course_classInfo_table=ClassInfo;
                return Public_Result.error("第"+tol+"行输入的班级信息不存在，请检查输入的信息后，重试！",teacher_course_classInfo_table);
            }

            /***  4、验证 该名学生是否已经存在  ****/
            boolean Student_TrueOrFalse = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_JudgeStudentTrue(ClassInfo.getCourseNo(), ClassInfo.getCourseCreatorNo(), ClassInfo.getCourseClassNo(), ClassInfo.getCourseClassStudentNo());
            if (Student_TrueOrFalse){
                //  正确，证明学生已经存在，直接修改其信息即可
                //  懒得 再去 构建 一个全面 修改的方法，，所以选择直接删掉原有的，然后重新增加，，
                //  因为 已经验证了 其信息基本正确，，所以基本不会出现 删掉后，，添加出错的问题
                i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_Delete(ClassInfo.getCourseNo(), ClassInfo.getCourseCreatorNo(), ClassInfo.getCourseClassNo(), ClassInfo.getCourseClassStudentNo());

                //return Public_Result.error("新增失败，该名学生已存在，请检查信息后重试！");
            }
            /*****  经检验，无问题，进行后续操作    Stop      ****/


            boolean flag = i_teacher_course_classInfo_service.Teacher_Course_ClassInfo_Insert(ClassInfo);
            /********  将其单个 数据 逐一 遍历 添加到 该列表中*/
            //学生表List.add(单个数据);
        }
    return Public_Result.success("批量插入成功!");
    }


    /*************************   以下 为静态方法，进行 MultipartFile 转 普通 File   Begin******************************/
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*******************    Stop  ******************/

 }




