package com.code.fuqinqin.jdk.timezoom;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimezoomTest {
    @Test
    public void test(){

        long t = System.currentTimeMillis();
        System.out.println("long = " + t);
        // current time zone:
        SimpleDateFormat sdf_default = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf_default.format(t));

        SimpleDateFormat sdf_8 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf_8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        System.out.println("GMT0 = " + sdf_8.format(t));
    }
}
