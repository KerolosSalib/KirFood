package com.kerolossalib.kirfood.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {



    private String imageUrl;
    private String name;
    private String address;
    private float minimumOrder;
    private ArrayList<Product> products;


    public Restaurant(String imageUrl, String name, String address, float minimumOrder) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.minimumOrder = minimumOrder;
        products = new ArrayList<>();

    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        name = jsonRestaurant.getString("name");
        address = jsonRestaurant.getString("address");
        minimumOrder = Float.valueOf(jsonRestaurant.getString("min_order"));
        imageUrl = jsonRestaurant.getString("image_url");
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }



    public String getAddress() {
        return address;
    }


    public float getMinimumOrder() {
        return minimumOrder;
    }


    public void setImageUrl(String s) {
        imageUrl = s;
    }



}
