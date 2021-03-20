package com.graduate.project.ExcelTemplateForTeacher;

import com.graduate.project.TeacherManage.teacher_entity.Teacher_Course_ClassInfo_Table;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelFormatForTeacherConfigStudentInfo_Util {
    public static List<Teacher_Course_ClassInfo_Table> Analysis_Excel_Student(File file) throws Exception{
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        List<Teacher_Course_ClassInfo_Table> list = new ArrayList<Teacher_Course_ClassInfo_Table>();

        if(sheet!=null){
            for(int rowNum =1;rowNum<=sheet.getLastRowNum();rowNum++){
                HSSFRow row = sheet.getRow(rowNum);
                if(row==null){
                    continue;
                }

                Teacher_Course_ClassInfo_Table teacher_course_classInfo_table = new Teacher_Course_ClassInfo_Table();

                /*******************   格式转换方法语句    Begin  ******************/
/*                System.out.println(Excel单元格格式化工具.formatCell(row.getCell(0)));
                String Id = Excel单元格格式化工具.formatCell(row.getCell(0));
                int intNum = Double.valueOf(Id).intValue();  //  将 带小数 的String 类型 经过两次转换，变成 int
                System.out.println(Integer.valueOf(intNum));
                教师管理学生上课表.setId(intNum);*/
                /*******************    Stop  ******************/
                teacher_course_classInfo_table.setCourseNo(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(0)));
                teacher_course_classInfo_table.setCourseName(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(1)));
                teacher_course_classInfo_table.setCourseCreatorNo(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(2)));
                teacher_course_classInfo_table.setCourseCreatorName(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(3)));
                teacher_course_classInfo_table.setCourseClassNo(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(4)));
                teacher_course_classInfo_table.setCourseClassName(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(5)));
                teacher_course_classInfo_table.setCourseClassGroupNo(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(6)));
                teacher_course_classInfo_table.setCourseClassGroupName(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(7)));
                teacher_course_classInfo_table.setCourseClassStudentNo(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(8)));
                teacher_course_classInfo_table.setCourseClassStudentName(ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(9)));
                teacher_course_classInfo_table.setStudentFinalGrade(Double.valueOf((ExcelFormatForTeacherConfigStudentInfo.formatCell(row.getCell(10)))));
                list.add(teacher_course_classInfo_table);
            }
        }
        return list;
    }



}
