package com.kerolossalib.kirfood.datamodels;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "order")
public class Order {

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @Embedded //Define a relation ( the object restaurant is embedded or included in object Order)
    private Restaurant restaurant;

    @ColumnInfo(name = "products")
    @TypeConverters(ProductTypeConverter.class)
    private List<Product> products;

    @ColumnInfo(name = "total")
    private float total;

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public String getRestaurantId() {
        return restaurant.getId();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}