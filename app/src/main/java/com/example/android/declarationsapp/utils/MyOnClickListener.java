package com.example.android.declarationsapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.android.declarationsapp.R;

public class MyOnClickListener implements View.OnClickListener {
    private Context context;
    private String firstname,  lastname,  placeOfWork,  position;
    private String link, comment;

    public MyOnClickListener(Context context, String firstname, String lastname, String placeOfWork, String position){
        this.context = context;
        this.firstname = firstname;
        this.lastname = lastname;
        this.placeOfWork = placeOfWork;
        this.position = position;
    }
    public MyOnClickListener(Context context, String link){
        this.context = context;
        this.link = link;
    }
    @Override
    public void onClick(final View view) {
        switch (view.getId()){
            case R.id.btn_link:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(intent);
                break;
            case R.id.btn_star:
                final View itemView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null, false);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setView(itemView)
                        .setPositiveButton("Додати", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText etComment = (EditText) itemView.findViewById(R.id.et_comment);
                                comment = etComment.getText().toString();
                                AddDataToList.putData(firstname, lastname, placeOfWork, position, comment);
                            }
                        })
                        .setNegativeButton("Відміна", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.create().show();
                break;
        }
    }
}
