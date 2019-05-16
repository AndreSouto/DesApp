package com.example.meau;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PetDatabaseHelper {

    // Cria o animal no banco
    public static void createPet(Animal animal, OnSuccessListener successListener) {
        DatabaseReference reference = DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PETS);

        animal.setUid(reference.push().getKey());
        reference.child(animal.getUid()).setValue(animal.toMap()).addOnSuccessListener(successListener);
    }

    // Atualiza os dados do animal
    public static void updatePet(Animal animal, OnSuccessListener successListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PETS).child(animal.getUid()).setValue(animal.toMap()).addOnSuccessListener(successListener);
    }

    // Obtém dados do animal com Uid específica
    public static void getPetWithUid(String uid, ValueEventListener eventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PETS).child(uid).addListenerForSingleValueEvent(eventListener);
    }

    // Obtém os animais do usuário com Uid específico
    public static void getPetWithUserUid(String uid, ValueEventListener eventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PETS).orderByChild("userUid").equalTo(uid).addListenerForSingleValueEvent(eventListener);
    }

    // Obtém todos os animais
    public static void getAllPets(ValueEventListener eventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PETS).addListenerForSingleValueEvent(eventListener);
    }
}
