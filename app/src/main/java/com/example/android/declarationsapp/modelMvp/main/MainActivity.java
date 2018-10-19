package com.example.android.declarationsapp.modelMvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.declarationsapp.MyAdapter;
import com.example.android.declarationsapp.R;
import com.example.android.declarationsapp.data.Person;
import com.example.android.declarationsapp.modelMvp.favorites.FavoritesActivity;
import com.example.android.declarationsapp.utils.CheckingConnection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    static final String TAG = "MainActivity";
    @BindView(R.id.recycler_view_main)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_nothing_find)
    TextView tvNothingToFind;

    MainPresenter presenter;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new MainPresenter(this);

        if (!CheckingConnection.hasConnection(this)) {
            tvNothingToFind.setText(getString(R.string.no_connection));
            tvNothingToFind.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            presenter.getDataFromApi(query);
        }
    }

    public void onClickSearch(View view) {
        query = etSearch.getText().toString();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                query = editable.toString();
            }
        });
        etSearch.setHint(query);
        presenter.getCloneDataFromApi(query);
    }

    @Override
    public void setDataToAdapter(List<Person> personList) {
        if (personList == null) {
            tvNothingToFind.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNothingToFind.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            MyAdapter myAdapter = new MyAdapter(personList);
            recyclerView.setAdapter(myAdapter);
        }
    }

    @Override
    public void makeToastOnFailure(Throwable throwable) {
        Log.d(TAG, "onFailure: " + throwable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_favorites:
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
