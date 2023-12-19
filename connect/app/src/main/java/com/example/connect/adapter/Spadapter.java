package com.example.connect.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.connect.Interface.Itemclick;
import com.example.connect.activity.ChitietActivity;
import com.example.connect.model.spmoi;
import com.example.connect.R;

import java.text.DecimalFormat;
import java.util.List;

public class Spadapter extends RecyclerView.Adapter<Spadapter.MyViewHolder> {
    Context context;
    List<spmoi> array;

    public Spadapter(Context context, List<spmoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spmoi,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        spmoi Spmoi = array.get(position);
        holder.txtten.setText(Spmoi.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá:"+Spmoi.getGia()+"Đ");
        //holder.txtgia.setText("Giá:"+decimalFormat.format(Double.parseDouble(Spmoi.getGia()))+"Đ");
        Glide.with(context).load(Spmoi.getHinhanh()).into(holder.imghinhanh);
        holder.setItemclick(new Itemclick() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent(context, ChitietActivity.class);
                    intent.putExtra("chitiet",Spmoi);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtgia, txtten;
        ImageView imghinhanh;
        private Itemclick itemclick;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            txtgia = itemView.findViewById(R.id.itemsp_gia);
            txtten = itemView.findViewById(R.id.itemsp_ten);
            imghinhanh = itemView.findViewById(R.id.itemsp_img);
            itemView.setOnClickListener(this);
        }

        public void setItemclick(Itemclick itemclick) {
            this.itemclick = itemclick;
        }

        @Override
        public void onClick(View v) {
            itemclick.onClick(v, getAdapterPosition(),false);
        }
    }
}
