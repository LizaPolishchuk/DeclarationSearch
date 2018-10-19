package com.example.android.declarationsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.declarationsapp.data.Person;
import com.example.android.declarationsapp.utils.MyOnClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Person> personList;
    private List<String> commentList;
    private boolean itIsFavorites = false;

    public MyAdapter(List<Person> personList) {
        this.personList = personList;
    }

    public MyAdapter(List<Person> personList, List<String> commentList) {
        this.personList = personList;
        this.commentList = commentList;
        itIsFavorites = true;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Person person = personList.get(position);

        String link = person.getLinkPDF();

        /**Setting data to the item*/
        holder.tvFirstName.setText(person.getFirstname());
        holder.tvLastName.setText(person.getLastname());
        holder.tvPlaceOfWork.setText(person.getPlaceOfWork());
        holder.tvPosition.setText(person.getPosition());

        if (commentList != null) {
            final String comment = commentList.get(position);
            holder.tvComment.setText(comment);
            holder.textComment.setVisibility(View.VISIBLE);
            holder.tvComment.setVisibility(View.VISIBLE);
        }

        holder.btnLinkPdf.setOnClickListener(new MyOnClickListener(holder.itemView.getContext(), link));

        holder.btnStar.setOnClickListener(new MyOnClickListener(holder.itemView.getContext(), person));

        /**If it is the favorite list hide button add to favorite*/
        if (itIsFavorites) {
            holder.btnStar.setVisibility(View.GONE);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    /**Deleting item from the favorite list*/
                    final AlertDialog alertDialog = MyOnClickListener.getAlertDialogBuilder(holder.itemView.getContext()).create();
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Видалити", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            personList.remove(holder.getAdapterPosition());
                            commentList.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                    });
                    alertDialog.show();
                    /**Changing the comment in the item*/
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EditText etComment = (EditText) alertDialog.findViewById(R.id.et_comment);
                            String comment = etComment.getText().toString();
                            holder.tvComment.setText(comment);
                            alertDialog.cancel();
                        }
                    });
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_firstname)
        TextView tvFirstName;
        @BindView(R.id.tv_lastname)
        TextView tvLastName;
        @BindView(R.id.tv_place_of_work)
        TextView tvPlaceOfWork;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.btn_link)
        ImageButton btnLinkPdf;
        @BindView(R.id.btn_star)
        ImageButton btnStar;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.text_comment)
        TextView textComment;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
