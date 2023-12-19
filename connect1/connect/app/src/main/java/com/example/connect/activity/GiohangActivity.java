package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.connect.utils.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.connect.R;
import com.example.connect.adapter.Giohangadapter;
import com.example.connect.model.Giohang;
import com.example.connect.utils.utils;

import java.text.DecimalFormat;
import java.util.List;

public class GiohangActivity extends AppCompatActivity {
    TextView giohangtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;
    Giohangadapter adapter;
    List<Giohang>manggiohang;
    long tongtiensp ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        initView();
        initControl();
        Tongtien();

    }

    private void Tongtien() {
        tongtiensp = 0;
        for (int i = 0; i<utils.manggiohang.size();i++){
            tongtiensp = tongtiensp+ (utils.manggiohang.get(i).getGiasp()*utils.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtiensp)+".000Đ");
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(utils.manggiohang.size() == 0){
            giohangtrong.setVisibility(View.VISIBLE);

        }else {
            adapter = new Giohangadapter(getApplicationContext(),utils.manggiohang);
            recyclerView.setAdapter(adapter);
            //giohangtrong.setVisibility(View.VISIBLE);
        }
        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent thanhtoan = new Intent(getApplicationContext(),ThanhtoanActivity.class);
                thanhtoan.putExtra("tongtien",tongtiensp);
                startActivity(thanhtoan);
            }
        });
    }

    private void initView() {
        giohangtrong = findViewById(R.id.txtgoihangtrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleviewgiohang);
        btnmuahang = findViewById(R.id.btnmuahang);


    }
}