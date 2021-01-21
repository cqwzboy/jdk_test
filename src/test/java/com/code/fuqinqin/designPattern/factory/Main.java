package com.code.fuqinqin.designPattern.factory;

import com.code.fuqinqin.designPattern.factory.huawei.HuaweiFactoryImpl;
import com.code.fuqinqin.designPattern.factory.xiaomi.XiaomiFactoryImpl;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:58
 */
public class Main {
    public static void main(String[] args) {
        IFactory huaweiFactory = new HuaweiFactoryImpl();
        IProduct huaweiPhone = huaweiFactory.buildProduct("PHONE");
        huaweiPhone.show();

        IFactory xiaomiFactory = new XiaomiFactoryImpl();
        IProduct xiaomiSpeaker = xiaomiFactory.buildProduct("SPEAKER");
        xiaomiSpeaker.show();
    }
}
