package com.example.android.declarationsapp.modelMvp.main;

import com.example.android.declarationsapp.data.Person;

import java.util.List;

public class MainPresenter implements MainContract.Presenter, MainContract.Model.onFinishedGettingData {

    private MainContract.Model model;
    private MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
        model = new MainModel();
    }

    @Override
    public void getDataFromApi(String query) {
        model.getPersonList(this, query);
    }

    @Override
    public void getCloneDataFromApi(String query) {
        model.getClonePersonList(this, query);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onFinishedSuccess(List<Person> personList) {
        if (view != null) {
            view.setDataToAdapter(personList);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (view != null) {
            view.makeToastOnFailure(throwable);
        }
    }
}
