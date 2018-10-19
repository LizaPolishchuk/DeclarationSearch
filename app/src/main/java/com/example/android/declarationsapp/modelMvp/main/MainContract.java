package com.example.android.declarationsapp.modelMvp.main;

import com.example.android.declarationsapp.data.Person;

import java.util.List;

public class MainContract {

    interface Model {
        void getPersonList(onFinishedGettingData onFinishedGettingData, String query);

        void getClonePersonList(onFinishedGettingData onFinishedGettingData, String query);

        interface onFinishedGettingData {
            void onFinishedSuccess(List<Person> personList);

            void onFailure(Throwable throwable);
        }
    }

    interface View {
        void setDataToAdapter(List<Person> personList);

        void makeToastOnFailure(Throwable throwable);
    }

    interface Presenter {
        void getDataFromApi(String query);

        void getCloneDataFromApi(String query);

        void onDestroy();
    }
}
