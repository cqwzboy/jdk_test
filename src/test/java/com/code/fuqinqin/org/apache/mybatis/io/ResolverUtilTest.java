package com.code.fuqinqin.org.apache.mybatis.io;

import com.code.fuqinqin.designPattern.bridge.work.IWork;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.VFS;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/3 18:08
 */
public class ResolverUtilTest {
    private static final String PATH = "com/code/fuqinqin";

    @Test
    public void VFSTest() throws IOException {
        List<String> list = VFS.getInstance().list(PATH);
        list.forEach(System.out::println);
    }

    @Test
    public void resolverUtilTestTest() {
        ResolverUtil<IWork> util = new ResolverUtil<>();
        util.findImplementations(IWork.class, PATH)
                .getClasses()
                .forEach(clazz -> System.out.println(clazz.getName()));
    }

    @Test
    public void resolverUtilAnnotateTest() {
        ResolverUtil<Component> util = new ResolverUtil<>();
        util.findAnnotated(Component.class, PATH)
                .getClasses()
                .forEach(clazz -> System.out.println(clazz.getName()));
    }

}
