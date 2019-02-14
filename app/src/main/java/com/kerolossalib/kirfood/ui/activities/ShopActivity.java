package com.kerolossalib.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.datamodels.Product;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.services.RESTController;
import com.kerolossalib.kirfood.ui.adapters.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class ShopActivity extends AppCompatActivity implements ProductAdapter.OnQuanityChangedListener, Response.Listener<String>, Response.ErrorListener {

    // UI components
    private TextView shopNameTv, shopAddress, totalTxtView;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView restaurantIv;

    // RecyclerView components
    private RecyclerView productRv;
    private LinearLayoutManager layoutManager;
    private ProductAdapter adapter;

    ArrayList<Product> products = new ArrayList<>();

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


        String id = getIntent().getStringExtra("id");
        RESTController restController = new RESTController(this);
        restController.getRequest(RESTController.RESTAURANT_ENDPOINT.concat("/").concat(id), this, this);
        Log.i("ShopActivity", "onCreate: " + RESTController.RESTAURANT_ENDPOINT.concat("/").concat(id));


//        binData();


        layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this);
        adapter.setOnQuanityChangedListener(this);
        productRv.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) Objects.requireNonNull(productRv.getItemAnimator())).setSupportsChangeAnimations(false);
        productRv.setAdapter(adapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
            }
        });
    }

    private void binData() {
        String id = getIntent().getStringExtra("id");
        RESTController restController = new RESTController(this);
        restController.getRequest(RESTController.RESTAURANT_ENDPOINT.concat("/").concat(id), this, this);

//        restaurant.setProducts(getProducts());


    }


    private void setRestaurantInfo() {

        if (restaurant != null) {
            shopNameTv.setText(this.restaurant.getName());
            shopAddress.setText(this.restaurant.getAddress());
            Glide.with(this).load(this.restaurant.getImageUrl()).into(restaurantIv);
            progressBar.setMax((int) this.restaurant.getMinimumOrder() * 100);
        }

    }

    //TODO hardcoded
/*    private Restaurant getRestaurant() {
        return new Restaurant();
    }*/

    //TODO hardcoded
    private ArrayList<Product> getProducts() {

        products.add(new Product("McMenu", 7, "https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg"));
        return products;
    }

    private void updateTotal(float item) {
        total = total + item;
        totalTxtView.setText(getString(R.string.total).concat(String.valueOf(total)));
    }

    private void updateProgress(int progress) {
        progressBar.setProgress(progress);
    }

    private void enableButton() {
        checkout.setEnabled(total >= restaurant.getMinimumOrder());
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgress((int) total * 100);
        enableButton();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("RequestError", error.getMessage());
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            this.restaurant = new Restaurant(jsonObject);

            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < jsonArray.length(); i++) {
                products.add(new Product(jsonArray.getJSONObject(i)));
            }
            restaurant.setProducts(products);
            adapter.setData(products);
            setRestaurantInfo();

        } catch (JSONException e) {
            Log.e("JSONError", e.getMessage());

        }
    }

}