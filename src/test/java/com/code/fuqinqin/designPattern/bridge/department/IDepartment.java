package com.code.fuqinqin.designPattern.bridge.department;

import com.code.fuqinqin.designPattern.bridge.work.IWork;
import lombok.Data;

/**
 * <p>部门抽象</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 9:51
 */
@Data
public abstract class IDepartment {
    private IWork work;

    public abstract String departmentName();

    public void work(){
        System.out.println("----------------------- 部门："+departmentName()+" ---------------------");
        work.process();
    }
}
