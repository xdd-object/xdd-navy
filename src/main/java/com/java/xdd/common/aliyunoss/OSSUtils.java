package com.java.xdd.common.aliyunoss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.DefaultCredentials;
import org.apache.log4j.PropertyConfigurator;

public class OSSUtils {
    private final static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private final static String accessKeyId = "LTAIw5o7mxB4e7Ts";
    private final static String accessKeySecret = "Ll5CB4E4Fw3oVt9E2o2nSRrsWIhkV6";
    private final static String bucketName = "xdd-navy-oss";
    private static String firstKey = "name.suffix";

    public OSSClient getOSSClient(){
        PropertyConfigurator.configure("classpath:log4j.properties");
        ClientConfiguration clientConfig = new ClientConfiguration();

        OSSClient defalutOssClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, clientConfig);

        Credentials credentials = new DefaultCredentials(accessKeyId, accessKeySecret);
        CredentialsProvider provider = new DefaultCredentialProvider(credentials);

        OSSClient ossClient = new OSSClient(endpoint, provider, clientConfig);


        return ossClient;
    }
}
