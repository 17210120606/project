package com.graduate.project.TeacherManage.teacher_entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "对应Teacher_Course表；用于创建课程",description = "")  // swag 核心
/*
*   对应Teacher_Course表；用于让创建课程
*   不过也是 仅限于 对课程进行总体性的 基础操作
* */
public class Teacher_Course_Table {
    @ApiModelProperty(value = "教师最初创建课程的课程编号；此表的主键")//,required = true)//true 必填
    private String CourseNo;

    @ApiModelProperty(value = "教师最初创建课程的课程名称")//,required = true)//true 必填
    private String CourseName;

    @ApiModelProperty(value = "教师最初创建此课程的教师编号")//,required = true)//true 必填
    private String CourseCreatorNo;

    @ApiModelProperty(value = "教师最初创建此课程的教师名称")//,required = true)//true 必填
    private String CourseCreatorName;

    @ApiModelProperty(value = "教师最初创建此课程的课程开始时间")//,required = true)//true 必填
    private Timestamp CourseStartTime;

    @ApiModelProperty(value = "教师最初创建此课程的课程结束时间")//,required = true)//true 必填
    private Timestamp CourseStopTime;

    @ApiModelProperty(value = "教师最初创建此课程的课程及格代码要求")//,required = true)//true 必填
    private Integer PassRequired;

}
