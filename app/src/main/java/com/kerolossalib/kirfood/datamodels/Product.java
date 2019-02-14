package com.kerolossalib.kirfood.datamodels;

public class Product {

    private String name;
    private int quantity = 0;
    private float price;
    private String imageUrl;


    public Product(String name, float price, String imageUrl){

        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Product(String name, float price, int quantity){

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, float price){

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void increaseQuantity(){
        this.quantity++;
    }
    public void decreaseQuantity(){
        if(quantity == 0) return;
        this.quantity--;
    }

    public float getSubtotal(){
        return quantity * price;
    }

}