package com.graduate.project.TeacherManage.teacher_entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "所有学生的上课课表 信息",description = "")  // swag 核心
/*
*   查询 学生 的上课 课程信息
* */
public class Student_Course_Table {
    @ApiModelProperty(value = "学生的学号")//,required = true)//true 必填
    private String 学号;

    @ApiModelProperty(value = "学生姓名")//,required = true)//true 必填
    private String 姓名;

    @ApiModelProperty(value = "课程名称")//,required = true)//true 必填
    private String 课程名称;

    @ApiModelProperty(value = "课程编号")//,required = true)//true 必填
    private String 课程编号;

    @ApiModelProperty(value = "授课教师名称")//,required = true)//true 必填
    private String 授课教师;

    @ApiModelProperty(value = "授课教师编号")//,required = true)//true 必填
    private String 教师编号;

    @ApiModelProperty(value = "该课程 及格 所需的 最终代码行数")//,required = true)//true 必填
    private Integer 课程目标代码量;

    @ApiModelProperty(value = "该课程 当前已提交的 代码行数")//,required = true)//true 必填
    private Integer 课程当前代码量;
}
