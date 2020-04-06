package com.code.fuqinqin.pool.apache;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class TestPooledObjectFactory implements PooledObjectFactory<Student> {
    @Override
    public PooledObject<Student> makeObject() throws Exception {
        return new DefaultPooledObject<>(new Student(1001L, "zhangsan",
                new Long(Math.round(Math.random()*10+100)).intValue(),
                new Long(Math.round(Math.random()*10000000)).intValue()));
    }

    @Override
    public void destroyObject(PooledObject<Student> pooledObject) throws Exception {
        Student student = pooledObject.getObject();
        student.setAge(99999);
    }

    @Override
    public boolean validateObject(PooledObject<Student> pooledObject) {
        return pooledObject.getObject() != null;
    }

    @Override
    public void activateObject(PooledObject<Student> pooledObject) throws Exception {
        if(pooledObject.getObject() == null){
            pooledObject = new DefaultPooledObject<>(new Student(1002L, "lisi", 36,
                    new Long(Math.round(Math.random()*10000000)).intValue()));
        }
    }

    /**
     * 当return以后的回调
     * */
    @Override
    public void passivateObject(PooledObject<Student> pooledObject) throws Exception {
        Student student = pooledObject.getObject();
        student.setAge(student.getAge()+9);
    }
}
