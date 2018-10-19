package com.example.android.declarationsapp.modelMvp.favorites;

import com.example.android.declarationsapp.data.Person;

import java.util.List;

public class FavoritesPresenter implements FavoritesContract.Presenter, FavoritesContract.Model.OnFinishedGet {

    private FavoritesContract.Model model;
    private FavoritesContract.View view;

    FavoritesPresenter(FavoritesActivity view) {
        this.view = view;
        model = new FavoritesModel();
    }

    @Override
    public void getDataList() {
        model.getData(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onReturnData(List<Person> personList, List<String> commentList) {
        if (view != null) {
            view.setDataToAdapter(personList, commentList);
        }
    }
}
