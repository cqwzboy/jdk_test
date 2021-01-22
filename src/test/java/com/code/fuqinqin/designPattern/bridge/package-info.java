/**
 * <p>
 *     桥接模式，分为四个角色：
 *      ① 抽象化角色（Abstraction），可以使接口或者抽象类。
 *      ② 修正抽象化角色（Refine Abstraction）
 *      ③ 实现化角色（Implementor），可以使接口或者抽象类。
 *      ④ 具体实现化角色（Concrete Implementor）
 *
 *     桥梁（Bridge）模式属于结构型设计模式。它将抽象化与实现化脱耦，使得二者可以独立的变化，也就是说将他们之间的强关联变成弱关联，
 *     也就是指在一个软件系统的抽象化和实现化之间使用组合/聚合关系而不是继承关系，从而使两者可以独立的变化。
 *
 *     桥接模式的典型运用就是Java的JDBC规范，和各数据库厂家的具体驱动实现
 *
 *     参考文章：https://mp.weixin.qq.com/s/P3SRUDAINstJL7KqrNqgiA
 *
 *     例子：公司中存在多个部门：产品部，研发部和测试部等，每个部门每天的工作中都有开会和总结汇报等，因此可以抽象出部门（Abstraction），每个具体部门就是Refine Abstraction，
 *          可以抽象出工作（Implementor），具体工作就是Concrete Implementor，顶层依赖是部门每天都会开展工作
 * </p>
 *
 * @author fuqinqin3
 * @date 2021/1/22 9:41
 * @version 1.0
 */
package com.code.fuqinqin.designPattern.bridge;