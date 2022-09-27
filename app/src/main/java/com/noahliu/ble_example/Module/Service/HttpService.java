package com.noahliu.ble_example.Module.Service;

import android.app.Service;
import android.util.Log;

import com.noahliu.ble_example.Module.Enitiy.ScannedData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class HttpService  {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public static void post(ScannedData scannedData) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        /**設置傳送所需夾帶的內容*/
        FormBody formBody = new FormBody.Builder()
                .add("name", scannedData.getDeviceName())
                .add("id", scannedData.getAddress())
                .add("rssi", scannedData.getRssi())
                .build();
        /**設置傳送需求*/
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .post(formBody)
                .build();
        /**設置回傳*/
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                /**如果傳送過程有發生錯誤*/
                Log.d("error",e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                /**取得回傳*/
                Log.d("POST回傳：\n", response.body().string());
            }
        });
    }
    }

