package com.aqinga.yuekaolianxi;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/2019:28
 */

public class OkHttpUtils {
    public static void sendOkHttpRequest(String address, Callback callback){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(address)
                    .build();
            client.newCall(request).enqueue(callback);
        }

}
