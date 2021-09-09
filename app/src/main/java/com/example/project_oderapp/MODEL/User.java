package com.example.project_oderapp.MODEL;

public class User {
    public String user_id;
    public String user_username;
    public String user_email;
    public String user_numberphone;
    public String user_address;
    public String user_image;
    public String user_password;
    public String user_address_getfood;

    public User(String user_id, String user_username, String user_email, String user_numberphone, String user_address, String user_image, String user_password, String user_address_getfood) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_email = user_email;
        this.user_numberphone = user_numberphone;
        this.user_address = user_address;
        this.user_image = user_image;
        this.user_password = user_password;
        this.user_address_getfood = user_address_getfood;

    }
public User()
{}


    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_address_getfood() {
        return user_address_getfood;
    }

    public void setUser_address_getfood(String user_address_getfood) {
        this.user_address_getfood = user_address_getfood;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_numberphone() {
        return user_numberphone;
    }

    public void setUser_numberphone(String user_numberphone) {
        this.user_numberphone = user_numberphone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
