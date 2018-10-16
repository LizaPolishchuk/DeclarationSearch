package com.example.android.declarationsapp.utils;


import com.example.android.declarationsapp.data.Person;
import java.util.ArrayList;
import java.util.List;

public class AddDataToList {

    private static List<Person> personList = new ArrayList<>();
    private static List<String> commentList = new ArrayList<>();

    public static void putData(String firstname, String lastname, String placeOfWork, String position, String comment){
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setPlaceOfWork(placeOfWork);
        person.setPosition(position);

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
