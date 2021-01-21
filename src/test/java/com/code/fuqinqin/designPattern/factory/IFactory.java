package com.code.fuqinqin.designPattern.factory;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:39
 */
public interface IFactory {
    IProduct buildProduct(String productType);
}
