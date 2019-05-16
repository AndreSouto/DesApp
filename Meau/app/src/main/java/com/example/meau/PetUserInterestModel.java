package com.example.meau;

import java.io.Serializable;

public class PetUserInterestModel implements Serializable {
    private String uid, petUid, userUid;

    public PetUserInterestModel() {}

    public PetUserInterestModel(String uid, String petUid, String userUid) {
        this.uid = uid;
        this.petUid = petUid;
        this.userUid = userUid;
    }

    public PetUserInterestModel(String petUid, String userUid) {
        this.petUid = petUid;
        this.userUid = userUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPetUid() {
        return petUid;
    }

    public void setPetUid(String petUid) {
        this.petUid = petUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
