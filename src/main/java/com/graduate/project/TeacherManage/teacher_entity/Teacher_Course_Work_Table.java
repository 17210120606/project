package com.graduate.project.TeacherManage.teacher_entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "对应Teacher_Course_Work表；用于创建课程的对应班级中的具体作业，并针对到组或者学生",description = "")  // swag 核心

/*
 *   对应Teacher_Course_Work_Table表；用于创建课程的对应班级中的具体作业，并针对到组或者学生
 * */
public class Teacher_Course_Work_Table {
    @ApiModelProperty(value = "教师最初创建课程的课程编号")//,required = true)//true 必填
    private String CourseNo;

    @ApiModelProperty(value = "教师最初创建课程的课程名称")//,required = true)//true 必填
    private String CourseName;

    @ApiModelProperty(value = "教师最初创建此课程的教师编号")//,required = true)//true 必填
    private String CourseCreatorNo;

    @ApiModelProperty(value = "教师最初创建此课程的教师名称")//,required = true)//true 必填
    private String CourseCreatorName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级的班级编号")//,required = true)//true 必填
    private String CourseClassNo;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级的班级名称")//,required = true)//true 必填
    private String CourseClassName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组编号")//,required = true)//true 必填
    private String CourseClassGroupNo;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组名称")//,required = true)//true 必填
    private String CourseClassGroupName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组中具体学生的学号")//,required = true)//true 必填
    private String CourseClassGroupStudentNo;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组中具体学生的姓名")//,required = true)//true 必填
    private String CourseClassGroupStudentName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的编号")//,required = true)//true 必填
    private String CourseWorkNo;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的名称")//,required = true)//true 必填
    private String CourseWorkName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的类型")//,required = true)//true 必填
    private String CourseWorkType;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的及格代码要求")//,required = true)//true 必填
    private Integer CourseWorkPassRequired;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的开始时间")//,required = true)//true 必填
    private Timestamp CourseWorkStartTime;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级下所布置的作业的结束时间")//,required = true)//true 必填
    private Timestamp CourseWorkStopTime;







}
