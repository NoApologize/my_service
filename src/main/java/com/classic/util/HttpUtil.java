package com.classic.util;

import com.classic.constant.SystemConstant;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpUtil {

    //获取httpClient对象
    public static HttpClient getHttpClient() {
        //最简单的方式
        HttpClient httpclient = HttpClientBuilder.create().build();
        return httpclient;
    }

    //httpClient for get
    public static HttpResponse httpClientForGet(String url, HttpClient httpClient) throws Exception {
        HttpGet get = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(get);
        return httpResponse;
    }

    //httpClient for post
    public static HttpResponse httpClientForPost(String url, List<NameValuePair> list, HttpClient httpClient) throws Exception {
        HttpPost post = new HttpPost(url);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        post.setEntity(urlEncodedFormEntity);
        HttpResponse httpResponse = httpClient.execute(post);
        return httpResponse;
    }

    //restful json get
    public static String restTemplateForGet(String url, HttpHeaders headers) {
        ResponseEntity<byte[]> response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        return new String(response.getBody());
    }

    //restful json get
    public static String restTemplateForGet(String url) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(SystemConstant.RESTTEMPLATE_READTIMEOUT);
        requestFactory.setConnectTimeout(SystemConstant.RESTTEMPLATE_CONNECTTIMEOUT);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate.getForObject(url, String.class);
    }

    //restful json post
    public static String restTemplateForPost(String url, HttpHeaders headers, String jsonString) {
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonString, headers);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(SystemConstant.RESTTEMPLATE_READTIMEOUT);
        requestFactory.setConnectTimeout(SystemConstant.RESTTEMPLATE_CONNECTTIMEOUT);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate.postForObject(url, formEntity, String.class);
    }

    public static String httpGet(String url) {
        String resData;
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = client.execute(httpGet);
            resData = EntityUtils.toString(response.getEntity(), "utf-8");

            return resData;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return "false";
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
            return "false";
        }
    }

}
