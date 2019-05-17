package com.example.meau;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meau.Animal;
import com.example.meau.ClickInterface;
import com.example.meau.DefaultPetViewHolder;

import java.util.ArrayList;

public class MyPetsFragmentAdapter extends RecyclerView.Adapter<DefaultPetViewHolder> {

    private ArrayList<Animal> mList;
    private Context mContext;

    public MyPetsFragmentAdapter(Context context) {
        super();
        mContext = context;
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DefaultPetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_default_pet, viewGroup, false);
        return new DefaultPetViewHolder(view, new ClickInterface() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(mContext, Sancho.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Animal.class.getName(), mList.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultPetViewHolder defaultPetViewHolder, int i) {
        Animal petModel = mList.get(i);

        defaultPetViewHolder.title.setText(petModel.getNome());
        defaultPetViewHolder.gender.setText(petModel.getSexo());
        defaultPetViewHolder.age.setText(petModel.getIdade());
        defaultPetViewHolder.postage.setText(petModel.getPorte());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<Animal> list) {
        mList = list;
        notifyDataSetChanged();
    }
}
