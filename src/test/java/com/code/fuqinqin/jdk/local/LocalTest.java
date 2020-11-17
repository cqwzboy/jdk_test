package com.code.fuqinqin.jdk.local;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fuqinqin3
 * @date 2020-11-17
 * */
public class LocalTest {

    @Test
    public void availableLocalesTest(){
        Set<String> set1 = Arrays.stream(Locale.getAvailableLocales()).map(Locale::toString).collect(Collectors.toSet());
        Set<String> set2 = Arrays.stream(Locale.getAvailableLocales()).filter(l -> !l.equals(Locale.ROOT)).map(Locale::toString).collect(Collectors.toSet());
        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        String ext = FilenameUtils.getExtension("F:\\工作\\2020\\11\\17 prometheus-server问题梳理\\promethus-server-sql.sql");
        System.out.println("ext = " + ext);
    }

}
