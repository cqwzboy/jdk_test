package com.code.fuqinqin.jdk.面试;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class Test1 {
    @Test
    public void test1(){
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 =Integer.valueOf(59);
        Integer i04 = new Integer(59);

        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
    }

    @Test
    public void test2(){
        int row = 3;
        int col = 3;

        int[][] array = new int[row][col];
        int num = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                array[r][c] = ++num;
            }
        }
        System.out.println(JSON.toJSONString(array));
        spiralMatrix(array);
    }

    /**
     * 螺旋矩阵
     * */
    private void spiralMatrix(int[][] matrix){
        if(matrix == null){
            return;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        for (int c = 0; c < col; c++) {
            System.out.print(matrix[0][c]+" ");
        }
        for (int r = 1; r < row; r++) {
            System.out.print(matrix[r][col-1]+" ");
        }
        for (int c = col-2; c >= 0; c--) {
            System.out.print(matrix[row-1][c]+" ");
        }
        for (int r = row-2; r > 0; r--) {
            System.out.print(matrix[r][0]+" ");
        }

        spiralMatrix(recomMatrix(matrix));
    }

    private int[][] recomMatrix(int[][] source){
        int row = source.length;
        int col = source[0].length;

        if(row<=2 || col<=2){
            return null;
        }

        int[][] target = new int[row - 2][col - 2];
        for (int r = 1; r < row-1; r++) {
            for (int c = 1; c < col-1; c++) {
                target[r-1][c-1] = source[r][c];
            }
        }
        return target;
    }
}
