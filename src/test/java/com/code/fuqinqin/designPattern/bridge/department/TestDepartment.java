package com.code.fuqinqin.designPattern.bridge.department;

/**
 * <p>测试部门</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 10:00
 */
public class TestDepartment extends IDepartment {
    @Override
    public String departmentName() {
        return "测试部";
    }
}
