package com.example.meau;

public class Usuario {

    private static long id = 0;
    private String age;
    private String name;
    private String state;
    private String address;
    private String email;
    private String phoneNumber;
    private String city;
    private String nameusu;
    private String password;

    public Usuario(){}

    public String getAddress() {
        return this.address;
    }

    public String getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public static long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCity() {
        return this.city;
    }

    public String getNameUsu() {
        return this.nameusu;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNameUsu(String nameusu) {
        this.nameusu = nameusu;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
}
