package com.example.meau;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserDatabaseHelper {

    // Cria o usuário no banco
    public static void createUser(Usuario usuario, OnSuccessListener successListener) {
        DatabaseReference reference = DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.USERS);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(usuario.getUid(), usuario);

        reference.updateChildren(childUpdates).addOnSuccessListener(successListener);
    }

    // Atualiza os dados do usuário
    public static void updateUser(Usuario usuario, OnSuccessListener successListener) {
        createUser(usuario, successListener);
    }

    // Obtém dados do usuário com Uid específica
    public static void getUserWithUid(String uid, ValueEventListener eventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.USERS).child(uid).addListenerForSingleValueEvent(eventListener);
    }

    // Obtém dados do usuário com email específico
    public static void getUserWithEmail(String email, ValueEventListener eventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.USERS).orderByChild("email").equalTo(email).addListenerForSingleValueEvent(eventListener);
    }
}
