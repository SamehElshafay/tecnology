package com.example.projectb.customerview;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String username;
    private String name;
    private String product;
    private String password;
    private String phone;
    private String photo;
    private String location;
    private String state;
    private String fav;

    public User() {
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getFav() {
        return fav;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    public String getLocation() {
        return location;
    }

    public String getState() {
        return state;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public User(String username, String name, String password, String phone, String photo, String location, String state, String product,String fav) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.photo = photo;
        this.location = location;
        this.state = state;
        this.product = product;
        this.fav = fav;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setState(String state) {
        this.state = state;
    }
}