package com.example.meau;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meau.R;
import com.example.meau.ClickInterface;

public class DefaultPetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title, gender, age, postage, city;
    public ImageView image;
    public ClickInterface clickInterface;

    public DefaultPetViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.view_holder_pet_title);
        gender = itemView.findViewById(R.id.view_holder_pet_gender);
        age = itemView.findViewById(R.id.view_holder_pet_age);
        postage = itemView.findViewById(R.id.view_holder_pet_postage);
        city = itemView.findViewById(R.id.view_holder_pet_city);
        image = itemView.findViewById(R.id.view_holder_pet_image);
    }

    public DefaultPetViewHolder(@NonNull View itemView, ClickInterface clickInterface) {
        super(itemView);
        title = itemView.findViewById(R.id.view_holder_pet_title);
        gender = itemView.findViewById(R.id.view_holder_pet_gender);
        age = itemView.findViewById(R.id.view_holder_pet_age);
        postage = itemView.findViewById(R.id.view_holder_pet_postage);
        city = itemView.findViewById(R.id.view_holder_pet_city);
        image = itemView.findViewById(R.id.view_holder_pet_image);
        itemView.setOnClickListener(this);
        this.clickInterface = clickInterface;
    }

    @Override
    public void onClick(View view) {
        clickInterface.onClick(view, getAdapterPosition());
    }
}
