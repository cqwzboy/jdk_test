package com.code.fuqinqin.http.apache_httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Http长轮询例子
 *
 * @author fuqinqin3
 * @date 2020-05-25
 */
public class HttpTest {
    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;

    @Before
    public void init() {
        httpClient = HttpClientBuilder.create().build();
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setRedirectsEnabled(false)
                .build();
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
            close(httpClient, httpResponse);
        }
    }

    @Test
    public void getTest(){
        String url = "http://localhost:8088/test/1";
        HttpGet httpGet = new HttpGet(url);
        sendGet(httpGet);
    }

    @Test
    public void getAndParamTest() throws URISyntaxException {
        URI uri = buildURI();
        HttpGet httpGet = new HttpGet(uri);
        sendGet(httpGet);
    }

    /**
     * Header测试
     * */
    @Test
    public void getAndHeaderTest() throws URISyntaxException {
        URI uri = buildURI();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("h1", "v1");
        httpGet.addHeader(new BasicHeader("h2", "v2"));
        sendGet(httpGet);
    }

    /**
     * Cookie测试
     * */
    @Test
    public void getAndCookieTest() throws URISyntaxException {
        URI uri = buildURI();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        /*CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("cookie1", "cookie_value_1");
        cookie.setVersion(0);
        cookie.setDomain("/localhost");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        sendGet(httpGet, cookieStore);*/
        httpGet.addHeader("Cookie", "cookie_2=cookie_value_2");
        sendGet(httpGet);
    }

    private URI buildURI() throws URISyntaxException {
        return new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8088")
                .setPath("/test/1")
                .setParameter("id", "110")
                .setParameter("name", "张三")
                .setParameter("age", "19")
                .setParameter("address", "ChengDu")
                .build();
    }

    private void sendGet(HttpGet httpGet) {
        this.sendGet(httpGet, null);
    }

    private void sendGet(HttpGet httpGet, CookieStore cookieStore) {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        try{
            httpResponse = httpClient.execute(httpGet);
            System.out.println("状态码："+httpResponse.getStatusLine());
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpEntity != null){
                System.out.println("响应内容长度为:" + httpEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(httpEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(httpClient, httpResponse);
        }
    }

    private void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse){
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
