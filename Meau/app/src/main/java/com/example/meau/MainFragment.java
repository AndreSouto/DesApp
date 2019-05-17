package com.example.meau;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meau.MainFragmentAdapter;
import com.example.meau.PetDatabaseHelper;
import com.example.meau.UserHelper;
import com.example.meau.MainActivity;
import com.example.meau.Animal;
import com.example.meau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainFragmentAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Animal> mList;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mList = new ArrayList<>();

        // Cria o RecyclerView
        recyclerView = getView().findViewById(R.id.main_fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MainFragmentAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        PetDatabaseHelper.getAllPets(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) return;

                String uid, uuid;
                Animal petModel;

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                uid = currentFirebaseUser.getUid();

                mList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    petModel = snapshot.getValue(Animal.class);
                    uuid = petModel.getUid();

                    mList.add(petModel);
                }
                mAdapter.setList(mList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}