package com.java.xdd.common.httpclient;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * 这个是代码配置httpclient
 * 已使用Spring管理，可见配置文件
 * 使用httpClientUtil的方法
 */
@Deprecated
public class HttpClient {
    CloseableHttpClient client = HttpClients.createDefault();
    PoolingHttpClientConnectionManager manager;
    HttpClientBuilder clientBuilder;
    public void aa() {
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(100); //连接池最大连接数
        manager.setDefaultMaxPerRoute(50); //每个路由基础的连接
        // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建立
        manager.setValidateAfterInactivity(30000); //每个路由基础的连接
        //目标主机的最大连接数(视情况配置该属性)
        /*HttpHost httpHost = new HttpHost("127.0.0.1", 8080);
        manager.setMaxPerRoute(new HttpRoute(httpHost), 50);*/
    }
    public void bb() {
        clientBuilder = HttpClientBuilder.create();
        clientBuilder.setRetryHandler(this.getHttpRequestRetryHandler()); //请求重试处理
        clientBuilder.setConnectionManager(manager);
    }
    public CloseableHttpClient cc() {
        CloseableHttpClient httpClient = clientBuilder.setDefaultRequestConfig(this.getRequestConfig()).build();
        return httpClient;
    }

    public RequestConfig getRequestConfig() {
        RequestConfig.Builder custom = RequestConfig.custom();
        custom.setConnectTimeout(4000);// 连接超时毫秒
        custom.setConnectionRequestTimeout(3000);// 获取请求超时毫秒
        custom.setSocketTimeout(10000);// 传输超时毫秒
        return custom.build();
    }


    public HttpRequestRetryHandler getHttpRequestRetryHandler(){
        return new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
    }

    public void dd() {

    }
}
