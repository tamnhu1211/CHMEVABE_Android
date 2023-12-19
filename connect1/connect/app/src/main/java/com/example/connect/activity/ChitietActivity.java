package com.example.connect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect.utils.utils;


import com.bumptech.glide.Glide;
import com.example.connect.model.Giohang;
import com.example.connect.model.spmoi;
//import androidx.constraintlayout.widget.R;

import com.example.connect.R;
import com.example.connect.utils.utils;

import java.text.DecimalFormat;
import java.util.List;

public class ChitietActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    spmoi sp;
    int tongtiensp;

    List<Giohang>manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        initView();
        initData();
        ActionToolBar();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themgoihang();


            }
        });
    }

    private void themgoihang() {
        if(utils.manggiohang.size()>0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i<utils.manggiohang.size();i++){
                if(utils.manggiohang.get(i).getId() == sp.getId()){
                    utils.manggiohang.get(i).setSoluong(soluong+utils.manggiohang.get(i).getSoluong());
                    long gia = Long.parseLong(sp.getGia())* utils.manggiohang.get(i).getSoluong();
                    utils.manggiohang.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if(flag == false){
                long gia = Long.parseLong(sp.getGia())* soluong;
                Giohang giohang = new Giohang();
                giohang.setGiasp(gia);
                giohang.setSoluong(soluong);
                giohang.setId(sp.getId());
                giohang.setTensp(sp.getTensp());
                giohang.setHinhsp(sp.getHinhanh());
                utils.manggiohang.add(giohang);
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(trangchu);

            }
        }else {

            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(sp.getGia())* soluong;
            Giohang giohang = new Giohang();
            giohang.setGiasp(Long.parseLong(sp.getGia()));
            giohang.setSoluong(soluong);
            giohang.setId(sp.getId());
            giohang.setTensp(sp.getTensp());
            giohang.setHinhsp(sp.getHinhanh());
            utils.manggiohang.add(giohang);
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(trangchu);

        }
        if(utils.manggiohang !=null) {
            int tongtiensp = 0;
            for (int i = 0; i < utils.manggiohang.size(); i++) {
                tongtiensp = tongtiensp + utils.manggiohang.get(i).getSoluong();
            }
        }
    }

    private void initData() {
         sp = (spmoi) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sp.getTensp());
        mota.setText(sp.getMota());
        Glide.with(getApplicationContext()).load(sp.getHinhanh()).into(imghinhanh);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá:"+sp.getGia()+".000Đ");
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterp = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterp);
    }

    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmota);
        btnthem = findViewById(R.id.btnthêm);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spinner);
         RecyclerView recyclerView =findViewById(R.id.recycleviewgiohang);

//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent giohang = new Intent(getApplicationContext(),GiohangActivity.class);
//                startActivity(giohang);
//            }
//        });

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



}