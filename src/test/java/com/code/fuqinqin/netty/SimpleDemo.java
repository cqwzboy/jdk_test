package com.code.fuqinqin.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 入门例子
 * @author fuqinqin
 * @date 2019-12-17
 * */
public class SimpleDemo {

    private static final Integer PORT = 8080;

    /************************************************* Server端代码 *************************************************/
    @Test
    public void serer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ServerIniterHandler());
        // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //是否启用心跳保活机制
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        Channel channel = null;
        try {
            channel = bootstrap.bind(PORT).sync().channel();
            System.out.println("server start...");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class ServerHandler extends SimpleChannelInboundHandler<String> {
        /**
         * 所有的活动用户
         */
        public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        /**
         * 读取消息通道
         *
         * @param context
         * @param s
         * @throws Exception
         */
        @Override
        protected void channelRead0(ChannelHandlerContext context, String s)
                throws Exception {
            Channel channel = context.channel();
            //当有用户发送消息的时候，对其他的用户发送消息
            for (Channel ch : group) {
                if (ch == channel) {
                    ch.writeAndFlush("[you]: " + s + "\n");
                } else {
                    ch.writeAndFlush("[" + channel.remoteAddress() + "]: " + s + "\n");
                }
            }
            System.out.println("[" + channel.remoteAddress() + "]: " + s + "\n");
        }

        /**
         * 处理新加的消息通道
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            for (Channel ch : group) {
                if (ch == channel) {
                    ch.writeAndFlush("[" + channel.remoteAddress() + "] coming");
                }
            }
            group.add(channel);
        }

        /**
         * 处理退出消息通道
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            for (Channel ch : group) {
                if (ch == channel) {
                    ch.writeAndFlush("[" + channel.remoteAddress() + "] leaving");
                }
            }
            group.remove(channel);
        }

        /**
         * 在建立连接时发送消息
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            boolean active = channel.isActive();
            if (active) {
                System.out.println("[" + channel.remoteAddress() + "] is online");
            } else {
                System.out.println("[" + channel.remoteAddress() + "] is offline");
            }
            ctx.writeAndFlush("[server]: welcome");
        }

        /**
         * 退出时发送消息
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();
            if (!channel.isActive()) {
                System.out.println("[" + channel.remoteAddress() + "] is offline");
            } else {
                System.out.println("[" + channel.remoteAddress() + "] is online");
            }
        }

        /**
         * 异常捕获
         *
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
            Channel channel = ctx.channel();
            System.out.println("[" + channel.remoteAddress() + "] leave the room");
            ctx.close().sync();
        }
    }

    /**
     * 服务器初始化
     * */
    public class ServerIniterHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //管道注册handler
            ChannelPipeline pipeline = socketChannel.pipeline();
            //编码通道处理
            pipeline.addLast("decoder", new StringDecoder());
            //转码通道处理
            pipeline.addLast("encoder", new StringEncoder());
//            pipeline.addLast(new HttpServerCodec());
            //聊天服务通道处理
            pipeline.addLast("chat", new ServerHandler());
        }
    }

    /************************************************* Client端代码 *************************************************/
    @Test
    public void client(){
        int number = 1;
        ExecutorService service = Executors.newFixedThreadPool(number);
        for (int i = 0; i < number; i++) {
            service.execute(new NettyClient());
        }
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class NettyClient implements Runnable{
        @Override
        public void run() {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ClientIniterHandler());
            try {
                Channel channel = bootstrap.connect("127.0.0.1", PORT).sync().channel();
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if (StringUtils.isNotEmpty(content)) {
                        if (StringUtils.equalsIgnoreCase(content, "q")) {
                            System.exit(1);
                        }
                        channel.writeAndFlush(content);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                workerGroup.shutdownGracefully();
            }
        }
    }

    private static class ClientHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
            System.out.println(s);
        }
    }

    private static class ClientIniterHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast("http", new HttpClientCodec());
            pipeline.addLast("chat", new ClientHandler());
        }
    }
}
