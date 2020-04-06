package com.code.fuqinqin.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemoryError示例
 *
 * @author fuqinqin
 * */
public class OomDemo {
    public static void main(String[] args){
        List<OomObject> oomObjects = new ArrayList<>();

        while (true) {
            oomObjects.add(new OomObject());
        }
    }

    static class OomObject{

    }
}
