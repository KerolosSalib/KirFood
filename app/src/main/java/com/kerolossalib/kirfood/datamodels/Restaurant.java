package com.kerolossalib.kirfood.datamodels;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    private String id;
    private String imageUrl;
    private String name;
    private String address;
    private float minimumOrder;

    public int getRating() {
        return rating;
    }

    private int rating;
    private ArrayList<Product> products;


    public Restaurant(String imageUrl, String name, String address, float minimumOrder) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.minimumOrder = minimumOrder;
        products = new ArrayList<>();

    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        this.name = jsonRestaurant.getString("name");
        this.address = jsonRestaurant.getString("address");
        this.minimumOrder = Float.valueOf(jsonRestaurant.getString("min_order"));
        this.imageUrl = jsonRestaurant.getString("image_url");
        this.id = jsonRestaurant.getString("id");
        this.rating = jsonRestaurant.getInt("rating");
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

    public String getId() {
        return id;
    }


}
