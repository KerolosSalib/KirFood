package com.kerolossalib.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.SharedPreferencesSettings;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.datamodels.User;
import com.kerolossalib.kirfood.services.RESTController;
import com.kerolossalib.kirfood.ui.adapters.RestaurantAdapter;
import com.pedromassango.ibackdrop.Backdrop;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener, View.OnClickListener {

    private static final int LOGIN_REQUEST_CODE = 2001;
    // Initialise Views
    RecyclerView restaurantRV;
    RecyclerView.LayoutManager layoutManager;

    //Initialise Adapter and tool bar
    RestaurantAdapter adapter;
    Toolbar toolbar;
    Backdrop backdrop_view;

    //Initialize Menu Buttons
    Button homeButton;
    Button profileButton;
    Button checkoutButton;


    //Declare Restaurants Array List
    ArrayList<Restaurant> restaurantsArrayList = new ArrayList<>();

    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        toolbar = findViewById(R.id.home_toolbar);
        backdrop_view = findViewById(R.id.backdrop_view);
        backdrop_view.setScrollBarSize(150);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backdrop_view.openBackdrop();
            }
        });

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);

        /*toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);*/

        //Initialize Menu Buttons
        homeButton = findViewById(R.id.home_button_menu);
        profileButton = findViewById(R.id.profile_button_menu);
        checkoutButton = findViewById(R.id.checkout_button_menu);
        homeButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        checkoutButton.setOnClickListener(this);


        restaurantRV = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestaurantAdapter(this);


        restaurantRV.setLayoutManager(layoutManager);
        restaurantRV.setAdapter(adapter);
        getLayoutFromPreferences();


        RESTController restController = new RESTController(this);
        restController.getRequest(RESTController.RESTAURANT_ENDPOINT, this, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem viewModeButton = menu.findItem(R.id.view_mode_button);
        viewModeButton.setIcon(adapter.isGridMode() ? R.drawable.ic_list_on : R.drawable.ic_grid_on);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.login_menu: {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
            case R.id.chechout_menu: {
                if (User.isLoggedin(this))
                    startActivity(new Intent(this, CheckoutActivity.class));
                else
                    startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
                return true;
            }*/
            case R.id.view_mode_button: {
                setLayoutManager();
                item.setIcon(adapter.isGridMode() ? R.drawable.ic_list_on : R.drawable.ic_grid_on);
                return true;
            }
           /* case R.id.setting_menu: {
                User.logout(this);
                return true;
            }*/

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            startActivity(new Intent(this, CheckoutActivity.class));
        }
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


    @Override
    public void onErrorResponse(VolleyError error) {

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            //This indicates that the request has either time out or there is no connection
            Toast.makeText(this, "Internet Connection error", Toast.LENGTH_LONG).show();
        } else if (error instanceof AuthFailureError) {
            //Error indicating that there was an Authentication Failure while performing the request
            Toast.makeText(this, "AuthFailureError", Toast.LENGTH_LONG).show();
        } else if (error instanceof ServerError) {
            //Indicates that the server responded with a error response
            Toast.makeText(this, "ServerError", Toast.LENGTH_LONG).show();
        } else if (error instanceof NetworkError) {
            //Indicates that there was network error while performing the request
            Toast.makeText(this, "NetworkError", Toast.LENGTH_LONG).show();
        } else if (error instanceof ParseError) {
            // Indicates that the server response could not be parsed
            Toast.makeText(this, "ParseError", Toast.LENGTH_LONG).show();
        }

        Log.e("RequestError", error.toString());
//        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                restaurantsArrayList.add(new Restaurant(jsonArray.getJSONObject(i)));
            }
            adapter.setData(restaurantsArrayList);
        } catch (JSONException e) {
            Log.e("JSONError", e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button_menu: {
                backdrop_view.closeBackdrop();
                break;
            }
            case R.id.profile_button_menu: {
                if (User.isLoggedin(this)) {
                    startActivity(new Intent(this, ProfileActivity.class));
                    backdrop_view.closeBackdrop();
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
                    backdrop_view.closeBackdrop();
                }
                break;
            }
            case R.id.checkout_button_menu: {
                if (User.isLoggedin(this)) {
                    startActivity(new Intent(this, CheckoutActivity.class));
                    backdrop_view.closeBackdrop();
                } else {
                    startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_REQUEST_CODE);
                    backdrop_view.closeBackdrop();
                }
                break;
            }
        }
    }

}

