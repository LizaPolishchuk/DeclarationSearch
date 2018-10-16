package com.example.android.declarationsapp.retrofit;

import com.example.android.declarationsapp.data.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiData {
    @GET("declaration/")
    Call<Items> itemList(@Query("q") String query);
}
