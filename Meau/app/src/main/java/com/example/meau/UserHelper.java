package com.example.meau;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserHelper {
    private static Usuario usuario;

    public static Usuario getUserModel(final Context context) {
        if (usuario != null && FirebaseAuth.getInstance().getCurrentUser() != null) {
                UserDatabaseHelper.getUserWithUid(FirebaseAuth.getInstance().getCurrentUser().getUid(), new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null)
                            setUserModel(context, dataSnapshot.getValue(Usuario.class));
                        else
                            setUserModel(context, new Usuario());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        setUserModel(context, new Usuario());
                    }
                });
        }
        else {
            usuario = new Usuario(context.getSharedPreferences(Usuario.class.getName(), Context.MODE_PRIVATE).getString(Usuario.class.getName(), (new Usuario()).toString()));
        }
        return usuario;
    }

    public static void setUserModel(Context context, Usuario usuario) {
        UserHelper.usuario = usuario;

        SharedPreferences sharedPref = context.getSharedPreferences(Usuario.class.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Usuario.class.getName(), usuario.toString());
        editor.apply();

        if (usuario.getUid() != null && !usuario.getUid().isEmpty())
            UserDatabaseHelper.updateUser(usuario, null);
    }
}
