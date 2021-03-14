package com.graduate.project.PublicConfig;

import org.springframework.security.core.parameters.P;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatetimeConfig {


    public static Object Timedes(String time) {
        /**  规定转换后的时间格式  ****/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map res = new HashMap<>();
        /**  转换失败  基本都是ParseException 错误  ****/
        try {
            /***  开始 转换  ***/
            Date newtime = formatter.parse(time);

            /***  转换过程 没有报错 ，进行裁剪判断，保障 年月日的大小*/
            /****  Begin  ****/
            String YMD = time.substring(0,10);
            String cf[] = YMD.split("-");

            Integer year = Integer.valueOf(cf[0]);
            Integer month = Integer.valueOf(cf[1]);
            Integer day = Integer.valueOf(cf[2]);

            if ( year%4 ==0 && year%100 != 0){  // 能整除4 但不能整除 100；；闰年
                // 2月 29
                if (month<1 || month > 12){
                    //return "月份应该在1-12之间";
                    return Public_Result.error("月份应该在1-12之间");
                }
                //  判断 大小月 与 2月，开始
                if (month == 2){  // 闰年 2月
                    if (day<1 || day > 29){
                        //return "本年2月最多29天";
                        return Public_Result.error("本年2月最多29天");
                    }
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        //return "本年大月最多31天";
                        return Public_Result.error("本年大月最多31天");
                    }
                }else {
                    if (day<1 || day > 30){
                        //return "本年小月最多30天";
                        return Public_Result.error("本年小月最多30天");
                    }
                }  ///  结束
////////////////////////////

            }else if (year % 400 ==0){  // 能整除 400，，闰年
                // 2月 29
                if (month<1 || month > 12){
                    //return "月份应该在1-12之间";
                    return Public_Result.error("月份应该在1-12之间");
                }
                //  判断 大小月 与 2月，开始
                if (month == 2){  // 闰年 2月
                    if (day<1 || day > 29){
                        //return "本年2月最多29天";
                        return Public_Result.error("本年2月最多29天");
                    }
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        //return "本年大月最多31天";
                        return Public_Result.error("本年大月最多31天");
                    }
                }else {
                    if (day<1 || day > 30){
                       // return "本年小月最多30天";
                        return Public_Result.error("本年小月最多30天");
                    }
                }  ///  结束
                ///////////////////////////////////
            }else {  //  其余的 普通年
                // 2月 29
                if (month<1 || month > 12){
                    //return "月份应该在1-12之间";
                    return Public_Result.error("月份应该在1-12之间");
                }
                //  判断 大小月 与 2月，开始
                if (month == 2){  // 年 2月
                    if (day<1 || day > 28){
                        //return "本年2月最多28天";
                        return Public_Result.error("本年2月最多28天");
                    }
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        //return "本年大月最多31天";
                        return Public_Result.error("本年大月最多31天");
                    }
                }else {
                    if (day<1 || day > 30){
                        //return "本年小月最多30天";
                        return Public_Result.error("本年小月最多30天");
                    }
                }  ///  结束
            }

            /****  Stop  *****/



            /***  转换过程 没有报错 ，进行裁剪判断，保障 时分秒的大小*/
            /****  Begin  ****/
            String HMS = time.substring(11);
            String df[] = HMS.split(":");

            Integer hour = Integer.valueOf(df[0]);
            Integer minute = Integer.valueOf(df[1]);
            Integer second = Integer.valueOf(df[2]);

            if (hour<0 || hour > 23){
                //return "小时应该在0-24之间";
                return Public_Result.error("小时应该在0-24之间");
            }
            if (minute<0 || minute > 59){
                //return "分钟应该在0-60之间";
                return Public_Result.error("分钟应该在0-60之间");
            }
            if (second<0 || second > 59){
                //return "秒应该在0-60之间";
                return Public_Result.error("秒应该在0-60之间");
            }
            /****  Stop  *****/



            return newtime;
        } catch (ParseException e) {
            //return "日期格式错误";
            return Public_Result.error("日期格式错误");
        } catch (Exception e) {
            //return "日期格式错误";
            return Public_Result.error("日期格式错误");
        }
    }
}
