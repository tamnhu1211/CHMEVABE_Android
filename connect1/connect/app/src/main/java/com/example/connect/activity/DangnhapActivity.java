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
import com.example.connect.model.Usermodel;

import com.example.connect.retrofit.Apibanhang;
import com.example.connect.retrofit.retrofitclient;
import com.example.connect.utils.utils;

import com.example.connect.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.disposables.Disposable;
public class DangnhapActivity extends AppCompatActivity {
    TextView txtdangky;
    EditText email, pass;
    AppCompatButton btndangnhap;
    Apibanhang apibanhang;
    CompositeDisposable compositeDisposable= new CompositeDisposable();
    Usermodel usermodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        initview();
        initcontrol();
    }
    private void initcontrol() {
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangky = new Intent(getApplicationContext(), DangkyActivity.class);
                startActivity(dangky);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString().trim();
                String strpass = pass.getText().toString().trim();
                if (TextUtils.isEmpty(stremail)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(strpass)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
                } else
                    {
                        compositeDisposable.add(apibanhang.dangnhap(stremail,strpass)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        Usermodel -> {
                                            if(Usermodel.isSuccess()){
                                                utils.user_cr = Usermodel.getResult().get(0);
                                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                                            }
                                        }
//                                        ,
//                                        throwable -> {
//                                            Toast.makeText(getApplicationContext(),throwable.getMessage() ,Toast.LENGTH_LONG).show();
//                                }
                                ));

                }

            }
        });
    }

    private void initview() {
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);

        txtdangky = findViewById(R.id.txtdangky);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btndangnhap = findViewById(R.id.btndangnhap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(utils.user_cr.getEmail() != null && utils.user_cr.getPass() !=null ){
            email.setText(utils.user_cr.getEmail());
            pass.setText(utils.user_cr.getPass());

        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}