package com.example.android.declarationsapp.modelMvp.favorites;

import com.example.android.declarationsapp.data.Person;
import com.example.android.declarationsapp.utils.CreatingList;

import java.util.List;

public class FavoritesModel implements FavoritesContract.Model {

    @Override
    public void getData(OnFinishedGet onFinishedGet) {
        List<Person> personList = CreatingList.getPersonList();
        List<String> commentList = CreatingList.getCommentList();

        onFinishedGet.onReturnData(personList, commentList);
    }
}

