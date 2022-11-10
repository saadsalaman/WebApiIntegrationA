package com.example.webapiintegration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context c;
    List<MyContact> ls;

    public MyAdapter(Context c, List<MyContact> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(ls.get(position).getName());
        holder.phno.setText(ls.get(position).getPhno());
        holder.address.setText(ls.get(position).getAddress());

        holder.roww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        c,
                        position+"",
                        Toast.LENGTH_LONG
                ).show();
                ls.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phno,address;
        LinearLayout roww;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roww=itemView.findViewById(R.id.roww);
            name=itemView.findViewById(R.id.name);
            phno=itemView.findViewById(R.id.phno);
            address=itemView.findViewById(R.id.address);
        }
    }
}
