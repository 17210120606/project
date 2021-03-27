package com.graduate.project.TeacherManage.teacher_dao;


import org.springframework.stereotype.Repository;

@Repository
public interface I_Teacher_Course_Work_Dao {
    /*** 1、按班级查询 自己布置的所有作业信息
     * **   直接显示出 作业的 列表，提交数 和  未提交数  总共的
     * **   比方说返回：：整个班，交了多少人，还剩多少人，何时结束，何时开始的，作业名称
     * **   输入的 参数为  班级 即可****/



    /*** 2、在具体班级下，再次加上学生信息，查询  某学生的具体作业的信息
     *  **  输入参数为： 班级，作业名称，学号
     *  **  返回值：  单个的 作业实体*/


    /*** 3、创建 作业
     *  **  感觉需要多加一个 作业的基础表，专门用于创建作业
     *  **  让后学生 提交作业的话，数量 基础表增加，，但内容在这个复杂的表里面
     *  **  但复杂表里面的数据可能会有点多*/

    /*** 4、修改作业信息*/

    /***  5、删除作业信息*/

    /****  简单作业表内容：：  作业编号，名称，
     *                        创建者编号，名称
     *                        班级编号，名称
     *                        作业类型，要求
     *                        开始时间，结束时间
     *                  查询时，自动返回数据 还包括：已提交人数 和 未提交人数
     *                  将此 构建成新的 实体类型
     *          共计  3个实体   2个表*/


}