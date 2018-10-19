package com.example.android.declarationsapp;

import android.app.Application;

import com.example.android.declarationsapp.retrofit.ApiData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;
    private ApiData apiData;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        String BASE_URL = "https://public-api.nazk.gov.ua/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiData = retrofit.create(ApiData.class);

    }

    public ApiData getApiData() {
        return apiData;
    }
}
