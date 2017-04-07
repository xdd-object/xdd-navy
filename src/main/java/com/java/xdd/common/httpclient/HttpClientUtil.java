package com.java.xdd.common.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service("httpClientUtil")
public class HttpClientUtil {
    @Autowired(required = false)
    private CloseableHttpClient httpClient;
    @Autowired(required = false)
    private RequestConfig requestConfig;

    /**
     * get请求
     * @param url 请求url
     * @param params 请求参数，可以为null
     * @return 返回请求结果
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, Object> params) throws URISyntaxException, IOException{
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if(null != params && !params.isEmpty()) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(this.requestConfig);
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
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
     * post请求(表单提交)
     * @param url 请求url
     * @param params 请求参数，可以为null
     * @return 返回请求结果
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doPost(String url, Map<String, Object> params) throws URISyntaxException, IOException{
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (params != null && !params.isEmpty()) {
            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<>(0);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体,并且指定参数的编码为UTF-8
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * post请求(请求头application/json，参数在entity中)
     * @param url 请求url
     * @param json 请求数据(json格式)
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doPostJson(String url, String json) throws URISyntaxException, IOException{
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (json != null) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * put请求(请求头application/json，参数在entity中)
     * @param url 请求url
     * @param json 请求数据(json格式)
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doPutJson(String url, String json) throws URISyntaxException, IOException{
        // 创建http POST请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(this.requestConfig);
        if (json != null) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPut.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPut);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * put请求(表单提交)
     * @param url 请求url
     * @param params 请求参数，可以为null
     * @return 返回请求结果
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doPut(String url, Map<String, Object> params) throws URISyntaxException, IOException{
        // 创建http POST请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(this.requestConfig);
        if (params != null) {
            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<>(0);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体,并且指定参数的编码为UTF-8
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPut.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPut);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 使用post发送delete请求(表单提交)
     * @param url 请求url
     * @param params 请求参数，可以为null
     * @return 返回请求结果
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doDelete(String url, Map<String, Object> params) throws URISyntaxException,IOException {
        if (null == params) params = new HashMap<>();
        params.put("_method", "DELETE");
        return this.doPost(url, params);
    }

    /**
     * delete请求
     * @param url 请求url
     * @return 返回请求结果
     * @throws URISyntaxException
     * @throws IOException
     */
    public HttpResult doDelete(String url) throws URISyntaxException, IOException {
        // 创建http DELETE请求
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpDelete);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }



}
