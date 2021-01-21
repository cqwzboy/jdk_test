package com.code.fuqinqin.designPattern.builder;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 10:29
 */
public class Main {
    public static void main(String[] args) {
        MarketHandOfGod director = new MarketHandOfGod();
        IPhone phone = director.build(MarketHandOfGod.HUAWEI);
        System.out.println(phone.getName());
    }
}
