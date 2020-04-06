package com.code.fuqinqin.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * guava的BloomFilter测试
 *
 * @author fuqinqin
 * @date 2019-08-20
 * */
public class BloomFilterTest {
    private static final int SIZE = Integer.MAX_VALUE/(1<<10);

    @Test
    public void test(){
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), SIZE, 0.0001);
        for (int i = 0; i < SIZE; i++) {
            bloomFilter.put(String.valueOf(i));
        }

        // 判断不存在正确率
        int n = 0;
        for (int i = 0; i < SIZE; i++) {
            if(!bloomFilter.mightContain(String.valueOf(i))){
                n++;
            }
        }
        if(n > 0){
            System.out.println("有坏人逃脱了，逃脱人数 n = "+n);
        }

        // 判断存在正确率
        int m = 0;
        for (int i = SIZE; i < SIZE+10000; i++) {
            if(bloomFilter.mightContain(String.valueOf(i))){
                m++;
            }
        }
        if(m > 0){
            System.out.println("出现了误判 m = "+m);
        }
    }

}
