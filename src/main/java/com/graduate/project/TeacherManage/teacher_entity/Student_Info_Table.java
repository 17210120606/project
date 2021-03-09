package com.graduate.project.TeacherManage.teacher_entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "学生信息表[账户密码总代码量]",description = "")  // swag 核心

/*
*   感觉 登录 就可以先不写了，，作用不大
*
* */

public class Student_Info_Table {

    @ApiModelProperty(value = "学生的学号，也是此数据表的 主键")//,required = true)//true 必填
    private String 学号;

    @ApiModelProperty(value = "学生姓名")//,required = true)//true 必填
    private String 姓名;

    @ApiModelProperty(value = "学生性别 ")//,required = true)//true 必填
    private String 性别;

    @ApiModelProperty(value = "该账户的登录密码")//,required = true)//true 必填
    private String 密码;

    @ApiModelProperty(value = "就读 学校 名称")//,required = true)//true 必填
    private String 学校;

    @ApiModelProperty(value = "所处 院系 名称")//,required = true)//true 必填
    private String 院系;

    @ApiModelProperty(value = "所处 院系 的编号")//,required = true)//true 必填
    private String 系代号;

    @ApiModelProperty(value = "就读专业名称")//,required = true)//true 必填
    private String 专业;

    @ApiModelProperty(value = "所处班别")//,required = true)//true 必填
    private String 班级;

    @ApiModelProperty(value = "该学生辅导员的名称")//,required = true)//true 必填
    private String 辅导员;

    @ApiModelProperty(value = "该学生辅导员的 工作证编号")//,required = true)//true 必填
    private String 工作证号;

    @ApiModelProperty(value = "毕业 所需 的总体 代码累加行数标准")//,required = true)//true 必填
    private Integer 目标总体代码行数;

    @ApiModelProperty(value = "当前的总体 代码累加行数数目")//,required = true)//true 必填
    private Integer 当前总体代码行数;
}
