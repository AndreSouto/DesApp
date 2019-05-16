package com.example.meau;

import com.example.meau.DatabaseFirebaseHelper;
import com.example.meau.PetUserInterestModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class InterestDatabaseHelper {

    // Cria um interesse no banco
    public static void createInterest(PetUserInterestModel interestModel, OnSuccessListener successListener) {
        DatabaseReference reference = DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PET_USER_INTEREST);

        interestModel.setUid(reference.push().getKey());
        reference.child(interestModel.getUid()).setValue(interestModel).addOnSuccessListener(successListener);
    }

    // Pega todos os uid usuário interessados no animal com um Uid específico
    public static void getAllUsersInterest(String petUid, ValueEventListener valueEventListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PET_USER_INTEREST).orderByChild("petUid").equalTo(petUid).addListenerForSingleValueEvent(valueEventListener);
    }

    // Apaga o interesse com Uid
    public static void deleteInterest(String uid, DatabaseReference.CompletionListener completionListener) {
        DatabaseFirebaseHelper.getDatabaseReference(DatabaseFirebaseHelper.PET_USER_INTEREST).child(uid).removeValue(completionListener);
    }
}
