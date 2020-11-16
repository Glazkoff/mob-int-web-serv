package ru.catcherry.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FactActivity extends AppCompatActivity {

    TextView factText;
    TextView factBody;
    ImageView factImageFull;
    ApiInterface api;
    private CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact);
        factText = findViewById(R.id.factText);
        factBody = findViewById(R.id.factBody);
        factImageFull = findViewById(R.id.factImageFull);
        api = ApiConfiguration.getApi();
        disposables = new CompositeDisposable();
        if (getIntent().getExtras() != null){
            Log.d("!!!", "onCreate: " + getIntent().getStringExtra("factid"));
            disposables.add(
                    api.fact(getIntent().getStringExtra("factid"))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    (fact) -> {
                                        factBody.setText(fact.text);
                                        int rand = (int) Math.ceil(Math.random()*1000);
                                        Glide.with(this).load("https://picsum.photos/id/"+rand+"/800/800").into(factImageFull);
                                    },
                                    (error) -> {
                                        error.printStackTrace();
                                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    }));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
