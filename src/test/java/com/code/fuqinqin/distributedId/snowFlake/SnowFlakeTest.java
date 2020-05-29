package com.code.fuqinqin.distributedId.snowFlake;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 雪花算法
 *
 * @author fuqinqin3
 * @date 2020-05-26
 * */
public class SnowFlakeTest {
    public static class SnowFlake{
        /**
         * 起始的时间戳
         * 2016-11-26 21:21:05.631
         */
        private final static long START_STMP = 1480166465631L;
//    private final static long START_STMP = System.currentTimeMillis();
    /*private static long START_STMP;

    static {
        try {
            START_STMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("1952-01-01 00:00:00.000").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

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

        public SnowFlake(long datacenterId, long machineId) {
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
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(2, 3);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            snowFlake.nextId();
//            System.out.println(snowFlakeTest.nextId());
        }
        System.out.println("耗时："+(System.currentTimeMillis() - start));
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void test_1(){
        long start = System.currentTimeMillis();
        int num = Integer.MAX_VALUE;
        long v;
        for (int i = 0; i < num; i++) {
            v = -1L ^ (-1L << 5);
        }
        System.out.println("耗时1："+(System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            v = (1<<5) - 1;
        }
        System.out.println("耗时2："+(System.currentTimeMillis() - start));
    }

    @Test
    public void test_2() throws UnsupportedEncodingException {
        String companyId = "1";
        String tenantId = "12375421354151351";
        long nextId = new SnowFlake(2, 3).nextId();
        String data = nextId + "-" + companyId + "-" + tenantId;
        String code = Base64.encodeBase64String(data.getBytes("UTF-8"));
        System.out.println(code);
        System.out.println(new String(Base64.decodeBase64(code)));
    }

    @Test
    public void test_3() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("3080-01-01 00:12:00.259");
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test_4(){
        String data = "235wehdsjfhn3io52";
        char[] chars = data.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length-1){
                sb.append((int)chars[i]).append(",");
            }else {
                sb.append((int)chars[i]);
            }
        }
        System.out.println("Ascii："+sb.toString());
    }

    @Test
    public void test_5(){
        String[] data = {"12", "17", "23", "98", "76"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append((char)Integer.parseInt(data[i]));
        }
        System.out.println(sb.toString());
    }

    @Test
    public void test_6() throws UnsupportedEncodingException {
        String companyId = "1015881664249334";
        String dialogType = "01";
        byte[] bytes = (companyId + dialogType).getBytes("UTF-8");
        String base64 = Base64.encodeBase64String(bytes);
        System.out.println(base64);
    }

    @Test
    public void test_7(){
        byte[] bytes = {-28, -67, -96};
        String string = new String(bytes);
        System.out.println(string);
    }

    private static final String[] BASE_50 = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
            "r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X"};
    /**
     * 越位标识，指超过50时判定为越位
     * */
    private static final String OFF_SIDE = "Y";
    /**
     * 补位
     * */
    private static final String FILL = "Z";
    @Test
    public void test_8() throws ParseException {
        String companyId = "1005081664840334";
        String dialogType = "02";
        String data = companyId + dialogType;
        System.out.println(data);
        String encode = encode(data);
        System.out.println(encode);
        System.out.println(decode(encode));

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2018-11-20 00:00:00.000").getTime());
    }

    private String encode(String data){
        if(StringUtils.isBlank(data) || !StringUtils.isNumeric(data)){
            throw new IllegalArgumentException("非法入参: "+data);
        }
        boolean needFill = false;
        if(data.length() % 2 != 0){
            needFill = true;
        }
        char[] chars = data.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean onFill;
        for (int i = 0; i < chars.length; i+=2) {
            onFill = needFill && i==chars.length-1;
            int j;
            if(onFill){// 补位
                j = Integer.parseInt(String.valueOf(chars[i]) + "0");
            }else{
                j = Integer.parseInt(String.valueOf(chars[i]) + String.valueOf(chars[i + 1]));
            }
            if(j >= 50){
                if(onFill){
                    sb.append(OFF_SIDE).append(FILL).append(BASE_50[j%50]);
                }else {
                    sb.append(OFF_SIDE).append(BASE_50[j%50]);
                }
            }else{
                if(onFill){
                    sb.append(FILL).append(BASE_50[j]);
                }else{
                    sb.append(BASE_50[j]);
                }
            }
        }
        return sb.toString();
    }

    private String decode(String data){
        if(StringUtils.isBlank(data)){
            throw new IllegalArgumentException("非法入参: "+data);
        }
        String[] strings = data.trim().split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equals(OFF_SIDE)){
                i++;
                if(strings[i].equals(FILL)){
                    i++;
                    sb.append(String.valueOf((parseIndex(strings[i]) + 50) / 10));
                }else {
                    sb.append(String.valueOf(parseIndex(strings[i]) + 50));
                }
            }else if(strings[i].equals(FILL)){
                i++;
                sb.append(String.valueOf(parseIndex(strings[i]) / 10));
            }else{
                String s = String.valueOf(parseIndex(strings[i]));
                if(s.length() == 1){
                    s = "0" + s;
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }

    private int parseIndex(String s){
        if(StringUtils.isBlank(s)){
            throw new IllegalArgumentException("非法入参："+s);
        }
        for (int index = 0; index < BASE_50.length; index++) {
            if(s.equals(BASE_50[index])){
                return index;
            }
        }
        throw new IllegalArgumentException("入参 "+s+" 不在BASE_51内");
    }

    @Test
    public void test_9(){
        int count = 10000;
        long start = System.currentTimeMillis();
        StringBuilder url = new StringBuilder();
        for (int i = 0; i < count; i++) {
            url.append((int)(Math.random()*254+1))
                    .append(".")
                    .append((int)(Math.random()*254+1))
                    .append(".")
                    .append((int)(Math.random()*254+1))
                    .append(".")
                    .append((int)(Math.random()*254+1));
            show(url.toString());
            url.setLength(0);
        }
        System.out.println("耗时："+(System.currentTimeMillis() - start));
        System.out.println("次数="+count+", 算法碰撞数："+IS_COUNT+", ip碰撞数="+IP_COUNT);
    }

    private static List<String> ips = new ArrayList<>();
    private static List<Integer> is = new ArrayList<>();
    private static int IS_COUNT = 0;
    private static int IP_COUNT = 0;
    private void show(String ip){
        if(ips.contains(ip)){
            IP_COUNT++;
        }else{
            ips.add(ip);
        }
//        System.out.println("ip = "+ip);
        String[] splits = ip.split("\\.");
        int i = (Integer.parseInt(splits[0])<<24)
                | (Integer.parseInt(splits[1])<<16)
                | (Integer.parseInt(splits[2])<<8)
                | (Integer.parseInt(splits[3]));
        if(is.contains(i)){
            System.out.println("算法碰撞：ip="+ip+", i="+i);
            IS_COUNT++;
        }else{
            System.out.println(i);
            is.add(i);
        }
//        System.out.println(i);
    }
}
