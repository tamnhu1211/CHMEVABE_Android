package com.example.connect.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connect.Interface.Itemclick;
import com.example.connect.activity.ChitietActivity;
import com.example.connect.activity.GiohangActivity;
import com.example.connect.model.Giohang;
import com.example.connect.R;
import com.example.connect.model.Giohang;

import java.text.DecimalFormat;
import java.util.List;

public class Giohangadapter extends RecyclerView.Adapter<Giohangadapter.MyViewHolder> {
    Context context;
    List<Giohang> giohangList;
    Giohang giohang;

    public Giohangadapter(Context context, List<Giohang> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Giohang giohang = giohangList.get(position);
        holder.item_giohangtensp.setText(giohang.getTensp());
        holder.item_giohangsl.setText(giohang.getSoluong()+"");
        Glide.with(context).load(giohang.getHinhsp()).into(holder.item_giohangimg);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohanggia.setText("Giá:"+giohang.getGiasp()+"Đ");
        long gia = giohang.getSoluong() * giohang.getGiasp();
        holder.item_giohanggia2.setText(decimalFormat.format(gia));
//        myViewHolder.itemView(new Itemclick() {
//            @Override
//            public void onClick(View view, int pos, boolean isLongClick) {
//                if(!isLongClick){
//                    Intent intent = new Intent(context, GiohangActivity.class);
//                    intent.putExtra("giohang",);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    //implements View.OnClickListener
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView item_giohangimg;
        TextView item_giohangtensp,item_giohanggia,item_giohanggia2,item_giohangsl;

        private Itemclick itemclick;
        public MyViewHolder(@NonNull View itemView){

            super(itemView);
            item_giohangimg = itemView.findViewById(R.id.item_giohangimg);
            item_giohangtensp = itemView.findViewById(R.id.item_giohangtensp);
            item_giohanggia= itemView.findViewById(R.id.item_giohanggia);
            //spinner_giohang = itemView.findViewById(R.id.spinner_giohang);
            item_giohanggia2 = itemView.findViewById(R.id.item_giohanggia2);
            item_giohangsl = itemView.findViewById(R.id.item_giohangsl);
            //itemView.setOnClickListener(this);

        }
//        public void setItemclick(Itemclick itemclick) {
//            this.itemclick = itemclick;
//        }
//
//        @Override
//        public void onClick(View v) {
//            itemclick.onClick(v, getAdapterPosition(),false);
//        }
    }
}
