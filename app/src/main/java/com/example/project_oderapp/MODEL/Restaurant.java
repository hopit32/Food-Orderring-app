package com.example.project_oderapp.MODEL;

import java.io.Serializable;

public class Restaurant  implements Serializable {
    String about;
    String address;
    String distance;
    String id;
    String image;
    String name;
    String rate;

    public Restaurant(String about, String address, String distance, String id, String image, String name, String rate) {
        this.about = about;
        this.address = address;
        this.distance = distance;
        this.id = id;
        this.image = image;
        this.name = name;
        this.rate = rate;
    }
    public Restaurant(){}

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
