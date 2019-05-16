package com.example.meau;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Animal implements Serializable {
    private String userUid, name, gender, age, postage, city, imageUrl, disease, temperament, requiriments, about, uid;
    private boolean castrated, dewormed, vaccinated, available;

    public Animal() {
        // Construtor padrão necessário para usar objeto no Firebase
    }

    public Animal(String name, String gender, String age, String postage, String city, String imageUrl, String disease, String temperament, String requiriments, String about, boolean castrated, boolean dewormed, boolean vaccinated) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.postage = postage;
        this.city = city;
        this.imageUrl = imageUrl;
        this.disease = disease;
        this.temperament = temperament;
        this.requiriments = requiriments;
        this.about = about;
        this.castrated = castrated;
        this.dewormed = dewormed;
        this.vaccinated = vaccinated;
    }

    public Animal(String name, String gender, String age, String postage, String city, String imageUrl, String uid) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.postage = postage;
        this.city = city;
        this.imageUrl = imageUrl;
        this.uid = uid;
    }

    public Animal(String userUid, String name, String gender, String age, String postage, String city, String imageUrl, String disease, String temperament, String requiriments, String about, boolean castrated, boolean dewormed, boolean vaccinated) {
        this.userUid = userUid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.postage = postage;
        this.city = city;
        this.imageUrl = imageUrl;
        this.disease = disease;
        this.temperament = temperament;
        this.requiriments = requiriments;
        this.about = about;
        this.uid = uid;
        this.castrated = castrated;
        this.dewormed = dewormed;
        this.vaccinated = vaccinated;
    }

    public Animal(String userUid, String name, String gender, String age, String postage, String city, String imageUrl, String disease, String temperament, String requiriments, String about, boolean castrated, boolean dewormed, boolean vaccinated, boolean available) {
        this.userUid = userUid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.postage = postage;
        this.city = city;
        this.imageUrl = imageUrl;
        this.disease = disease;
        this.temperament = temperament;
        this.requiriments = requiriments;
        this.about = about;
        this.castrated = castrated;
        this.dewormed = dewormed;
        this.vaccinated = vaccinated;
        this.available = available;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("uid", getUid());
        map.put("name", getName());
        map.put("gender", getGender());
        map.put("age", getAge());
        map.put("postage", getPostage());
        map.put("city", getCity());
        map.put("imageUrl", getImageUrl());
        map.put("disease", getDisease());
        map.put("temperament", getTemperament());
        map.put("requiriments", getRequiriments());
        map.put("about", getAbout());
        map.put("castrated", isCastrated());
        map.put("dewormed", isDewormed());
        map.put("vaccinated", isVaccinated());
        map.put("userUid", getUserUid());
        map.put("available", isAvailable());

        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getRequiriments() {
        return requiriments;
    }

    public void setRequiriments(String requiriments) {
        this.requiriments = requiriments;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public void setCastrated(boolean castrated) {
        this.castrated = castrated;
    }

    public boolean isDewormed() {
        return dewormed;
    }

    public void setDewormed(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
