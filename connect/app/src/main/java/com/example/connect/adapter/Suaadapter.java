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
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;


public class Suaadapter extends RecyclerView.Adapter<Suaadapter.MyViewHoler> {
    Context context;
    List<spmoi> array;

    public Suaadapter(Context context, List<spmoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sua,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        MyViewHoler myViewHoler = (MyViewHoler)  holder;
        spmoi sp = array.get(position);
        holder.tensp.setText(sp.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá:"+sp.getGia()+"Đ");
        holder.mota.setText(sp.getMota());
        Glide.with(context).load(sp.getHinhanh()).into(holder.hinhanh);
        myViewHoler.setItemclick(new Itemclick() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent(context, ChitietActivity.class);
                    intent.putExtra("chitiet",sp);
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

    public class MyViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tensp, giasp, mota;
        ImageView hinhanh;
        private Itemclick itemclick;
        public MyViewHoler(@NonNull View itemView){
            super(itemView);
            tensp=itemView.findViewById(R.id.item_suaten);
            giasp=itemView.findViewById(R.id.item_suagia);
            mota=itemView.findViewById(R.id.item_suamota);
            hinhanh=itemView.findViewById(R.id.item_suaanh);
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
