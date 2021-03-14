package com.graduate.project.PublicConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeConfig {
    public static Date Timedes(String time) {

        String fi = ":00";
        time = time + fi;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date newtime = formatter.parse(time);
            //return Public_Result.success("转换成功",newtime);
            return newtime;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
           // return Public_Result.error("日期格式错误！");
        }
        return null;
    }



    public static Object Timedes2(String time) {
        /**  规定转换后的时间格式  ****/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map res = new HashMap<>();
        /**  转换失败  基本都是ParseException 错误  ****/
        try {
            Date newtime = formatter.parse(time);
            return newtime;
        } catch (ParseException e) {
            return Public_Result.error("时间格式存在错误");
        } catch (Exception e) {
            return Public_Result.error("时间格式存在错误");
        }
        //return Public_Result.error("时间格式存在错误");
    }

    }

