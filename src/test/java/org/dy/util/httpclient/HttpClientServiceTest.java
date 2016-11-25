package org.dy.util.httpclient;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SHENG on 2016/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-httpclient.xml")
public class HttpClientServiceTest {

    @Autowired
    private HttpClientService httpClientService;

    String uri = "http://127.0.0.1:8081/warn/json";

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("params", "{\"StockCode\":\"SH000001\",\"funcno\":200101,\"UserID\":\"2015082300000662\",\"WarnInfo\":\"1:10:1|2:9:1\"}");
        return params;
    }


    @org.junit.Test
    public void doGet() throws Exception {
        Map<String, String> params = getParams();
        httpClientService.doGet(uri, params);
    }

    @org.junit.Test
    public void doGet1() throws Exception {
        System.out.println(httpClientService.doGet("http://www.baidu.com"));
    }

    @org.junit.Test
    public void doPost() throws Exception {
        Map<String, String> params = getParams();
        System.out.println(httpClientService.doPost(uri, params));
    }

    @org.junit.Test
    public void doPost1() throws Exception {

    }

    @org.junit.Test
    public void doPostJson() throws Exception {

    }

}