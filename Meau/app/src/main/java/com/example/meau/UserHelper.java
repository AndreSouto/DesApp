package com.example.meau;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.meau.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserHelper {
    private static Usuario userModel;

//    public static Usuario getUserModel(final Context context) {
//        if (userModel != null && FirebaseAuth.getInstance().getCurrentUser() != null) {
//            UserDatabaseHelper.getUserWithUid(FirebaseAuth.getInstance().getCurrentUser().getUid(), new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.getValue() != null)
//                        setUserModel(context, dataSnapshot.getValue(UserModel.class));
//                    else
//                        setUserModel(context, new UserModel());
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    setUserModel(context, new UserModel());
//                }
//            });
//        }
//        else {
//            userModel = new UserModel(context.getSharedPreferences(UserModel.class.getName(), Context.MODE_PRIVATE).getString(UserModel.class.getName(), (new UserModel()).toString()));
//        }
//        return userModel;
//    }

//    public static void setUserModel(Context context, UserModel userModel) {
//        UserHelper.userModel = userModel;
//
//        SharedPreferences sharedPref = context.getSharedPreferences(UserModel.class.getName(), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(UserModel.class.getName(), userModel.toString());
//        editor.apply();
//
//        if (userModel.getUid() != null && !userModel.getUid().isEmpty())
//            UserDatabaseHelper.updateUser(userModel, null);
//    }
}
