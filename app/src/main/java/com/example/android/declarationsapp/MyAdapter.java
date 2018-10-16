package com.example.android.declarationsapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public MyAdapter(List<Person> personList){
        this.personList = personList;
    }

    public MyAdapter(List<Person> personList, List<String> commentList){
        this.personList = personList;
        this.commentList = commentList;
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
        Log.d("myLogs", "onBind" + person.getFirstname());

        String firstname = person.getFirstname();
        String lastname = person.getLastname();
        String placeOfWork = person.getPlaceOfWork();
        String positionPerson = person.getPosition();
        String link = person.getLinkPDF();

        holder.tvFirstName.setText(person.getFirstname());
        holder.tvLastName.setText(person.getLastname());
        holder.tvPlaceOfWork.setText(person.getPlaceOfWork());
        holder.tvPosition.setText(person.getPosition());

        if (commentList!=null){
            final String comment = commentList.get(position);
            holder.tvComment.setText(comment);
            holder.textComment.setVisibility(View.VISIBLE);
            holder.tvComment.setVisibility(View.VISIBLE);
        }
        holder.btnLinkPdf.setOnClickListener(new MyOnClickListener(holder.itemView.getContext(), link));

        holder.btnStar.setOnClickListener(new MyOnClickListener(holder.itemView.getContext(), firstname, lastname, placeOfWork, positionPerson));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
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
