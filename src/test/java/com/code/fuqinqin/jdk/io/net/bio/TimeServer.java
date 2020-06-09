package com.code.fuqinqin.jdk.io.net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 时间服务
 *
 * @author fuqinqin3
 * @date 2020-06-09
 * */
public class TimeServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(12345);
            System.out.println("TimeServer Started on 12345...");
            while (true){
                Socket accept = server.accept();
                new Thread(new TimeServerHandler(accept)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server != null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
