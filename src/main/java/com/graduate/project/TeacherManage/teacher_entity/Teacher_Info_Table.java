package com.graduate.project.TeacherManage.teacher_entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "对应Teacher_Info表；用于教师登录",description = "")  // swag 核心
public class Teacher_Info_Table {
    @ApiModelProperty(value = "教师登录的教师编号，主键")//,required = true)//true 必填
    private String TeacherNo;

    @ApiModelProperty(value = "教师登录的教师名字")//,required = true)//true 必填
    private String TeacherName;

    @ApiModelProperty(value = "教师登录的MD5加密密码")//,required = true)//true 必填
    private String TeacherPassword;
}
