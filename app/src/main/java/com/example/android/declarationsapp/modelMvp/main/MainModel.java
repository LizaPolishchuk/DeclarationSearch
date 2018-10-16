package com.example.android.declarationsapp.modelMvp.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.declarationsapp.App;
import com.example.android.declarationsapp.data.Items;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainContract.Model {

    private Callback<Items> myCallback;

    private void initCallback(final onFinishedGettingData onFinishedGettingData){
        myCallback = new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, @NonNull Response<Items> response) {
                if (response.body()==null){
                    Log.d("myLogs", "personList = null");
                } else {
                    onFinishedGettingData.onFinishedSuccess(response.body().getItems());
            }
        }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                onFinishedGettingData.onFailure(t);
            }
        };
    }

    @Override
    public void getPersonList(final onFinishedGettingData onFinishedGettingData, String query) {
        Call<Items> itemsCall = App.getInstance().getApiData().itemList(query);

        initCallback(onFinishedGettingData);
        itemsCall.enqueue(myCallback);
    }

    @Override
    public void getClonePersonList(onFinishedGettingData onFinishedGettingData, String query) {
        Call<Items> itemsCall = App.getInstance().getApiData().itemList(query);

        initCallback(onFinishedGettingData);
        itemsCall.clone().enqueue(myCallback);
    }
}
