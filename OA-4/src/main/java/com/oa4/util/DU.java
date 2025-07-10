package com.oa4.util;

import java.util.Calendar;


public class DU {
    //
    public static String getNowString() {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH) + 1;
        int D = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        int mm = calendar.get(Calendar.MILLISECOND);
        return Y + "-" + f(M) + "-" + f(D) + " " + f(h) + ":" + f(m) + ":" + f(s) + ":" + f(mm);
    }

    public static String getNowSortString() {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH) + 1;
        int D = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return Y + "-" + f(M) + "-" + f(D);
    }

    public static String getNowAM() {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH) + 1;
        int D = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return Y + "-" + f(M) + "-" + f(D) + " 08:30:00:00";
    }

    public static String getNowPM() {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH) + 1;
        int D = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return Y + "-" + f(M) + "-" + f(D) + " 17:30:00:00";
    }

    //判断当前毫秒值是否06:00-08:30
    public static boolean IsAMMillis(Long now) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY , 6);
        calendar.set(Calendar.MINUTE , 0);
        calendar.set(Calendar.SECOND , 0);
        Long am1 = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY , 8);
        calendar.set(Calendar.MINUTE , 30);
        calendar.set(Calendar.SECOND , 0);
        Long am2 = calendar.getTimeInMillis();
        return now > am1 && now < am2;
    }

    //判断当前毫秒值是否在今天之内
    public static boolean IsToDayMillis(Long now) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY , 0);
        calendar.set(Calendar.MINUTE , 0);
        calendar.set(Calendar.SECOND , 0);
        Long am1 = calendar.getTimeInMillis();
        System.out.println(am1);
        calendar.set(Calendar.HOUR_OF_DAY , 24);
        calendar.set(Calendar.MINUTE , 0);
        calendar.set(Calendar.SECOND , 0);
        Long am2 = calendar.getTimeInMillis();
        System.out.println(am2);
        return now > am1 && now < am2;
    }
    //判断当前毫秒值是否13:00-14:30
    public static boolean IsPMMillis(Long now) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY , 13);
        calendar.set(Calendar.MINUTE , 0);
        calendar.set(Calendar.SECOND , 0);
        Long am1 = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY , 14);
        calendar.set(Calendar.MINUTE , 30);
        calendar.set(Calendar.SECOND , 0);
        Long am2 = calendar.getTimeInMillis();
        return now > am1 && now < am2;
    }

    public static String f(int a) {
        if (a < 10) {
            return "0" + String.valueOf(a);
        }
        return String.valueOf(a);
    }

    public static void main(String[] args) {
//        System.out.println(IsAMMillis(System.currentTimeMillis()));
//        System.out.println(IsPMMillis(System.currentTimeMillis()));
//        String str = String.format("%tF %<tT" , System.currentTimeMillis());
//        String[] arr = str.split("\\s+");
//        System.out.println(getNowSortString());
//        System.out.println(arr[0].equals(getNowSortString()));
//        System.out.println(IsToDayMillis(System.currentTimeMillis()));
//        System.out.println(SysFun.md5("123"));

    }
}
