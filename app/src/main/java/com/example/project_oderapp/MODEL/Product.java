package com.example.project_oderapp.MODEL;

import java.io.Serializable;

public class Product implements Serializable {
public String description;
public String id;
public String idcategory;
public String image;
public String location;
public String name;
public String price;
public String rate;
public String timedeliver;
public String calories;
public String restaurant;
public int numberinCart;

    public Product(int numberinCart,String restaurant,String calories, String description, String id, String idcategory, String image, String location, String name, String price, String rate, String timedeliver) {
        this.description = description;
        this.id = id;
        this.idcategory = idcategory;
        this.image = image;
        this.location = location;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.timedeliver = timedeliver;
        this.calories = calories;
        this.restaurant = restaurant;
        this.numberinCart = numberinCart;
    }
    public Product()
    {}

    public Product(String restaurant,String calories, String description, String id, String idcategory, String image, String location, String name, String price, String rate, String timedeliver) {
        this.description = description;
        this.id = id;
        this.idcategory = idcategory;
        this.image = image;
        this.location = location;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.timedeliver = timedeliver;
        this.calories = calories;
        this.restaurant = restaurant;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTimedeliver() {
        return timedeliver;
    }

    public void setTimedeliver(String timedeliver) {
        this.timedeliver = timedeliver;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
