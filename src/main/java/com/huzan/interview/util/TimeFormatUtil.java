package com.huzan.interview.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huzan on 16/7/15.
 */
public class TimeFormatUtil {


    public static String dateToString(Date date,int kind){
        SimpleDateFormat sdf=new SimpleDateFormat(getDateFormat(kind));
        String str=sdf.format(date);
        return str;
    }
    public static Date stringToData(String str,int kind){
        SimpleDateFormat sdf = new SimpleDateFormat(getDateFormat(kind));
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date TimeFormat(long timeStamp) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(timeStamp);
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    private static String getDateFormat(int kind) {
        String[] format = { "yyyy-MM-dd", // 0
                "yyyy-MM-dd HH:mm:ss", // 1
                "yyyy",// 2
                "M",// 3
                "dd", // 4
                "yyyy年M月d日H点m分", // 5
                "yyyy年M月d日", // 6
                "H点m分", // 7
                "yyyy/MM/dd HH:mm", // 8
                "HH",// 9
                "mm",// 10
                "yyyyMMdd", // 11
                "yyyyMMddHHmmss", // 12
                "yyyy-MM-dd 23:59:59", // 13
                "HH:mm:ss", // 14
                "yyyy/MM/dd HH:mm:ss", // 15
                "yyyy/MM/dd HH:mm",//16
                "yyyy-MM"//17
        };
        return format[kind];
    }
}
