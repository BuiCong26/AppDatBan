package com.example.appdatban.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatban.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMonAn extends RecyclerView.Adapter<AdapterMonAn.MyHolder> {
    private ArrayList<MonAn> arrayList;
    private Context context;
    private  ClickListener clickListener;

    public AdapterMonAn (Context context, ArrayList<MonAn> arrayList, ClickListener clickListener)
    {
        this.context = context;
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monan, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MonAn monan = arrayList.get(position);
        holder.txtTenMonAn.setText(monan.getTenMonAn());
        holder.txtMotaMonAn.setText(monan.getMotaMonAn());
        Picasso.with(context).load(monan.getPhoto()).into(holder.imgMonAn);


    }

    @Override
    public int getItemCount() {
        if (arrayList != null && arrayList.size() > 0)
            return arrayList.size();
        else
            return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView txtTenMonAn,txtMotaMonAn;
        private ImageView imgMonAn;
        public MyHolder(@NonNull View itemview) {
            super(itemview);
            txtTenMonAn = itemview.findViewById(R.id.txtTenMonAn);
            txtMotaMonAn = itemview.findViewById(R.id.txtMotaMonAn);
            imgMonAn = itemview.findViewById(R.id.imgMonAN);

            itemview.setOnClickListener(v -> {
                clickListener.onItemClick(getAdapterPosition(),v);
            });


        }
    }
    public interface ClickListener{
        void onItemClick(int position, View v);

    }

}
