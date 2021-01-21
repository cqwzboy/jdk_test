package com.code.fuqinqin.designPattern.factory.xiaomi;

import com.code.fuqinqin.designPattern.factory.IFactory;
import com.code.fuqinqin.designPattern.factory.IProduct;

/**
 * <p>小米工厂</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:54
 */
public class XiaomiFactoryImpl implements IFactory {
    @Override
    public IProduct buildProduct(String productType) {
        if ("PHONE".equals(productType))
            return new XiaomiPhone();
        else if ("SPEAKER".equals(productType))
            return new XiaomiSpeaker();
        return null;
    }
}
