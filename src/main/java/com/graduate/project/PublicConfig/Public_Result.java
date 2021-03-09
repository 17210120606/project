package com.graduate.project.PublicConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/*
 * 公用返回对象
 * */
public class Public_Result {
    private long code;
    private String message;
    private Object obj;

    /*
     * 1、成功 返回结果 信息
     * */
    public static Public_Result success (String message){
        return new Public_Result(200,message,null);
    }
    /*
     * 2、成功 返回结果 内容
     * */
    public static Public_Result success (String message, Object obj){
        return new Public_Result(200,message,obj);
    }

    /*
     * 3、失败 返回结果 信息
     * */
    public static Public_Result error (String message){
        return new Public_Result(500,message,null);
    }
    /*
     * 4、失败 返回结果 内容
     * */
    public static Public_Result error (String message, Object obj){
        return new Public_Result(500,message,obj);
    }



}

