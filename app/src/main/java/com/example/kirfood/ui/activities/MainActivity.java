package com.example.kirfood.ui.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kirfood.R;
import com.example.kirfood.datamodels.Restaurant;
import com.example.kirfood.ui.adabters.RestaurantAdabter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutMager;
    RestaurantAdabter adabter;
    ArrayList<Restaurant> arrayList;

    private ArrayList<Restaurant> getData(){
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("www.mackdonald.com/logo","Mackdonald's","Via Tiburtina",10 ));
        arrayList.add(new Restaurant("www.burgerking.com/logo","burgerking","Via Tiburtina",9 ));
        arrayList.add(new Restaurant("www.roadhouse.com/logo","roadhouse","Via Tiburtina",8 ));
        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.recyclerview);
        layoutMager = new LinearLayoutManager(this);
        adabter = new RestaurantAdabter(this, getData());

        restaurantRV.setLayoutManager(layoutMager);
        restaurantRV.setAdapter(adabter);
        // TODO: 1/30/2019
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.login_menu){
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }else if (item.getItemId() == R.id.chechout_menu){
            startActivity(new Intent(this,CheckoutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

