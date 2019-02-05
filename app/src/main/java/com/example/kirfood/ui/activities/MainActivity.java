package com.example.kirfood.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kirfood.R;
import com.example.kirfood.SharedPreferencesSettings;
import com.example.kirfood.datamodels.Restaurant;
import com.example.kirfood.ui.adapters.RestaurantAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Restaurant> arrayList;
    String TAG = "KIRO";


    private ArrayList<Restaurant> getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new Restaurant("https://foodrevolution.org/wp-content/uploads/2018/04/blog-featured-diabetes-20180406-1330.jpg", "Mackdonald's", "Via Tiburtina", 10));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg", "Burgerking", "Via Tiburtina", 9));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/46239/salmon-dish-food-meal-46239.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/928475/pexels-photo-928475.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/1624487/pexels-photo-1624487.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/675951/pexels-photo-675951.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/1633525/pexels-photo-1633525.jpeg", "Roadhouse", "Via Tiburtina", 8));
        arrayList.add(new Restaurant("https://images.pexels.com/photos/161519/abstract-barbecue-barbeque-bbq-161519.jpeg", "KFC", "Via Tiburtina", 8));
        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRV = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this, getData());
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
        getLayoutFromPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem viewModeButton = menu.findItem(R.id.view_mode_button);
        viewModeButton.setIcon(adapter.isGridMode() ? R.drawable.baseline_dehaze_black_18 : R.drawable.baseline_grid_on_black_18);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.chechout_menu) {
            startActivity(new Intent(this, CheckoutActivity.class));
            return true;
        } else if (item.getItemId() == R.id.view_mode_button) {
            setLayoutManager();
            item.setIcon(adapter.isGridMode() ? R.drawable.baseline_dehaze_black_18 : R.drawable.baseline_grid_on_black_18);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLayoutManager() {
        layoutManager = adapter.isGridMode() ? new LinearLayoutManager(this) : new GridLayoutManager(this, 2);
        adapter.setGridMode(!adapter.isGridMode());
        saveLayoutInPreferences();
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }


    public void saveLayoutInPreferences() {
        SharedPreferencesSettings.setSharedPreferences(this, "isGridMode", adapter.isGridMode());
    }

    public void getLayoutFromPreferences() {
        adapter.setGridMode(SharedPreferencesSettings.getBooleanFromPreferences(this, "isGridMode"));
        layoutManager = adapter.isGridMode() ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);
        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
    }


}

