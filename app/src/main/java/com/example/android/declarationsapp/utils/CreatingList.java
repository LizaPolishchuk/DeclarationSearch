package com.example.android.declarationsapp.utils;


import android.widget.Toast;

import com.example.android.declarationsapp.App;
import com.example.android.declarationsapp.data.Person;

import java.util.ArrayList;
import java.util.List;

public class CreatingList {

    private static List<Person> personList = new ArrayList<>();
    private static List<String> commentList = new ArrayList<>();

    public static void putData(Person person, String comment) {
        /**If this declaration is already in the favorites don't add it*/
        for (Person pers : personList) {
            if (pers.getFirstname().equals(person.getFirstname()) &&
                    pers.getLastname().equals(person.getLastname())
                    && pers.getPlaceOfWork().equals(person.getPlaceOfWork())) {
                Toast.makeText(App.getInstance().getApplicationContext(), "Дана декларація вже додана до обраного", Toast.LENGTH_LONG).show();
                return;
            }
        }
        personList.add(person);
        commentList.add(comment);
    }

    public static List<Person> getPersonList() {
        return personList;
    }

    public static List<String> getCommentList() {
        return commentList;
    }
}
