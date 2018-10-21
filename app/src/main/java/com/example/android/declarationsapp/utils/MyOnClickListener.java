package com.example.android.declarationsapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.declarationsapp.R;
import com.example.android.declarationsapp.data.Person;

public class MyOnClickListener implements View.OnClickListener {
    private Context context;
    private Person person;
    private String link, comment;

    /**
     * Constructor, if you click the add to favorites button
     */
    public MyOnClickListener(Context context, Person person) {
        this.context = context;
        this.person = person;
    }

    /**
     * Constructor, if you click the download PDF button
     */
    public MyOnClickListener(Context context, String link) {
        this.context = context;
        this.link = link;
    }

    /**
     * AlertDialog for adding comment to the declaration
     */
    public static AlertDialog.Builder getAlertDialogBuilder(Context context) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(itemView)
                .setPositiveButton("Додати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setNeutralButton("Відміна", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder;
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            /**Opening PDF file by parse link or showing the toast that PDF not found */
            case R.id.btn_link:
                if (link != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "PDF файл не знайдено", Toast.LENGTH_SHORT).show();
                }
                break;
            /**Adding declaration to the favorite list with comment*/
            case R.id.btn_star:
                final AlertDialog alertDialog = getAlertDialogBuilder(context).create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText etComment = (EditText) alertDialog.findViewById(R.id.et_comment);
                        comment = etComment.getText().toString();
                        CreatingList.putData(person, comment);
                        alertDialog.cancel();
                    }
                });
                break;
        }
    }
}
