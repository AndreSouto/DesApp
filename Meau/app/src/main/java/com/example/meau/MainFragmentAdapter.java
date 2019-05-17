package com.example.meau;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meau.R;
import com.example.meau.ClickInterface;
import com.example.meau.DefaultPetViewHolder;
import com.example.meau.Animal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragmentAdapter extends RecyclerView.Adapter<DefaultPetViewHolder> {
    private ArrayList<Animal> mList;
    private Context mContext;

    public MainFragmentAdapter(Context context) {
        super();
        mContext = context;
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DefaultPetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_default_pet, viewGroup, false);
        return new DefaultPetViewHolder(view, new ClickInterface() {
            @Override
            public void onClick(View view, int position) {

                final String uid;

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                uid = currentFirebaseUser.getUid();

                final Animal obj = mList.get(position);
                obj.setUid(uid);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference databaseReference = database.getReference();
                databaseReference.child("Animals").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        for (DataSnapshot child : children){
                            Animal anm = child.getValue(Animal.class);

                            if (anm.getNome().equals(obj.getNome())){
                                DatabaseReference dr = child.getRef();
                                dr.child("uid").setValue(uid);
                                dr.child("available").setValue(false);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(mContext, IntroActivity.class);
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
        defaultPetViewHolder.image.setImageResource(R.drawable.alec);
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
