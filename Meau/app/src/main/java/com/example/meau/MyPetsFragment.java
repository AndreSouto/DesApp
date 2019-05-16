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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyPetsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyPetsFragmentAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Animal> mList;

    public static MyPetsFragment newInstance() { return new MyPetsFragment(); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_pets, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ((InterActivity)getActivity()).setTitleToolbar(R.string.my_pets);

        mList = new ArrayList<>();

        // Cria o RecyclerView
        recyclerView = getView().findViewById(R.id.my_pets_fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyPetsFragmentAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        PetDatabaseHelper.getPetWithUserUid(UserHelper.getUserModel(getContext()).getUid(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mList.add(snapshot.getValue(Animal.class));
                }
                mAdapter.setList(mList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
