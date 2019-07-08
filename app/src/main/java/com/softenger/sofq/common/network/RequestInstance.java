package com.softenger.sofq.common.network;

import com.softenger.sofq.api.URLS;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestInstance {

    private static Retrofit retrofit;
    private static Retrofit debugRetrofit;

    //wp-json/wp/v2/posts

    public static Retrofit getRetrofit() {
        if (null == retrofit) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100,TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder().baseUrl(URLS.BASE_URL + URLS.NODEJS_PORT).client(client)
                    //.addConverterFactory(ScalarsConverterFactory.create()).
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
