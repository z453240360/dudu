package com.dodo.marcket.http.retrofit;

import android.content.Context;


import com.dodo.marcket.http.apiService.Api;
import com.dodo.marcket.http.apiService.ApiService;
import com.dodo.marcket.http.convert.JsonConverterFactory;
import com.dodo.marcket.http.utils.OkClientProvider;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * author：fmd on 16/8/22 17
 * use:
 */
public class RetroUtils {

    private volatile static RetroUtils retroUtils;

    private Retrofit encryptRetrofit;

    private ApiService apiService;

    public static Context APP;

    public static void init(Context context) {
        APP = context;
    }



    private static RetroUtils getInstance() {

        if (retroUtils == null) {
            synchronized (RetroUtils.class) {
                if (retroUtils == null) {
                    retroUtils = new RetroUtils();
                }
            }
        }

        return retroUtils;
    }

    public static ApiService getApi() {
        return getInstance().apiService;
    }

    public static <T> T getApi(Class<T> clazz) {
        return getInstance().encryptRetrofit.create(clazz);
    }

    private RetroUtils() {
        /**
         * 通用加密请求配置
         */
        encryptRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BaseUrl)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkClientProvider.create())
                .build();

        apiService = encryptRetrofit.create(ApiService.class);
    }

}
