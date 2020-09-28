package com.android.retrofittest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static OkHttpClient okHttpClient;
    public static Retrofit retrofit;

    public static OkHttpClient getOkHttpClientInstance(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            if (okHttpClient == null){
                getOkHttpClientInstance();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(Contants.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return  retrofit;
    }
}
