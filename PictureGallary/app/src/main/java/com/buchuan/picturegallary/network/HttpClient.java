package com.buchuan.picturegallary.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClient {
    private static OkHttpClient instance;

    // 单例模式
    public static OkHttpClient getInstance() {
        if(instance == null)
        {
            synchronized (HttpClient.class) {
                if(instance == null) {
                    // 添加日志拦截器（可选，用于调试）
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //初始化
                    instance = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }

        return instance;
    }
}
