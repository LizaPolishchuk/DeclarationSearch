package com.example.android.declarationsapp.modelMvp.favorites;

import android.arch.persistence.room.Room;
import android.util.Log;

import com.example.android.declarationsapp.App;
import com.example.android.declarationsapp.data.Person;

import com.example.android.declarationsapp.utils.AddDataToList;

import java.util.List;

public class FavoritesModel implements FavoritesContract.Model {

    @Override
    public void getData(OnFinishedGet onFinishedGet) {
        List<Person> personList = AddDataToList.getPersonList();
        List<String> commentList = AddDataToList.getCommentList();

        onFinishedGet.onReturnData(personList, commentList);
    }
}

