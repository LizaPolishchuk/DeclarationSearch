package com.example.android.declarationsapp.modelMvp.favorites;

import android.content.Intent;

import com.example.android.declarationsapp.data.Person;

import java.util.List;

 class FavoritesContract {
    interface Model{
        interface OnFinishedGet{
            void onReturnData(List<Person> personList, List<String> commentList);
        }
        void getData(OnFinishedGet onFinishedGet);
    }
    interface View{
        void setDataToAdapter(List<Person> personList, List<String> commentList);
    }
    interface Presenter{
        void getDataList();
        void onDestroy();
    }
}
