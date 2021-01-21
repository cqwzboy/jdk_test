package com.code.fuqinqin.designPattern.builder.huawei;

import com.code.fuqinqin.designPattern.builder.IPhone;

/**
 * <p>华为手机</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 10:18
 */
public class HuaweiPhone implements IPhone {
    @Override
    public String getName() {
        return "我是华为手机";
    }
}
