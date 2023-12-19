package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class BimActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Apibanhang apibanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 0;
    int loai;
    Suaadapter spadapter;
    List<spmoi> sanphamMoiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bim);
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);
        loai = getIntent().getIntExtra("loai",1);
        Anhxa();
        ActionToolBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apibanhang.getSp(page, loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spmoimodel ->  {
                            if(spmoimodel.isSuccess()){
                                sanphamMoiList = spmoimodel.getResult();
                                spadapter = new Suaadapter(getApplicationContext(),sanphamMoiList);
                                recyclerView.setAdapter(spadapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"khong ket noi duoc",Toast.LENGTH_LONG).show();
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
    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleview_sua);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sanphamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}