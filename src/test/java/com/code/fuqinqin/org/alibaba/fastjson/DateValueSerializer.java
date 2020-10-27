package com.code.fuqinqin.org.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author fuqinqin3
 * @date 2020-10-26
 * */
public class DateValueSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        System.out.println("#771354 "+Date.class.getTypeName()+" - "+type.getTypeName());
        if(!Date.class.getTypeName().equals(type.getTypeName())){
            return;
        }
        jsonSerializer.write(((Date)o).getTime());
    }
}
