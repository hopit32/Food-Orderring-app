package com.example.project_oderapp.MODEL;

public class BillInfo {
    public String namefood;
    public String count;
    public String price;
    public String image;

    public BillInfo(String namefood, String count, String price, String image) {
        this.namefood = namefood;
        this.count = count;
        this.price = price;
        this.image = image;
    }

    public String getNamefood() {
        return namefood;
    }

    public void setNamefood(String namefood) {
        this.namefood = namefood;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
