package com.hp.springboot.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doGet(String url, Map<String, String> param) {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("处理http请求异常，{}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            setParams(param, httpPost);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            LOGGER.error("处理http请求异常，{}", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    private static void setParams(Map<String, String> param, HttpPost httpPost) throws UnsupportedEncodingException {
        if (param != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (String key : param.keySet()) {
                paramList.add(new BasicNameValuePair(key, param.get(key)));
            }
            // 模拟表单
            httpPost.setEntity(new UrlEncodedFormEntity(paramList));
        }
    }

    public static String doPostWithHeaders(String url, Map<String, String> param, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);

            if (headers != null) {
                for (String header : headers.keySet()) {
                    httpPost.setHeader(header, headers.get(header));
                }
            }

            setParams(param, httpPost);
            response = httpClient.execute(httpPost);

            //获取响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                resultString = EntityUtils.toString(entity, "UTF-8");
                LOGGER.info("[HttpClient]请求成功,请求返回内容为:{}", resultString);
            } else {
                LOGGER.info("[HttpClient]请求失败,错误码为:{}", statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String uploadPic(String url, File file) throws FileNotFoundException {
        HttpPost httppost = new HttpPost(url);
        // 创建二进制文件流
        InputStream inputStream = new FileInputStream(file);
        // 创建 part contentBody,第一个是值，第二个参数是类型，第三个参数是编码格式
        StringBody stringBody = new StringBody("一种二进制文件", ContentType.create("text/plain", Consts.UTF_8));
        FileBody fileBody = new FileBody(file);
        // 创建MultipartEntityBuilder对象
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 添加二进制文件流，第一个参数是接口参数名，第二个参数是文件的二进制流，第三个参数是文件名
        //builder.addBinaryBody("file", inputStream, ContentType.create("multipart/form-data"), "11.png");
        // 添加参数和参数值
        builder.addPart("file", fileBody).addPart("comment", stringBody);
        // 创建 httpentity 实体
        HttpEntity httpEntity = builder.build();
        // 设置 httpentity 实体
        httppost.setEntity(httpEntity);
        // 发送请求获取相应
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httppost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultString;
    }

}
