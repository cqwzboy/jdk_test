package com.code.fuqinqin.org.apache.mybatis.reflection;

import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * <p>一个TypeParameterResolver的测试用例</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/10 17:19
 */
public class TypeParameterResolverDemo {
    /**
     * 父类。属性map存在于父类中
     * */
    public static class ClassA<K, V> {
        protected Map<K, V> map;
        public void setMap(Map<K, V> map){
            this.map = map;
        }
        public Map<K, V> getMap() {
            return this.map;
        }
    }

    /**
     * 子类
     * */
    public static class SubClassA<T> extends ClassA<T, T> {

    }

    public static class TestType {
        SubClassA<Long> sa = new SubClassA<>();

        public static void main(String[] args) throws NoSuchFieldException {
            Field field = ClassA.class.getDeclaredField("map");
            System.out.println(field.getGenericType());
            System.out.println(field.getGenericType() instanceof ParameterizedType);

            Type type = ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, TypeParameterResolverDemo.class);
//            Type type = TypeParameterResolver.resolveFieldType(field, TestType.class.getDeclaredField("sa").getGenericType());
//      Type type = TypeParameterResolver.resolveFieldType(field, SubClassA.class);
            System.out.println(type.getClass());

            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println(parameterizedType.getRawType());
            System.out.println(parameterizedType.getOwnerType());
            for (Type t : parameterizedType.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }
}
