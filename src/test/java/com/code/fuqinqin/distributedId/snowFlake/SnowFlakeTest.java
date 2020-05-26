package com.code.fuqinqin.distributedId.snowFlake;

import org.junit.Test;

/**
 * 雪花算法
 *
 * @author fuqinqin3
 * @date 2020-05-26
 * */
public class SnowFlakeTest {
    /**
     * 起始的时间戳
     * 2016-11-26 21:21:05.631
     */
    private final static long START_STMP = 1480166465631L;
    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12;            //序列号占用的位数
    private final static long MACHINE_BIT = 5;              //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;           //数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = (1<<DATACENTER_BIT) - 1;
    private final static long MAX_MACHINE_NUM = (1<<MACHINE_BIT) - 1;
    private final static long MAX_SEQUENCE = (1<<SEQUENCE_BIT) - 1;

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastTime = -1L;//上一次时间戳

    public SnowFlakeTest(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId(){
        long current = getNewstmp();
        if(current < lastTime){
            throw new IllegalArgumentException("currentTime must gte lastTime...");
        }

        if(current == lastTime){// 同一毫秒内请求
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if(sequence == 0){// 毫秒内序列号已达最大值，毫秒内暂停服务
                current = getNextMill();
            }
        }else{// 不同毫秒内，序列号重置为0
            sequence = 0;
        }

        lastTime = current;

        return (current - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastTime) {
            mill = getNewstmp();
        }
        return mill;
    }

    public static void main(String[] args) {
        SnowFlakeTest snowFlakeTest = new SnowFlakeTest(2, 3);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlakeTest.nextId());
        }
    }

    @Test
    public void test_1(){
        long start = System.currentTimeMillis();
        int num = Integer.MAX_VALUE;
        long v;
        for (int i = 0; i < num; i++) {
            v = -1L ^ (-1L << DATACENTER_BIT);
        }
        System.out.println("耗时1："+(System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            v = (1<<DATACENTER_BIT) - 1;
        }
        System.out.println("耗时2："+(System.currentTimeMillis() - start));
    }
}
