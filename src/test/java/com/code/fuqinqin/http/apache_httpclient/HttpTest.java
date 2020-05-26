package com.code.fuqinqin.http.apache_httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Http长轮询例子
 *
 * @author fuqinqin3
 * @date 2020-05-25
 */
public class HttpTest {
    private CloseableHttpClient httpClient;

    @Before
    public void init() {
        httpClient = HttpClientBuilder.create().build();
    }

    @Test
    public void test() {
        HttpGet httpGet = new HttpGet("http://localhost:8083/jtalk/service/message/test/conn?type=conn&mock=1&source=h5&webUniqueKey=2324234234234234234234234&_v=1.1");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println("响应状态为:" + httpResponse.getStatusLine());
            if (httpEntity != null) {
                System.out.println("响应内容长度为:" + httpEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(httpEntity));
            }
            test();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if(httpResponse != null){
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
