package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.connect.R;
import com.example.connect.adapter.Suaadapter;
import com.example.connect.model.spmoi;
import com.example.connect.retrofit.Apibanhang;
import com.example.connect.retrofit.retrofitclient;
import com.example.connect.utils.utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class timkiemActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Apibanhang apibanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Suaadapter spadapter;
    List<spmoi> sanphamMoiList;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
        initView();
        ActionToolBar();
    }

    private void initView() {
        sanphamMoiList = new ArrayList<>();
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);
        editText = findViewById(R.id.edtsearch);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleview_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    sanphamMoiList.clear();
                    spadapter = new Suaadapter(getApplicationContext(),sanphamMoiList);
                    recyclerView.setAdapter(spadapter);
                }else {


                    getDataSearch(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });
    }

    private void getDataSearch(String ss) {
        sanphamMoiList.clear();
        //String str_search = editText.getText().toString().trim();
        compositeDisposable.add(apibanhang.search(ss)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spmoimodel -> {
                            if(spmoimodel.isSuccess()){
                                sanphamMoiList = spmoimodel.getResult();
                                spadapter = new Suaadapter(getApplicationContext(),sanphamMoiList);
                                recyclerView.setAdapter(spadapter);
                            }
                        }
                ));
    }

    private  void  ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //nút back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}