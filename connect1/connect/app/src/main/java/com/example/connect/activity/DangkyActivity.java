package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect.R;
import com.example.connect.adapter.Suaadapter;
import com.example.connect.model.Usermodel;
import com.example.connect.retrofit.Apibanhang;
import com.example.connect.retrofit.retrofitclient;
import com.example.connect.utils.utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangkyActivity extends AppCompatActivity {
    EditText email,pass,ten,sdt;
    Usermodel usermodel;
    AppCompatButton button;
    Apibanhang apibanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        initview();
        initcontrol();
    }

    private void initcontrol() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky();
            }
        });
    }
private void dangky(){
        String stremail= email.getText().toString().trim();
        String strpass= pass.getText().toString().trim();
        String strsdt= sdt.getText().toString().trim();
        String strten= ten.getText().toString().trim();
        if(TextUtils.isEmpty(stremail)){
                Toast.makeText(getApplicationContext(),"Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
    } else if(TextUtils.isEmpty(strpass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(strsdt)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập sdt", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(strten)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập ten", Toast.LENGTH_SHORT).show();
        }
//        else {
//            if (strpass.equals(strsdt)){
//                Toast.makeText(getApplicationContext(), "pass và sdt phai khác nhau", Toast.LENGTH_SHORT).show();
//            }
//            else {
                compositeDisposable.add(apibanhang.dangky(stremail,strpass,strten,strsdt)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                Usermodel ->  {
                                    if(Usermodel.isSuccess()){
                                        utils.user_cr.setEmail(stremail);
                                        utils.user_cr.setPass(strpass);
                                        Intent intent = new Intent(getApplicationContext(),DangnhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(getApplicationContext(), usermodel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
//                                ,
//                                throwable -> {
//                                    Toast.makeText(getApplicationContext(),throwable.getMessage() ,Toast.LENGTH_LONG).show();
//                                }
                        ));
//            }
//        }
}
    private void initview() {
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.btndangky);
        sdt = findViewById(R.id.sdt);
        ten = findViewById(R.id.ten);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}