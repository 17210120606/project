package com.graduate.project.PublicConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConfig {
    public static Public_Result Timedes(String time) {

        String fi = ":00";
        time = time + fi;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date newtime = formatter.parse(time);
            //return Public_Result.success("转换成功",newtime);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return Public_Result.error("日期格式错误！");
        }
        return Public_Result.success("没有变化");
    }
    }

