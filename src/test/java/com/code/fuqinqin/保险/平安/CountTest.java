package com.code.fuqinqin.保险.平安;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 平安年金计算
 * @author fuqinqin
 * @date 2020-01-06
 * */
public class CountTest {
    private double 保费 = 10000.00;
    private double 保额 = 保费 * 2;
    private double 年金转保额率 = 0.3;
    private int 停止保费年限 = 20;
    private int 无年金窗口期 = 0;
    private int 持有年限 = 40;
    private float 年金到保额转换率 = 0.5f;
    private Map<Integer, Double> 年金轨迹 = new HashMap<>();

    @Test
    public void count(){
        double 本息和 = 0;
        double 成本 = 0;
        double 年金;
        for (int i = 1; i <= 持有年限; i++) {
            本息和 += 保费;
            if(i <= 无年金窗口期){
                成本 += 保费;
                continue;
            }

            年金 = 保额 * 年金转保额率;
            本息和 += 年金;
            年金轨迹.put(i, 年金);
            if(i <= 停止保费年限){// 未超保费期
                成本 += 保费;
            }
            保额 += 年金 * 年金到保额转换率;
        }

        System.out.println("成本 = "+成本);
        System.out.println("本息和 = "+本息和);
        System.out.println("年金轨迹：");
        for (Integer year : 年金轨迹.keySet()) {
            System.out.println("\t年="+year+", 年金="+年金轨迹.get(year)+"元");
        }
    }
}
