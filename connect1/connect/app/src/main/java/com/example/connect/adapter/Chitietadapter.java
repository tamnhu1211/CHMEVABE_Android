package com.example.connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connect.R;
import com.example.connect.model.Item;

import java.text.DecimalFormat;
import java.util.List;

public class Chitietadapter extends RecyclerView.Adapter<Chitietadapter.MyViewHolder>{
    Context context;
    List<Item> itemList;

    public Chitietadapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.txtten.setText(item.getTensp()+"");
        holder.txtsl.setText("số lượng:"+item.getSoluong()+"");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
         long gia = item.getSoluong() * item.getGiasp();
        holder.txtgia.setText("Giá:"+decimalFormat.format(gia)+".000Đ");
        //holder.txtgia.setText("Giá:"+item.getGiasp()+".000Đ");

        Glide.with(context).load(item.getHinhsp()).into(holder.imgchitiet);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchitiet;
        TextView txtten,txtsl,txtgia;
        public MyViewHolder (@NonNull View itemview){
            super(itemview);
            txtten = itemview.findViewById(R.id.item_tenchitiet);
            txtsl = itemview.findViewById(R.id.item_slchitiet);
            imgchitiet = itemview.findViewById(R.id.item_imgchitiet);
            txtgia = itemview.findViewById(R.id.item_giachitiet);

        }
    }
}
