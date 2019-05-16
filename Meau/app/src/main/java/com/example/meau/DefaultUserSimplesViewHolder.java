package com.example.meau;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.meau.R;
import com.example.meau.ClickInterface;

import de.hdodenhof.circleimageview.CircleImageView;

public class DefaultUserSimplesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView name, age;
    public CircleImageView image;
    public ClickInterface clickInterface;

    public DefaultUserSimplesViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.civ_user_name);
        age = itemView.findViewById(R.id.civ_user_age);
        image = itemView.findViewById(R.id.civ_user_image);
    }

    public DefaultUserSimplesViewHolder(@NonNull View itemView, ClickInterface clickInterface) {
        super(itemView);
        name = itemView.findViewById(R.id.civ_user_name);
        age = itemView.findViewById(R.id.civ_user_age);
        image = itemView.findViewById(R.id.civ_user_image);
        itemView.setOnClickListener(this);
        this.clickInterface = clickInterface;
    }

    @Override
    public void onClick(View view) {
        clickInterface.onClick(view, getAdapterPosition());
    }
}
