package com.ldxx.drug.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 在服务器不支持缓存时，用的拦截器
 * Created by liaodongxiaoxiao
 * on 2016/9/2.
 */

class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //cache for 7 days
                .header("Cache-Control", "max-age=" + 3600 * 24 * 7)
                .build();
    }
}