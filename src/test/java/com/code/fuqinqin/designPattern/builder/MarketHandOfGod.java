package com.code.fuqinqin.designPattern.builder;

import com.code.fuqinqin.designPattern.builder.huawei.HuaweiPhoneBuilder;
import com.code.fuqinqin.designPattern.builder.xiaomi.XiaomiPhoneBuilder;

/**
 * <p>市场上帝之手（导演者）</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 10:24
 */
public class MarketHandOfGod {
    public static final String HUAWEI = "HUAWEI";
    public static final String XIAOMI = "XIAOMI";

    public IPhone build(String manufacturer){
        switch (manufacturer){
            case HUAWEI:
                return product(new HuaweiPhoneBuilder());
            case XIAOMI:
                return product(new XiaomiPhoneBuilder());
            default:
                return null;
        }
    }

    private IPhone product(PhoneBuilder phoneBuilder){
        phoneBuilder.marketAnalysis();
        phoneBuilder.marketResearch();
        phoneBuilder.design();
        phoneBuilder.hardwareProduct();
        phoneBuilder.softwareDevelop();
        phoneBuilder.productTest();
        return phoneBuilder.listed();
    }
}
