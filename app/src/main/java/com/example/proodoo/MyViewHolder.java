package com.example.proodoo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView nom;
    TextView description;
    TextView prix;

    ImageView imageViewIcon;
    public MyViewHolder(View itemView) {
        super(itemView);
        this.nom=itemView.findViewById(R.id.nomproduit);
        this.description=itemView.findViewById(R.id.desproduit);
        this.prix=itemView.findViewById(R.id.prixproduit);
        this.imageViewIcon=itemView.findViewById(R.id.img);
    }
}

