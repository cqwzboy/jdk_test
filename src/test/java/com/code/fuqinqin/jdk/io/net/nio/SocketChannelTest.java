package com.code.fuqinqin.jdk.io.net.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * SocketChannel 客户端测试
 *
 * 本段代码功能是：连接上服务端后，发送一段文字给服务端，服务端返回一段文本并打印，关闭通道
 *
 * @author fuqinqin3
 * @date 2020-06-17
 * */
public class SocketChannelTest {
    @Test
    public void test() throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", 12345));
        while (!sc.finishConnect()){
            System.out.println("waiting connect server...");
            TimeUnit.SECONDS.sleep(1);
        }
        // TODO do something
        System.out.println("connect successfully...");
        sc.write(ByteBuffer.wrap("Hello Server".getBytes()));

        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (sc.read(buffer) <= 0){
            System.out.println("Waiting receive message from server...");
            TimeUnit.SECONDS.sleep(1);
        }
        buffer.flip();
        int remaining = buffer.remaining();
        byte[] bytes = new byte[remaining];
        for (int i = 0; i < remaining; i++) {
            bytes[i] = buffer.get();
        }
        System.out.println("收到消息："+new String(bytes));

        sc.close();
    }

    @Test
    public void test1(){
        String format = "<body>%s</body>";
        System.out.println(String.format(format, "你好"));
    }
}
