package com.example.connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.connect.R;
import com.example.connect.model.Donhang;

import java.util.List;


public class Donhangadapter extends RecyclerView.Adapter<Donhangadapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<Donhang> listdonhang;

    public Donhangadapter(Context context, List<Donhang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Donhang donhang = listdonhang.get(position);
        holder.txtdonhang.setText("Đơn hàng:"+donhang.getId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rechitiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(donhang.getItem().size());
        Chitietadapter chitietadapter = new Chitietadapter(context,donhang.getItem());
        holder.rechitiet.setLayoutManager(layoutManager);
        holder.rechitiet.setAdapter(chitietadapter);
        holder.rechitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtdonhang;
        RecyclerView rechitiet;
        public MyViewHolder (@NonNull View itemview){
            super(itemview);
            txtdonhang = itemview.findViewById(R.id.iddonhang);
            rechitiet = itemview.findViewById(R.id.recycleview_chitiet);
        }
    }
}
