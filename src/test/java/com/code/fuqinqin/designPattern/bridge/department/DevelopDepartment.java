package com.code.fuqinqin.designPattern.bridge.department;

/**
 * <p>研发部门</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 10:00
 */
public class DevelopDepartment extends IDepartment {
    @Override
    public String departmentName() {
        return "开发部";
    }
}
