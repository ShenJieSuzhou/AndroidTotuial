package com.buchuan.picturegallary.network;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiService {
    private OkHttpClient client;

    public ApiService() {
        client = HttpClient.getInstance();
    }

    // 封装 GET 请求
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    // 封装 POST 请求
    public String post(String url, RequestBody formBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    // 异步 GET 请求
    public void getAsync(String url, final ApiCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().string());
                } else {
                    callback.onFailure(new IOException("Unexpected code " + response));
                }
            }
        });
    }

    // 定义回调接口
    public interface ApiCallback {
        void onSuccess(String response);
        void onFailure(IOException e);
    }

}
