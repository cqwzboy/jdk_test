package com.code.fuqinqin.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuqinqin3
 * @date 2020-05-15
 * */
public class WebsocketClientTest {
    private WebSocketClient webSocketClient;

    @Before
    public void init() throws URISyntaxException {
        Map<String, String> header = new HashMap<>();
        header.put("clientType", "MOCK");
        header.put("user", "user_2");
        webSocketClient = new WebSocketClient(new URI("ws://10.9.10.41:8088/jtalk/websocket"), header){
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("onOpen.....");
            }

            @Override
            public void onMessage(String s) {
                System.out.println("onMessage.....，message={}" + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("onClose.....");
            }

            @Override
            public void onError(Exception e) {
                System.out.println("onError.....");
                e.printStackTrace();
            }
        };
        webSocketClient.connect();
        while (!webSocketClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
            System.out.println("正在连接...");
        }

        heartbeat();
    }

    @Test
    public void test(){
        webSocketClient.send("{\n" +
                "\t\t\t\"_v\": \"1.1\",\n" +
                "\t\t\t\"recordBizType\": \"JDJR\",\n" +
                "\t\t\t\"companyId\": \"1\",\n" +
                "\t\t\t\"tenantId\": \"110\",\n" +
                "\t\t\t\"msgId\": \"03557311380781422229\",\n" +
                "\t\t\t\"content\": \"你发一个试试-1111\",\n" +
                "\t\t\t\"msgType\": \"DIALOGUE\",\n" +
                "\t\t\t\"contentType\": \"TEXT\",\n" +
                "\t\t\t\"to\": \"2357235135135132\",\n" +
                "\t\t\t\"toUserName\": \"用户1\",\n" +
                "\t\t\t\"from\": \"8998325625252352\",\n" +
                "\t\t\t\"fromUserName\": \"用户2\",\n" +
                "\t\t\t\"groupId\": \"test_skg\",\n" +
                "\t\t\t\"dialogId\": \"80e7be99eb8d4c5d9ce79f020d353958\"\n" +
                "\t\t}");
        System.out.println("消息发送完毕...");

        synchronized (WebsocketClientTest.class){
            try {
                WebsocketClientTest.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void heartbeat() {
        new Thread(() -> {
            while (true){
                webSocketClient.send("{\n" +
                        "                \"_v\":\"1.1\",\n" +
                        "                \"platform\":\"GOLD_SHOP\",\n" +
                        "                \"msgType\":\"HEART_BEAT\"\n" +
                        "            }");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
