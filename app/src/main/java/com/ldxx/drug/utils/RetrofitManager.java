package com.ldxx.drug.utils;

import android.content.Context;

import com.ldxx.drug.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理类
 */
public class RetrofitManager {

    private OkHttpClient mOkHttpClient;

    private Retrofit retrofit;

    public RetrofitManager(Context context) {
        initOkHttpClient(context);

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_HOST)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initOkHttpClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(new Cache(context.getCacheDir(), 1024 * 200))
                            .addNetworkInterceptor(new CacheInterceptor())
                            .addInterceptor(interceptor)
                            .addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request()
                                            .newBuilder()
                                            .addHeader("Content-Type", "application/json; charset=UTF-8")
                                            //.addHeader("Accept-Encoding", "gzip, deflate")
                                            .addHeader("apikey", "d6e91c2b841ef37858964106aa83749c")
                                            .build();
                                    return chain.proceed(request);
                                }
                            })
                            .retryOnConnectionFailure(true)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
