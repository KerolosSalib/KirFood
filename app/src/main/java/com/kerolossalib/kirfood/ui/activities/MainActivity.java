package com.kerolossalib.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;

import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.SharedPreferencesSettings;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.ui.adapters.RestaurantAdapter;


import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    Toolbar toolbar;
    ArrayList<Restaurant> arrayList;



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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        restaurantRV = findViewById(R.id.recycler_view);
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
        viewModeButton.setIcon(adapter.isGridMode() ? R.drawable.baseline_dehaze_24 : R.drawable.baseline_grid_on_24);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_button: {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }case R.id.chechout_menu:{
                startActivity(new Intent(this, CheckoutActivity.class));
                return true;
            }case R.id.view_mode_button:{
                setLayoutManager();
                item.setIcon(adapter.isGridMode() ? R.drawable.baseline_dehaze_24 : R.drawable.baseline_grid_on_24);
            }case R.id.filters:{

            }

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

