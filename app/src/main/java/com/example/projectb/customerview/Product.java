package com.example.projectb.customerview;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    private String name;
    private String price;
    private String photo;
    private String key;
    private String description;
    private String storage;
    private String ram;
    private String rating;
    private String fav;
    private FavProduct fp;
    private String soldby;
    private String state;
    private String sale;
    private String type;
    String i;

    public Product(String name, String price, String photo, String description, String storage, String ram, String rating, String fav, String soldby) {
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = description;
        this.storage = storage;
        this.ram = ram;
        this.rating = rating;
        this.fav = fav;
        this.soldby = soldby;
    }

    public Product() {
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public void setSoldby(String soldby) {
        this.soldby = soldby;
    }

    public String getDescription() {
        return description;
    }

    public String getStorage() {
        return storage;
    }

    public String getRam() {
        return ram;
    }

    public String getRating() {
        return rating;
    }

    public String getFav() {
        return fav;
    }

    public String getSoldby() {
        return soldby;
    }

    public String getState() {
        return state;
    }

    public String getSale() {
        return sale;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
