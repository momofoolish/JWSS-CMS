package com.jwss.cms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * JwssSpider Jwss爬虫框架
 *
 * @author Jwss(锦威叔叔)
 * @version V0.0.1
 * @Description 描述类的功能
 * @CreateDate 2019-11-21
 **/
public abstract class JwssSpider implements Runnable {
    public void run() {
        startSpider();
    }

    private static String spiderUrl;
    private static String hk;
    private static String hv;

    /**
     * @see SpiderConfig 爬虫配置类
     */
    public static class SpiderConfig {
        /**
         * 添加一个爬虫地址
         *
         * @param url 请求地址
         */
        public void addUrl(String url) {
            spiderUrl = url;
        }

        /**
         * 添加请求头
         */
        public void addHeader(String k, String v) {
            hk = k;
            hv = v;
        }
    }

    /**
     * 启动爬虫
     */
    private void startSpider() {
        configSpider(new SpiderConfig());
        getRequest(spiderUrl);
    }

    /**
     * 请求页面成功后返回调用
     *
     * @param page Page类
     */
    public abstract void getData(Page page) throws JsonProcessingException;

    /**
     * 配置爬虫规则
     */
    public abstract void configSpider(SpiderConfig config);

    /**
     * 得到一个请求
     *
     * @param url 请求地址
     */
    private void getRequest(String url) {
        // 1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 2.创建Request对象，设置一个url地址,设置请求方式。
        Request request = new Request.Builder().url(url).addHeader(hk, hv).
                method("GET", null).build();
        // 3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        // 4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            // 请求失败执行的方法
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("jwss--错误代码：" + call.hashCode());
                System.out.println("jwss--错误内容：" + call.toString());
                e.printStackTrace();
            }

            // 请求成功执行的方法
            public void onResponse(@NotNull Call call, @NotNull Response response) throws JsonProcessingException {
                System.out.println("请求状态码:" + response.code());
                getData(new Page(response));
            }
        });
    }

    /**
     * 接收请求成功返回的数据
     */
    public static class Page {
        public Page(Response resp) {
            this.resp = resp;
        }

        private final Response resp;

        /**
         * 获取HTML页面数据
         */
        public String getHtml() {
            try {
                return Objects.requireNonNull(resp.body()).string();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        /**
         * 返回字节流
         */
        public InputStream getMedia() {
            return Objects.requireNonNull(resp.body()).byteStream();
        }

        /**
         * 用于保存指定路径的文件
         *
         * @param is   文件流
         * @param path 文件路径
         */
        public void saveMedia(InputStream is, String path) {
            FileOutputStream outputStream = null;
            File file = new File(path);
            try {
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    assert outputStream != null;
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
