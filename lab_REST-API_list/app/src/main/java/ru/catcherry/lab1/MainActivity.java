package ru.catcherry.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    List<FactDetail> list;
    ApiInterface api;
    private CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new ListAdapter(this, list);
        recyclerView = findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        api = ApiConfiguration.getApi();
        disposables = new CompositeDisposable();
        this.onClick(this.recyclerView);
    }

    public void onClick(View view){
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        disposables.add(api.facts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((facts) -> {

                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                    findViewById(R.id.list).setVisibility(View.VISIBLE);
                    list.clear();
                    list.addAll(facts.all);
                    adapter.notifyDataSetChanged();
                }, (error) -> {
                    Toast.makeText(this, "При поиске возникла ошибка:\n"+error.getMessage(),
                        Toast.LENGTH_LONG).show();
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                    findViewById(R.id.list).setVisibility(View.VISIBLE);

                }));
    }
}
