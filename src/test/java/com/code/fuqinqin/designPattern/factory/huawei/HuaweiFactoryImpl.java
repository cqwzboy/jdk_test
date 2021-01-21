package com.code.fuqinqin.designPattern.factory.huawei;

import com.code.fuqinqin.designPattern.factory.IFactory;
import com.code.fuqinqin.designPattern.factory.IProduct;

/**
 * <p>具体工厂实现-华为</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:43
 */
public class HuaweiFactoryImpl implements IFactory {
    @Override
    public IProduct buildProduct(String productType) {
        if ("PHONE".equals(productType))
            return new HuaweiPhone();
        else if ("NETBOOK".equals(productType))
            return new HuaweiNetbook();
        return null;
    }
}
