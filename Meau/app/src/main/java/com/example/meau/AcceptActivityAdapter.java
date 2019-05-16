package com.example.meau;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcceptActivityAdapter extends RecyclerView.Adapter<DefaultUserSimplesViewHolder> {

    private ArrayList<Usuario> mList;
    private Activity mActivity;
    private Animal mAnimal;

    public AcceptActivityAdapter(Activity activity, Animal animal) {
        super();
        mActivity = activity;
        mList = new ArrayList<>();
        mAnimal = animal;
    }

    @NonNull
    @Override
    public DefaultUserSimplesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_user_simple, viewGroup, false);
        return new DefaultUserSimplesViewHolder(view, new ClickInterface() {
            @Override
            public void onClick(View view, final int position) {
                new AlertDialog.Builder(mActivity)
                        .setTitle("Confirmar adoção?")
                        .setMessage("Você realmente deseja confirmar esta adoção?")
                        .setPositiveButton(R.string.yess, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAnimal.setUserUid(mList.get(position).getUid());
                                mAnimal.setAvailable(false);
                                PetDatabaseHelper.updatePet(mAnimal, new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        // Envia a notificação
                                        Map<String, Object> data = new HashMap<>();
                                        data.put("type", "confirm");
                                        data.put("pet", mAnimal.getName());
                                        data.put("petUid", mAnimal.getUid());
                                        data.put("user", UserHelper.getUserModel(mActivity).getShortName());
                                        data.put("token", mList.get(position).getToken());
                                        FirebaseFunctions.getInstance()
                                                .getHttpsCallable("sendNotification")
                                                .call(data);

                                        InterestDatabaseHelper.getAllUsersInterest(mAnimal.getUid(), new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.getValue() == null) return;

                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    PetUserInterestModel petUserInterestModel = snapshot.getValue(PetUserInterestModel.class);
                                                    InterestDatabaseHelper.deleteInterest(petUserInterestModel.getUid(), null);
                                                }

                                                Toast.makeText(mActivity, "Adoção realizada com sucesso!", Toast.LENGTH_SHORT).show();
                                                mActivity.setResult(Activity.RESULT_OK);
                                                mActivity.finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });
                            }
                        })
                        .setNegativeButton(R.string.Noo, null)
                        .show();
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultUserSimplesViewHolder defaultUserSimplesViewHolder, int i) {
        Usuario usuario = mList.get(i);

        defaultUserSimplesViewHolder.name.setText(usuario.getShortName());
        defaultUserSimplesViewHolder.age.setText(String.format("%s Anos", String.valueOf(usuario.getAge())));
        Glide.with(mActivity).load(usuario.getImageUrl()).into(defaultUserSimplesViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(ArrayList<Usuario> list) {
        mList = list;
        notifyDataSetChanged();
    }
}
