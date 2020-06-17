package com.code.fuqinqin.jdk.io.net.nio;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Byte缓冲区
 *
 * 不同的Buffer实现类，api都是相似的，当我们学会了ByteBuffer，其他的类型的Buffer自然而言也就会了
 *
 * 概念上，缓冲区是包在一个对象内的基本数据元素数组。 Buffer 类相比一个简单数组的优点 是它将关于数据的数据内容和信息包含在一个单一的对象中。
 *  Buffer 类以及它专有的子类定义了 一个用于处理数据缓冲区的 API。
 *
 * 容量（ Capacity）
 *      缓冲区能够容纳的数据元素的最大数量，可以理解为数组的长度。 这一容量在缓冲区创建时被设定，并且永远不能被改变。
 *
 * 上界（ Limit）
 *      缓冲区的第一个不能被读或写的元素。或者说，缓冲区中现存元素的计数。
 *
 * 位置（ Position）
 *      下一个要被读或写的元素的索引。Buffer类提供了get( )和 put( )函数 来读取或存入数据，position位置会自动进行相应的更新。
 *
 * 标记（ Mark）
 *      一个备忘位置。调用 mark( )来设定 mark = postion。调用 reset( )设定 position = mark。标记在设定前是未定义的(undefined)。
 *
 * BufferのAPI
 * public abstract class Buffer {
 *     //JDK1.4时，引入的api
 *     public final int capacity( )//返回此缓冲区的容量
 *     public final int position( )//返回此缓冲区的位置
 *     public final Buffer position (int newPositio)//设置此缓冲区的位置
 *     public final int limit( )//返回此缓冲区的限制
 *     public final Buffer limit (int newLimit)//设置此缓冲区的限制
 *     public final Buffer mark( )//在此缓冲区的位置设置标记
 *     public final Buffer reset( )//将此缓冲区的位置重置为以前标记的位置
 *     public final Buffer clear( )//清除此缓冲区
 *     public final Buffer flip( )//反转此缓冲区
 *     public final Buffer rewind( )//重绕此缓冲区
 *     public final int remaining( )//返回当前位置与限制之间的元素数
 *     public final boolean hasRemaining( )//告知在当前位置和限制之间是否有元素
 *     public abstract boolean isReadOnly( );//告知此缓冲区是否为只读缓冲区
 *
 *     //JDK1.6时引入的api
 *     public abstract boolean hasArray();//告知此缓冲区是否具有可访问的底层实现数组
 *     public abstract Object array();//返回此缓冲区的底层实现数组
 *     public abstract int arrayOffset();//返回此缓冲区的底层实现数组中第一个缓冲区元素的偏移量
 *     public abstract boolean isDirect();//告知此缓冲区是否为直接缓冲区
 * }
 *
 * ByteBufferのAPI
 * public abstract class ByteBuffer {
 *
 *     //缓冲区创建相关api
 *     public static ByteBuffer allocateDirect(int capacity)
 *     public static ByteBuffer allocate(int capacity)
 *     public static ByteBuffer wrap(byte[] array)
 *     public static ByteBuffer wrap(byte[] array,int offset, int length)
 *
 *     //缓存区存取相关API
 *     public abstract byte get( );//从当前位置position上get，get之后，position会自动+1
 *     public abstract byte get (int index);//从绝对位置get
 *     public abstract ByteBuffer put (byte b);//从当前位置上普通，put之后，position会自动+1
 *     public abstract ByteBuffer put (int index, byte b);//从绝对位置上put
 *
 * }
 *
 * @author fuqinqin3
 * @date 2020-06-09
 * */
public class ByteBufferTest {
    /**
     * 创建缓冲区 -- allocate(), wrap()
     *
     * 输出：
     * capacity=10,limit=10,position=0,hasRemaining:true,remaining=10,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=10,limit=10,position=0,hasRemaining:true,remaining=10,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=10,limit=7,position=2,hasRemaining:true,remaining=5,hasArray=true,isReadOnly=false,arrayOffset=0
     * */
    @Test
    public void createBuffer(){
        // case1: allocate直接分配，内部将创建一个数组
        ByteBuffer allocate = ByteBuffer.allocate(10);
        // case2: 根据已有的byte数组创建
        byte[] bytes = new byte[10];
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        // case3: 根据一个已有的数组指定区间创建
        ByteBuffer wrapOffset = ByteBuffer.wrap(bytes, 2, 5);
        print(allocate, wrap, wrapOffset);
    }

    /**
     * 缓冲区存储 -- put()
     *
     * 输出：
     * capacity=10,limit=10,position=0,hasRemaining:true,remaining=10,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=10,limit=10,position=5,hasRemaining:true,remaining=5,hasArray=true,isReadOnly=false,arrayOffset=0
     * */
    @Test
    public void putAndGet(){
        ByteBuffer allocate = ByteBuffer.allocate(10);
        print(allocate);
        byte h=0x48;
        byte e=0x65;
        byte l=0x6C;
        byte o=0x6F;
        allocate.put(h).put(e).put(l).put(l).put(o);
        print(allocate);

        // 绝对位置赋值
        allocate.put(0, "m".getBytes()[0]);
        print(allocate);
    }

    /**
     * 翻转函数 -- flip()
     *
     * flip函数的功能是 limit=position; position=0;
     *
     * 输出：
     * capacity=20,limit=20,position=0,hasRemaining:true,remaining=20,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=20,position=11,hasRemaining:true,remaining=9,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=11,position=0,hasRemaining:true,remaining=11,hasArray=true,isReadOnly=false,arrayOffset=0
     * */
    @Test
    public void flipTest(){
        String data = "Hello World";
        ByteBuffer buffer = ByteBuffer.allocate(20);
        print(buffer);
        buffer.put(data.getBytes());
        print(buffer);
        // 翻转，准备读数据
        buffer.flip();
        // 与flip()同等功能
//        buffer.limit(buffer.position()).position(0);
        print(buffer);
    }

    /**
     * 回退函数 -- rewind()
     *
     * 功能 position=0; 用于在flip()后重读历史数据
     *
     * 输出：
     * capacity=20,limit=20,position=0,hasRemaining:true,remaining=20,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=20,position=9,hasRemaining:true,remaining=11,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=9,position=0,hasRemaining:true,remaining=9,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=9,position=1,hasRemaining:true,remaining=8,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=9,position=0,hasRemaining:true,remaining=9,hasArray=true,isReadOnly=false,arrayOffset=0
     * */
    @Test
    public void rewindTest(){
        String data = "Hello NIO";
        ByteBuffer buffer = ByteBuffer.allocate(20);
        print(buffer);  // capacity=20,limit=20,position=0,hasRemaining:true,remaining=20,hasArray=true,isReadOnly=false,arrayOffset=0
        buffer.put(data.getBytes());
        print(buffer);  // capacity=20,limit=20,position=9,hasRemaining:true,remaining=11,hasArray=true,isReadOnly=false,arrayOffset=0
        buffer.flip();
        print(buffer);  // capacity=20,limit=9,position=0,hasRemaining:true,remaining=9,hasArray=true,isReadOnly=false,arrayOffset=0
        buffer.get();
        print(buffer);  // capacity=20,limit=9,position=1,hasRemaining:true,remaining=8,hasArray=true,isReadOnly=false,arrayOffset=0
        buffer.rewind();
        print(buffer);  // capacity=20,limit=9,position=0,hasRemaining:true,remaining=9,hasArray=true,isReadOnly=false,arrayOffset=0
    }

    /**
     * 遍历数据 -- get()
     * */
    @Test
    public void showData(){
        String data = "Hello NIO";
        ByteBuffer buffer = ByteBuffer.allocate(20).put(data.getBytes());
        System.out.println("readData1: " + readString(buffer));
        System.out.println("readData2: " + readString2(buffer));
    }

    /**
     * clear()
     *
     * 功能：position=0;
     *       limit = capacity;
     *       mark = -1;
     *
     * 输出：
     * capacity=20,limit=20,position=13,hasRemaining:true,remaining=7,hasArray=true,isReadOnly=false,arrayOffset=0
     * readString: Hello Clear()
     * capacity=20,limit=20,position=0,hasRemaining:true,remaining=20,hasArray=true,isReadOnly=false,arrayOffset=0
     * readString:
     * */
    @Test
    public void clearTest(){
        ByteBuffer buffer = ByteBuffer.allocate(20).put("Hello Clear()".getBytes());
        print(buffer);
        System.out.println("readString: "+readString(buffer));

        buffer.clear();

        print(buffer);
//        buffer.position(13);
        System.out.println("readString: "+readString(buffer));
    }

    /**
     * 标记函数 -- mark()
     *
     * 功能：mark = position
     *
     * 输出：
     * capacity=20,limit=20,position=12,hasRemaining:true,remaining=8,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=20,position=5,hasRemaining:true,remaining=15,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=20,position=2,hasRemaining:true,remaining=18,hasArray=true,isReadOnly=false,arrayOffset=0
     * */
    @Test
    public void markTest(){
        ByteBuffer buffer = ByteBuffer.allocate(20).put("Hello Mark()".getBytes());
        print(buffer);

        buffer.position(2).mark().position(5);

        print(buffer);

        buffer.reset();

        print(buffer);
    }

    /**
     * 压缩函数 -- compact()
     *
     * 压缩对于使缓冲区与您从端口中读入的数据（包）逻辑块流的同步来说也许是一种便利的方法(处理粘包、解包的问题)。
     *
     * 注意：使用前先调用 flip() 函数才能达到预期目的
     *
     * 功能：position = remaining();
     *       limit = capacity;
     *
     * 输出：
     * capacity=20,limit=20,position=15,hasRemaining:true,remaining=5,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=15,position=0,hasRemaining:true,remaining=15,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=15,position=2,hasRemaining:true,remaining=13,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=20,position=13,hasRemaining:true,remaining=7,hasArray=true,isReadOnly=false,arrayOffset=0
     * 10 = t
     * 11 = (
     * 12 = )
     * 13 = (
     * 14 = )
     * 重读： llo Compact()
     * */
    @Test
    public void compactTest(){
        ByteBuffer buffer = ByteBuffer.allocate(20).put("Hello Compact()".getBytes());
        print(buffer);
        buffer.flip();
        print(buffer);
        buffer.get();
        buffer.get();
        print(buffer);
        buffer.compact();
        print(buffer);
        System.out.println("10 = "+(char)buffer.get(10));
        System.out.println("11 = "+(char)buffer.get(11));
        System.out.println("12 = "+(char)buffer.get(12));
        System.out.println("13 = "+(char)buffer.get(13));
        System.out.println("14 = "+(char)buffer.get(14));

        // 重新读取
        System.out.println("重读： "+readString(buffer));
    }

    /**
     * 复制函数 - duplicate()
     *
     * 缓冲区的复制有分两种：
     * 1、完全复制：调用duplicate()函数或者asReadOnlyBuffer()函数
     * 2、部分复制：调用slice函数
     *
     * 输出：
     * capacity=20,limit=20,position=0,hasRemaining:true,remaining=20,hasArray=true,isReadOnly=false,arrayOffset=0
     * capacity=20,limit=10,position=6,hasRemaining:true,remaining=4,hasArray=true,isReadOnly=false,arrayOffset=0
     * isReadOnly: true
     * capacity=4,limit=4,position=0,hasRemaining:true,remaining=4,hasArray=true,isReadOnly=false,arrayOffset=6
     * */
    @Test
    public void duplicateTest(){
        // duplicate()
        ByteBuffer buffer = ByteBuffer.allocate(20).put("Hello Duplicate()".getBytes());
        buffer.position(3).limit(10).mark().position(6);
        ByteBuffer duplicate = buffer.duplicate();
        buffer.clear();
        print(buffer);
        print(duplicate);

        // asReadOnlyBuffer()
        ByteBuffer readOnlyBuffer = duplicate.asReadOnlyBuffer();
//        readOnlyBuffer.put("1".getBytes()); // java.nio.ReadOnlyBufferException
        System.out.println("isReadOnly: "+readOnlyBuffer.isReadOnly());

        // slice()
        ByteBuffer slice = duplicate.slice();
        print(slice);
    }

    /**
     * 直接缓存区
     *
     * 回顾我们之前讲解UNIX 五种IO模型中的读取数据的过程，读取数据总是需要通过内核空间传递到用户空间，而往外写数据总是要通过用户空间到内核空间。
     * JVM堆栈属于用户空间。 而我们这里提到的直接缓冲区，就是内核空间的内存。内核空间的内存在java中是通过Unsafe这个类来调用的。
     * */
    @Test
    public void directBufferTest(){
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(20).put("Hello DirectBuffer".getBytes());
        System.out.println("直接缓存区："+readString(directBuffer));
    }

    /**
     * 内存映射缓冲区 - MappedByteBuffer
     *
     * 映射缓冲区是带有存储在文件，通过内存映射来存取数据元素的字节缓冲区。映射缓冲区通常是直接存取内存的，只能通过 FileChannel 类创建。
     * 映射缓冲区的用法和直接缓冲区类似，但是 MappedByteBuffer 对象可以处理独立于文件存取形式的的许多特定字符。
     *
     * MappedByteBuffer在大文件处理方面性能比较高，如果你在做一个文件存储服务器，可以考虑使用MappedByteBuffer。
     * */
    @Test
    public void mappedByteBufferTest(){

    }

    /**
     * 遍历方式一（效率最高）
     * */
    private static String readString(ByteBuffer buffer){
        buffer.flip();
        int remaining = buffer.remaining();
        byte[] bytes = new byte[remaining];
        for (int i = 0; i < remaining; i++) {
            bytes[i] = buffer.get();
        }
        return new String(bytes);
    }

    /**
     * 遍历方式二
     * */
    private static String readString2(ByteBuffer buffer){
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        for (int i=0; buffer.hasRemaining(); i++){
            bytes[i] = buffer.get();
        }
        return new String(bytes);
    }

    private static void print(Buffer... buffers){
        for (Buffer buffer : buffers) {
            System.out.println("capacity="+buffer.capacity()
                    +",limit="+buffer.limit()
                    +",position="+buffer.position()
                    +",hasRemaining:"+buffer.hasArray()
                    +",remaining="+buffer.remaining()
                    +",hasArray="+buffer.hasArray()
                    +",isReadOnly="+buffer.isReadOnly()
                    +",arrayOffset="+buffer.arrayOffset());
        }
    }
}
