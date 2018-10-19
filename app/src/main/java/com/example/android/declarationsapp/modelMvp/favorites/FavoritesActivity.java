package com.example.android.declarationsapp.modelMvp.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.declarationsapp.MyAdapter;
import com.example.android.declarationsapp.R;
import com.example.android.declarationsapp.data.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity implements FavoritesContract.View {

    FavoritesContract.Presenter presenter;
    @BindView(R.id.recycler_view_favorites)
    RecyclerView recyclerView;
    @BindView(R.id.tv_favorite_list_is_empty)
    TextView tvEmptyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new FavoritesPresenter(this);
        presenter.getDataList();
    }

    @Override
    public void setDataToAdapter(List<Person> personList, List<String> commentList) {
        if (personList.size() == 0) {
            tvEmptyList.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            MyAdapter adapter = new MyAdapter(personList, commentList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
