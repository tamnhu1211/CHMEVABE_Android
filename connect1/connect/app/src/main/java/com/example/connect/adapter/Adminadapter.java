package com.example.connect.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.connect.R;
import com.example.connect.model.Admin;
import com.example.connect.model.spmoi;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


public class Adminadapter extends RecyclerView.Adapter<Adminadapter.AdminViewHolder>implements Filterable {
    private List<Admin> list;
    private List<Admin> lists;
    List<spmoi> mangspmoi;



    public Adminadapter(List<Admin> list) {
        this.list = list;
        this.lists = list;

    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sua,parent,false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        Admin admin = list.get(position);
        if(admin == null){
            return;
        }
        holder.tvten.setText(admin.getTen());
    }

    @Override
    public int getItemCount() {
        if(list !=null){
            return list.size();
        }
        return 0;
    }




    public class AdminViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private TextView tvten;
        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ll);
            tvten = itemView.findViewById(R.id.item_suaten);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                if(str.isEmpty()){

                }else {
                    mangspmoi = new ArrayList<>();
                    for (Admin admin : lists){

                    }
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }
}
