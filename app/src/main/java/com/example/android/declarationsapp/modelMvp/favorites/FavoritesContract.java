package com.example.android.declarationsapp.modelMvp.favorites;

import com.example.android.declarationsapp.data.Person;

import java.util.List;

class FavoritesContract {
    interface Model {
        void getData(OnFinishedGet onFinishedGet);

        interface OnFinishedGet {
            void onReturnData(List<Person> personList, List<String> commentList);
        }
    }

    interface View {
        void setDataToAdapter(List<Person> personList, List<String> commentList);
    }

    interface Presenter {
        void getDataList();

        void onDestroy();
    }
}
