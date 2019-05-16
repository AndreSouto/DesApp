package com.example.meau;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Serializable {
    @SerializedName("shortName")
    private String shortName = "";
    @SerializedName("fullName")
    private String fullName = "";
    @SerializedName("email")
    private String email = "";
    @SerializedName("state")
    private String state = "";
    @SerializedName("city")
    private String city = "";
    @SerializedName("address")
    private String address = "";
    @SerializedName("password")
    private String password = "";
    @SerializedName("phone")
    private String phone = "";
    @SerializedName("imageUrl")
    private String imageUrl = "";
    @SerializedName("uid")
    private String uid = "";
    @SerializedName("token")
    private String token = "";
    @SerializedName("age")
    private int age = 0;

    public Usuario(){
        // Construtor padrão necessário para usar objeto no Firebase
    }

    public Usuario(String json) {
        Usuario usuario = (Usuario) GsonUtil.deserialize(Usuario.class, json);
        setShortName(usuario.getShortName());
        setFullName(usuario.getFullName());
        setEmail(usuario.getEmail());
        setState(usuario.getState());
        setCity(usuario.getCity());
        setAddress(usuario.getAddress());
        setPassword(usuario.getPassword());
        setPhone(usuario.getPhone());
        setImageUrl(usuario.getImageUrl());
        setUid(usuario.getUid());
        setToken(usuario.getToken());
        setAge(usuario.getAge());
    }

    public Usuario(String shortName, String fullName, String email, String state, String city, String address, String username, String phone, String imageUrl, String hiid, int age) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.email = email;
        this.state = state;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.uid = hiid;
        this.age = age;
    }

    public Usuario(String shortName, String fullName, String email, String state, String city, String address, String username, String password, String phone, String imageUrl, String hiid, int age) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.email = email;
        this.state = state;
        this.city = city;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.uid = hiid;
        this.age = age;
    }

    public Usuario(String uid, String fullName, String email, String imageUrl) {
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.email = email;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("uid", getUid());
        map.put("shortName", getShortName());
        map.put("fullName", getFullName());
        map.put("email", getEmail());
        map.put("state", getState());
        map.put("city", getCity());
        map.put("address", getAddress());
        map.put("password", getPassword());
        map.put("phone", getPhone());
        map.put("imageUrl", getImageUrl());
        map.put("age", getAge());
        map.put("token", getToken());

        return map;
    }

    @Override
    public String toString() {
        return GsonUtil.serialize(this);
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String history) {
        this.uid = history;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
