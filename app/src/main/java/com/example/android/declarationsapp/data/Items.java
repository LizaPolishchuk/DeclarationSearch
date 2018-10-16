package com.example.android.declarationsapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {
    @SerializedName("items")
    private List<Person> items;

    public List<Person> getItems() {
        return items;
    }

    public void setItems(List<Person> items) {
        this.items = items;
    }
}
