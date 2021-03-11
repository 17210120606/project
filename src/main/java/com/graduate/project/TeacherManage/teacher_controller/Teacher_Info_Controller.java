package com.graduate.project.TeacherManage.teacher_controller;


import com.graduate.project.PublicConfig.Public_Result;
import com.graduate.project.TeacherManage.teacher_entity.Teacher_Info_Table;
import com.graduate.project.TeacherManage.teacher_service.I_Teacher_Info_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(tags = "教师信息管理专用的 Controller")
@RestController
public class Teacher_Info_Controller {
    @Autowired
    private I_Teacher_Info_Service i_teacher_info_service;


    /***  1、教师 登录
     * @param TeacherNo
     * @param TeacherPassword*****/
    @ApiOperation(value = "教师登录 ")
    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST)
    public Object TeacherLogin(@RequestParam("教师编号") String TeacherNo,
                               @RequestParam("登录密码") String TeacherPassword,
                               HttpSession session) {
        /***  使用获取到的 教师编号 和 登录密码 进行登录验证，成功True，，失败False  ******/
        String MD5TeacherPassword = DigestUtils.md5DigestAsHex(TeacherPassword.getBytes());
        boolean flag = i_teacher_info_service.Teacher_Info_Login(TeacherNo, MD5TeacherPassword);
        if (flag) {
            return Public_Result.success("登陆成功");
        }
        return Public_Result.error("登录失败");
    }


    /***  2、教师 查询 个人信息
     * @param TeacherNo*****/
    @ApiOperation(value = "教师查询个人信息 ")
    @RequestMapping(value = "/teacher/select", method = RequestMethod.GET)
    public Object TeacherLogin(@RequestParam("教师编号") String TeacherNo,
                               HttpSession session) {
        Teacher_Info_Table teacher_info_table = new Teacher_Info_Table();
        teacher_info_table = i_teacher_info_service.Teacher_Info_SelectHimself(TeacherNo);
        teacher_info_table.setTeacherPassword("null");
        return Public_Result.success("查询成功", teacher_info_table);
    }


    /***  3、教师 修改 个人信息
     * @param TeacherNo
     * @param OldTeacherPassword
     * @param NewTeacherPassword*****/
    @ApiOperation(value = "教师修改个人信息 ")
    @RequestMapping(value = "/teacher/select", method = RequestMethod.POST)
    public Object TeacherLogin(@RequestParam("教师编号") String TeacherNo,
                               @RequestParam("原始密码") String OldTeacherPassword,
                               @RequestParam("新密码") String NewTeacherPassword,
                               HttpSession session) {
        String MD5OldTeacherPassword = DigestUtils.md5DigestAsHex(OldTeacherPassword.getBytes());
        String MD5NewTeacherPassword = DigestUtils.md5DigestAsHex(NewTeacherPassword.getBytes());
        /***  使用获取到的 新增账户信息 进行修改验证，成功True，，失败False  ******/
        boolean flag = i_teacher_info_service.Teacher_Info_UpdateInfo(TeacherNo, MD5OldTeacherPassword, MD5NewTeacherPassword);
        if (flag) {
            return Public_Result.success("修改成功");
        }
        return Public_Result.error("修改失败");

    }
}