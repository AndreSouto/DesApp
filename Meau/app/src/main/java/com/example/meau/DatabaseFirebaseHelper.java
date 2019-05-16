package com.example.meau;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseFirebaseHelper {

    public static final String USERS = "users";
    public static final String PETS = "pets";
    public static final String PET_USER_INTEREST = "petUserInterest";
    private static FirebaseDatabase firebaseDatabase;

    public static FirebaseDatabase getInstance() {
        if (firebaseDatabase == null) firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase;
    }

    public static DatabaseReference getDatabaseReference(String reference) {
        switch (reference) {
            case USERS:
                getInstance().getReference(reference).keepSynced(true);
                break;
            case PETS:
                getInstance().getReference(reference).keepSynced(false);
                break;
            case PET_USER_INTEREST:
                getInstance().getReference(reference).keepSynced(true);
                break;
        }
        return getInstance().getReference(reference);
    }
}
