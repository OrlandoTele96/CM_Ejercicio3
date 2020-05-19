package com.example.cm_ejercicio3.model;

public class Product {//This class is a product

    int id;
    String name,thumnail_url,price,provider,delivery;

    public Product(int id, String name, String thumnail_url, String price, String provider, String delivery) {
        this.id = id;
        this.name = name;
        this.thumnail_url = thumnail_url;
        this.price = price;
        this.provider = provider;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumnail_url() {
        return thumnail_url;
    }

    public void setThumnail_url(String thumnail_url) {
        this.thumnail_url = thumnail_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
