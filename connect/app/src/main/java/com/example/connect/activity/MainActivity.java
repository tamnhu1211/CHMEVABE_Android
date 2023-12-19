package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.connect.R;
import com.example.connect.adapter.Spadapter;


import com.example.connect.retrofit.Apibanhang;
import com.example.connect.retrofit.retrofitclient;
import com.example.connect.utils.utils;
import com.google.android.material.navigation.NavigationView;
import com.example.connect.adapter.loaispadapter;
import com.example.connect.model.loaisp;
import com.example.connect.model.Loaispmodel;
import com.example.connect.model.spmoi;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;



import io.reactivex.rxjava3.core.Observable;
import com.google.gson.stream.JsonReader;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmaninhchinh;

    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    CompositeDisposable compositeDisposable =new CompositeDisposable();
    Apibanhang apibanhang;
    loaispadapter loaispadapter;
    List<loaisp> mangloaisp;
    Loaispmodel loaispmodel;
    List<spmoi> mangspmoi;
    Spadapter spadapter;


Observable observable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);
        AnhXa();
        if(isConnected(this)){



            getloaisp();
            getSpmoi();
            geteventclick();
        }else {
            Toast.makeText(getApplicationContext(), "ko co internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void geteventclick() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent sua = new Intent(getApplicationContext(),SuaActivity.class);
                        sua.putExtra("loai",1);
                        startActivity(sua);
                        break;
                    case 2:
                        Intent bim = new Intent(getApplicationContext(),BimActivity.class);
                        bim.putExtra("loai",2);
                        startActivity(bim);
                        break;
                    case 3:
                        Intent be = new Intent(getApplicationContext(),BeActivity.class);
                        be.putExtra("loai",3);
                        startActivity(be);
                        break;
                    case 4:
                        Intent me = new Intent(getApplicationContext(),MeActivity.class);
                        me.putExtra("loai",4);
                        startActivity(me);
                        break;
                    case 5:
                    Intent giohang = new Intent(getApplicationContext(),GiohangActivity.class);
                    startActivity(giohang);
                    break;
                    case 6:
                        Intent lienhe = new Intent(getApplicationContext(),LienheActivity.class);
                        startActivity(lienhe);
                        break;
                }
            }
        });




    }

    private void getSpmoi() {
        compositeDisposable.add(apibanhang.getspMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spmoimodel -> {
                            if(spmoimodel.isSuccess()){
                                mangspmoi = spmoimodel.getResult();
                                spadapter = new Spadapter(getApplicationContext(),mangspmoi);
                                recyclerViewmaninhchinh.setAdapter(spadapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"khong ket noi duoc"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }
    private void getloaisp() {
        compositeDisposable.add(apibanhang.getloaisanpham()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaispmodel -> {
                            if (loaispmodel != null && loaispmodel.isSuccess()) {
                                //Toast.makeText(getApplicationContext(), loaispmodel.getResult().get(0).getTênsp(),Toast.LENGTH_LONG).show();
                                mangloaisp = loaispmodel.getResult();
                                loaispadapter = new loaispadapter(getApplicationContext(), mangloaisp);
                                listViewmanhinhchinh.setAdapter(loaispadapter);
                            }
                        }

                ));
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewmaninhchinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewmaninhchinh.setLayoutManager(layoutManager);
        recyclerViewmaninhchinh.setHasFixedSize(true);
        navigationView = findViewById(R.id.nav);
       listViewmanhinhchinh = findViewById(R.id.lv);
       mangloaisp = new ArrayList<>();
        mangspmoi = new ArrayList<>();
        if(utils.manggiohang== null){
            utils.manggiohang = new ArrayList<>();
        }
    }
    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi !=null&& wifi.isConnected()||(mobile !=null && mobile.isConnected())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}