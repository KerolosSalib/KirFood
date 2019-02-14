package com.kerolossalib.kirfood.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;

import com.kerolossalib.kirfood.R;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kerolossalib.kirfood.datamodels.Product;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.ui.adapters.ProductAdapter;



import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements ProductAdapter.OnQuanityChangedListener{

    // UI components
    private TextView shopNameTv, shopAddress, totalTxtView;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView restaurantIv;

    // RecyclerView components
    private RecyclerView productRv;
    private LinearLayoutManager layoutManager;
    private ProductAdapter adapter;


    // data model
    private Restaurant restaurant;

    Toolbar toolbar;



    private float total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        shopNameTv = findViewById(R.id.shop_name_tv);
        shopAddress = findViewById(R.id.shop_desc_tv);
        totalTxtView = findViewById(R.id.total_tv);
        restaurantIv = findViewById(R.id.shop_iv);

        checkout = findViewById(R.id.checkout);
        progressBar = findViewById(R.id.progress);
        productRv = findViewById(R.id.product_rv);

        restaurant = getRestaurant();
        restaurant.setImageUrl("https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg");

        restaurant.setProducts(getProducts());

        shopNameTv.setText(restaurant.getName());
        shopAddress.setText(restaurant.getAddress());
        Glide.with(this).load(restaurant.getImageUrl()).into(restaurantIv);
        progressBar.setMax((int)restaurant.getMinimumOrder() * 100);


        binData();
        layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this, restaurant.getProducts());
        adapter.setOnQuanityChangedListener(this);
        productRv.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) productRv.getItemAnimator()).setSupportsChangeAnimations(false);
        productRv.setAdapter(adapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this,CheckoutActivity.class));
            }
        });


    }

    private void binData(){

        restaurant = getRestaurant();
        restaurant.setImageUrl("https://rovato5stelle.files.wordpress.com/2013/11/mcdonald.jpg");
        restaurant.setProducts(getProducts());
        shopNameTv.setText(restaurant.getName());
        shopAddress.setText(restaurant.getAddress());
        Glide.with(this).load(restaurant.getImageUrl()).into(restaurantIv);
        progressBar.setMax((int)restaurant.getMinimumOrder() * 100);


    }

    //TODO hardcoded
    private Restaurant getRestaurant() {
        return new Restaurant("","Fraschetta", "Via le mani dal naso", 10);
    }

    //TODO hardcoded
    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        products.add(new Product("McMenu", 7,"https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));

        return products;
    }

    private void updateTotal(float item){
        total= total + item;
        totalTxtView.setText(getString(R.string.total).concat(String.valueOf(total)));
    }

    private void updateProgress(int progress){
        progressBar.setProgress(progress);
    }

    private void enableBttuon(){
        checkout.setEnabled(total>=restaurant.getMinimumOrder());
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int)total*100);
        enableBttuon();
    }
}