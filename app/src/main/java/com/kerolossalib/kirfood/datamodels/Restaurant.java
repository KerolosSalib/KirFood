package com.kerolossalib.kirfood.datamodels;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity (tableName = "restaurant")
public class Restaurant {

    public void setId(String id) {
        this.id = id;
    }

    @ColumnInfo(name = "restaurant_id")
    private String id;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "minimum_order")
    private float minimumOrder;

    public void setRating(int rating) {
        this.rating = rating;
    }

    @ColumnInfo(name = "rating")
    private int rating;

    @Ignore
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

    public int getRating() {
        return rating;
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
