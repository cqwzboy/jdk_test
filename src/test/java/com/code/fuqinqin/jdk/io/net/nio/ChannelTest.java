package com.code.fuqinqin.jdk.io.net.nio;

import org.junit.Test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 通道测试类
 *
 * 这段程序的作用是，在 12345 端口上接受client的请求，一旦接收到client的请求，会给其回复固定的字符串响应"Hello I must be going."
 *
 * @author fuqinqin3
 * @date 2020-06-11
 * */
public class ChannelTest {
    @Test
    public void channelTest() throws IOException, InterruptedException {
        String data = "Hello, i must going.\r\n";
        int port = 12345;
        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        SocketChannel sc;
        while (true) {
            System.out.println("waiting for connection ["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) +"]");
            sc = ssc.accept();
            if(sc == null){
                TimeUnit.SECONDS.sleep(2);
            }else{
                sc.configureBlocking(false);
                ByteBuffer allocate = ByteBuffer.allocateDirect(1024 * 16);
                sc.read(allocate);
                allocate.flip();
                if(!allocate.hasRemaining()){
                    continue;
                }
                int remaining = allocate.remaining();
                byte[] bytes = new byte[remaining];
                for (int i = 0; i < remaining; i++) {
                    bytes[i] = allocate.get();
                }
                System.out.println("Data: " + new String(bytes));
                System.out.println("Incoming connection from: "+sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
