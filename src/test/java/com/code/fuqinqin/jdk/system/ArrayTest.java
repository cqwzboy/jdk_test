package com.code.fuqinqin.jdk.system;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author fuqinqin3
 * @date 2020-10-20
 * */
public class ArrayTest {

    @Test
    public void test(){
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8};
        showArray(array1);
        int[] array2 = new int[4];
        System.arraycopy(array1, 2, array2, 0, 4);
        showArray(array2);
        int[] array3 = Arrays.copyOfRange(array1, 2, 6);
        showArray(array3);
        int[] array4 = Arrays.copyOf(array1, 4);
        showArray(array4);
    }

    /**
     * 实现数组元素的移除
     * */
    @Test
    public void removeElement(){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        showArray(array);
        System.arraycopy(array, 3, array, 2, 5);
        showArray(array);
        array[array.length-1] = -1;
        showArray(array);
    }

    private void showArray(int[] array){
        int length = array.length;
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            if(i == length-1){
                System.out.println(array[i] + "]");
            }else{
                System.out.print(array[i] + ",");
            }
        }
    }
}
