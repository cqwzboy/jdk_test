package com.code.fuqinqin.designPattern.bridge;

import com.code.fuqinqin.designPattern.bridge.department.DevelopDepartment;
import com.code.fuqinqin.designPattern.bridge.department.IDepartment;
import com.code.fuqinqin.designPattern.bridge.department.ProductDepartment;
import com.code.fuqinqin.designPattern.bridge.department.TestDepartment;
import com.code.fuqinqin.designPattern.bridge.work.IWork;
import com.code.fuqinqin.designPattern.bridge.work.Meeting;
import com.code.fuqinqin.designPattern.bridge.work.Summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 10:05
 */
public class Main {
    public static void main(String[] args) {
//        new Main().test1();
        new Main().test2();
    }

    /**
     * 常规使用
     */
    private void test1() {
        IDepartment productDepartment = new ProductDepartment();
        IDepartment developDepartment = new DevelopDepartment();
        IDepartment testDepartment = new TestDepartment();

        IWork meeting = new Meeting();
        IWork summary = new Summary();

        productDepartment.setWork(meeting);
        productDepartment.work();
        productDepartment.setWork(summary);
        productDepartment.work();

        developDepartment.setWork(meeting);
        developDepartment.work();
        developDepartment.setWork(summary);
        developDepartment.work();

        testDepartment.setWork(meeting);
        testDepartment.work();
        testDepartment.setWork(summary);
        testDepartment.work();
    }

    /**
     * 使用ServiceLoader加载实现
     * 若项目使用Spring框架，则直接注入接口实现集合
     */
    private void test2() {
        List<IDepartment> departmentList = new ArrayList<>();
        ServiceLoader<IDepartment> departmentLoader = ServiceLoader.load(IDepartment.class);
        departmentLoader.forEach(departmentList::add);
        Map<String, List<IDepartment>> departmentMap = departmentList.stream().collect(Collectors.groupingBy(IDepartment::departmentName));

        List<IWork> workList = new ArrayList<>();
        ServiceLoader<IWork> workLoader = ServiceLoader.load(IWork.class);
        workLoader.forEach(workList::add);
        Map<String, List<IWork>> workMap = workList.stream().collect(Collectors.groupingBy(IWork::name));

        IDepartment department = departmentMap.get("开发部").get(0);
        department.setWork(workMap.get("meeting").get(0));
        department.work();
    }
}
