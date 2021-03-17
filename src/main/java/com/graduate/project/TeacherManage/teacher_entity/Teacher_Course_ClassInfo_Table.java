package com.graduate.project.TeacherManage.teacher_entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "对应Teacher_Course_ClassInfo表；用于创建课程的对应班级中的小组与学生信息",description = "")  // swag 核心

/*
 *   对应Teacher_Course_ClassInfo表；用于创建课程的对应班级中的小组与学生信息
 * */

public class Teacher_Course_ClassInfo_Table {
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

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组中的具体学生的学号")//,required = true)//true 必填
    private String CourseClassStudentNo;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组中的具体学生的姓名")//,required = true)//true 必填
    private String CourseClassStudentName;

    @ApiModelProperty(value = "教师在此课程下，创建相应的教学班级内部的小组中的具体学生的姓名的最终成绩")//,required = true)//true 必填
    private Double StudentFinalGrade;


}
