package com.example.proodoo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<DataModel> dataset;
    public CustomAdapter(ArrayList<DataModel> dataModels )
    {this.dataset=dataModels;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=
                LayoutInflater .from(parent.getContext()).inflate(R.layout.ligne,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView textView=holder.nom;
        TextView textView1=holder.description;
        TextView textView2=holder.prix;
        ImageView imageView=holder.imageViewIcon;
        textView.setText(dataset.get(position).getName());
        textView1.setText(dataset.get(position).getDescription());
        textView2.setText(dataset.get(position).getPrix());
        imageView.setImageResource(dataset.get(position).getid());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
