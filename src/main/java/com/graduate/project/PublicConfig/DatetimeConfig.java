package com.graduate.project.PublicConfig;

import org.springframework.security.core.parameters.P;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/******  创建 时间处理函数，判断输入的时间是否合理  ******/
public class DatetimeConfig {
    public static Object Timedes(String time) {
        /**  规定转换后的时间格式  ****/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*****  创建 Map 作为 Object 对象，用于返回   *******/
        Map res = new HashMap<>();
        /**  转换失败  基本都是ParseException 错误  ****/
        try {
            /***  开始 转换  ***/
            Date newtime = formatter.parse(time);

            /***  第一部分：转换过程 没有报错 ，进行裁剪判断，保障 年月日的大小*/
            /****  Begin  ****/
            String YMD = time.substring(0,10);
            String cf[] = YMD.split("-");

            Integer year = Integer.valueOf(cf[0]);  // 年
            Integer month = Integer.valueOf(cf[1]); // 月
            Integer day = Integer.valueOf(cf[2]);   // 日

            /*******  1、判断 是否 为闰年，方法 能整除 4 且 不能整除 100  Begin  ********/
            if ( year%4 ==0 && year%100 != 0){  // 能整除4 但不能整除 100；；闰年
                /**** 1、判断 输入时间中的月份 是否 符合要求   *****/
                if (month<1 || month > 12){
                    res.put("error","月份应该在1-12之间");
                    return res;
                }
                /**** 2、闰年，判断 输入2月份中的日期 是否 符合要求   *****/
                if (month == 2){  // 闰年 2月
                    if (day<1 || day > 29){
                        res.put("error","本年2月最多29天");
                        return res;
                    }
                 /**** 3、判断 输入月大的时候，输入的日期 是否 符合要求   *****/
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        res.put("error","本年大月最多31天");
                        return res;
                    }
                /**** 4、判断 输入月小的时候，输入的日期 是否 符合要求   *****/
                }else {
                    if (day<1 || day > 30){
                        res.put("error","本年小月最多30天");
                        return res;
                    }
                }  ///  结束
                /*********   第一种 判断 闰年的方法及其 相关操作，结束  Stop  ********/

            /*******  2、判断 是否 为闰年，方法 能整除 400  Begin  ********/
            }else if (year % 400 ==0){  // 能整除 400，，闰年
                /**** 1、判断 输入时间中的月份 是否 符合要求   *****/
                if (month<1 || month > 12){
                    res.put("error","月份应该在1-12之间");
                    return res;
                }
                /**** 2、闰年，判断 输入2月份中的日期 是否 符合要求   *****/
                if (month == 2){  // 闰年 2月
                    if (day<1 || day > 29){
                        res.put("error","本年2月最多29天");
                        return res;
                    }
                /**** 3、判断 输入月大的时候，输入的日期 是否 符合要求   *****/
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        res.put("error","本年大月最多31天");
                        return res;
                    }
                /**** 4、判断 输入月小的时候，输入的日期 是否 符合要求   *****/
                }else {
                    if (day<1 || day > 30){
                        res.put("error","本年小月最多30天");
                        return res;
                    }
                }  ///  结束
            /*********   第二种 判断 闰年的方法及其 相关操作，结束  Stop  ********/


            /*******  3、判断 是否 为闰年后，剩下的普通年份处理  Begin  ********/
            }else {  //  其余的 普通年
                /**** 1、判断 输入时间中的月份 是否 符合要求   *****/
                if (month<1 || month > 12){
                    res.put("error","月份应该在1-12之间");
                    return res;
                }
                /**** 2、非闰年，判断 输入2月份中的日期 是否 符合要求   *****/
                if (month == 2){  // 年 2月
                    if (day<1 || day > 28){
                        res.put("error","本年2月最多28天");
                        return res;
                    }
                /**** 3、判断 输入月大的时候，输入的日期 是否 符合要求   *****/
                }else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if (day<1 || day > 31){
                        res.put("error","本年大月最多31天");
                        return res;
                    }
                /**** 4、判断 输入月小的时候，输入的日期 是否 符合要求   *****/
                }else {
                    if (day<1 || day > 30){
                        res.put("error","本年小月最多30天");
                        return res;
                    }
                }  ///  结束
            }
            /*********   第三种 判断 普通年的方法及其 相关操作，结束  Stop  ********/
    /*********   第一部分时间转换，年月日处理结束  Stop  *****/


            /***  第二部分：转换过程 没有报错 ，进行裁剪判断，保障 时分秒的大小*/
            /****  Begin  ****/
            String HMS = time.substring(11);
            String df[] = HMS.split(":");

            Integer hour = Integer.valueOf(df[0]);
            Integer minute = Integer.valueOf(df[1]);
            Integer second = Integer.valueOf(df[2]);

            /********  1、判断输入时间中的  小时，是否符合要求  Begin    ****/
            if (hour<0 || hour > 23){
                res.put("error","小时应该在0-24之间");
                return res;
            }
            /**********   Stop  ************/

            /********  2、判断输入时间中的  分钟，是否符合要求  Begin    ****/
            if (minute<0 || minute > 59){
                res.put("error","分钟应该在0-60之间");
                return res;
            }
            /**********   Stop  ************/

            /********  3、判断输入时间中的  秒钟，是否符合要求  Begin    ****/
            if (second<0 || second > 59){
                res.put("error","秒应该在0-60之间");
                return res;
            }
            /**********   Stop  ************/

    /*********   第二部分时间转换，年月日处理结束  Stop  *****/


    /******  经多方面检查都没有问题，认为时间正确，将其赋值到Map中，并返回  *****/
            res.put("Time",newtime);
            return res;

        /*******  1、捕获异常1，时间格式问题*/
        } catch (ParseException e) {
            res.put("error","日期格式错误");
            return res;
        /*******  2、捕获异常2，其他问题*/
        } catch (Exception e) {
            res.put("error","日期格式错误");
            return res;
        }
    }
}
