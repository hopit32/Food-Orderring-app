package com.example.project_oderapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill  implements Serializable {

    private String address_Shipping;


    private String name;


    private String iduser;

    private String payment_method;

    private String total;

    private String date;

    private String note;

    private String price_deliver;

    private ArrayList<Product> products;

    private String code;

    private String subtotal;

    public Bill(String address_Shipping,String name,String code,String iduser, String payment_method, String total, String date, String note, String price_deliver, ArrayList<Product> products, String subtotal) {
        this.iduser = iduser;
        this.payment_method = payment_method;
        this.total = total;
        this.date = date;
        this.note = note;
        this.price_deliver = price_deliver;
        this.products = products;
        this.code = code;
        this.name = name;
        this.address_Shipping = address_Shipping;
        this.subtotal =subtotal;
    }
    public Bill()
    {}


    public String getAddress_Shipping() {
        return address_Shipping;
    }

    public void setAddress_Shipping(String address_Shipping) {
        this.address_Shipping = address_Shipping;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice_deliver() {
        return price_deliver;
    }

    public void setPrice_deliver(String price_deliver) {
        this.price_deliver = price_deliver;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
