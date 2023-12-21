package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect.R;
import com.example.connect.model.Usermodel;
import com.example.connect.retrofit.Apibanhang;
import com.example.connect.retrofit.retrofitclient;
import com.example.connect.utils.utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ThanhtoanActivity extends AppCompatActivity {
    TextView textView,tongtien;
    EditText ten,sdt,diachi;
    AppCompatButton btnthanhtoan;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Apibanhang apibanhang;
    long tong;
    int tongtiensl;
    //Usermodel usermodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        initview();
        count();
        initcontrol();
    }
    private void count(){
        tongtiensl = 0;
        for (int i = 0; i<utils.manggiohang.size();i++){
            tongtiensl = tongtiensl+ (utils.manggiohang.get(i).getSoluong());
        }
    }

    private void initcontrol() {
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // finish();
////                Intent dangky = new Intent(getApplicationContext(), DangkyActivity.class);
////                startActivity(dangky);
//            }
//        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
         tong = getIntent().getLongExtra("tongtien",0);
        tongtien.setText(decimalFormat.format(tong)+".000Đ");

//        chucnangthanhtoan
        
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strdiachi = diachi.getText().toString().trim();
                String strpten= ten.getText().toString().trim();
                String strsdt= sdt.getText().toString().trim();
                if(TextUtils.isEmpty(strpten)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(strsdt)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(strdiachi)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ ", Toast.LENGTH_SHORT).show();
                }else {
                    //String stremail = utils.user_cr.getEmail();
                    //String str_sdt = utils.user_cr.getSdt();
                    int id = utils.gio.getId();
                    Log.d("test",new Gson().toJson(utils.manggiohang));

                    compositeDisposable.add(apibanhang.donhang(strpten,strsdt,String.valueOf(tong),id,
                                    strdiachi,tongtiensl,new Gson().toJson(utils.manggiohang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    usermodel->  {
                                            Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            finish();

                                    }

//                                    ,
//
//                                    throwable -> {
//                                        Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
//                                    }
                                    ));

              }
            }
        });
    }




    private void initview() {
        apibanhang = retrofitclient.getInstance(utils.BASE_URL).create(Apibanhang.class);

        textView = findViewById(R.id.txtthanhtoan);
        ten = findViewById(R.id.ten);
        sdt = findViewById(R.id.sdt);
        diachi = findViewById(R.id.diachi);
        tongtien = findViewById(R.id.txttongtien);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}