package com.example.connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.connect.R;
import com.example.connect.model.loaisp;
import com.bumptech.glide.Glide;
import java.time.Instant;
import java.util.List;

public class loaispadapter extends BaseAdapter {
    List<loaisp> array;
    Context context;

    public loaispadapter( Context context,List<loaisp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView texttensp;
        ImageView imghinhanh;
    }
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder viewHolder = null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sp, null);
            viewHolder.texttensp = view.findViewById(R.id.tensp);
            viewHolder.imghinhanh = view.findViewById(R.id.item_sp);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.texttensp.setText(array.get(i).getTênsp());
        Glide.with(context).load(array.get(i).getHìnhảnh()).into(viewHolder.imghinhanh);
        return view;
    }


}
