package com.code.fuqinqin.jdk.io.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

/**
 * 时间服务处理器
 *
 * @author fuqinqin3
 * @date 2020-06-09
 * */
public class TimeServerHandler implements Runnable {
    private Socket clientProxy;

    public TimeServerHandler(Socket socket){
        this.clientProxy = socket;
    }

    @Override
    public void run() {
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientProxy.getInputStream()));
                PrintWriter writer = new PrintWriter(clientProxy.getOutputStream())
        ){
             while (true){
                 String request = reader.readLine();
                 if(!"GET CURRENT TIME".equals(request)){
                     System.out.println("BAD_REQUEST");
                 }else{
                     System.out.println("receive request: " + request);
                     writer.println(Calendar.getInstance().getTime().toLocaleString());
                 }
                 writer.flush();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
