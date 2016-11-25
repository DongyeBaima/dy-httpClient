package org.dy.util.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ���� :
 * ��Ȩ : Copyright-(c) 2016
 * ��˾ : Thinkive
 *
 * @author ���ӿ�
 * @version 2016-11-25 16:37
 */
@Component
public class HttpClientService {

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;

    /**
     * ִ��GET����
     */
    public String doGet(String url) throws ClientProtocolException, IOException {
        // ����http GET����
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);

        CloseableHttpResponse response = null;
        try {
            // ִ������
            response = httpClient.execute(httpGet);
            // �жϷ���״̬�Ƿ�Ϊ200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * ���в�����GET����
     */
    public String doGet(String url, Map<String, String> params)
            throws ClientProtocolException, IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        for (String key : params.keySet()) {
            uriBuilder.addParameter(key, params.get(key));
        }
        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * ִ��POST����
     */
    public HttpResult doPost(String url, Map<String, String> params) throws IOException {
        // ����http POST����
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (params != null) {
            // ����2��post������һ����scope��һ����q
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                parameters.add(new BasicNameValuePair(key, params.get(key)));
            }
            // ����һ��form��ʽ��ʵ��
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // ������ʵ�����õ�httpPost������
            httpPost.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // ִ������
            response = httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * ִ��POST����
     */
    public HttpResult doPost(String url) throws IOException {
        return this.doPost(url, null);
    }

    /**
     * �ύjson����
     */
    public HttpResult doPostJson(String url, String json) throws ClientProtocolException, IOException {
        // ����http POST����
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            // ����һ��form��ʽ��ʵ��
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // ������ʵ�����õ�httpPost������
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // ִ������
            response = this.httpClient.execute(httpPost);
            return new HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
