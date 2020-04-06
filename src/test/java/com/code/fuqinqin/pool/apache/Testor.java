package com.code.fuqinqin.pool.apache;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Testor {
    @Test
    public void test() throws Exception {
        ObjectPool<Student> objectPool = new GenericObjectPool<>(new TestPooledObjectFactory(), new GenericObjectPoolConfig<>());

        Student student = objectPool.borrowObject();
        System.out.println(student);
        student.setName(student.getName()+"-haha");
        System.out.println(student);
        objectPool.invalidateObject(student);
        objectPool.returnObject(student);
        System.out.println(student);

        Student student1 = objectPool.borrowObject();
        System.out.println(student1);

        System.out.println(student == student1);

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student s = objectPool.borrowObject();
            System.out.println(s);
            if(i == 6){
                objectPool.returnObject(s);
                continue;
            }
            students.add(s);
        }
        for (Student s : students) {
            objectPool.returnObject(s);
        }
    }
}
