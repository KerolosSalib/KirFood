package com.kerolossalib.kirfood.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.datamodels.Order;
import com.kerolossalib.kirfood.datamodels.Product;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.ui.adapters.OrderProductsAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener, OrderProductsAdapter.onItemRemovedListener {

    private TextView restaturantTv, restaurantAdress, totalTv;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;


    private Order order;
    private float total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        restaturantTv = findViewById(R.id.restaurant_name);
        restaurantAdress = findViewById(R.id.restaurant_adress);
        totalTv = findViewById(R.id.total_tv);
        productRv = findViewById(R.id.product_rv);
        payBtn = findViewById(R.id.pay_btn);

        // Initialize datamodel object
        order = getOrder();
        total = order.getTotal();

        // setup recyclerview
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        adapter = new OrderProductsAdapter(this, order.getProducts(), order.getRestaurant().getMinimumOrder());
        adapter.setOnItemRemovedListener(this);
        productRv.setAdapter(adapter);

        //set click listener for button
        payBtn.setOnClickListener(this);

        bindData();

    }


    private void bindData() {
        restaturantTv.setText(order.getRestaurant().getName());
        restaurantAdress.setText(order.getRestaurant().getAddress());
        totalTv.setText(String.valueOf(order.getTotal()));


    }


    //TODO hardcoded

    private Order getOrder() {

        Order mockOrder = new Order();
        mockOrder.setProducts(getProducts());
        mockOrder.setRestaurant(getRestaurant());
        float total=0;
        for (Product x: mockOrder.getProducts()){
            total = (float) (x.getQuantity() * x.getPrice());
        }

        mockOrder.setTotal(total);

        return mockOrder;
    }


    private Restaurant getRestaurant() {
        return new Restaurant("", "Macdonald's", "Via le mani dal naso", 10);
    }

    //TODO hardcoded
    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("McMenu", 5, 2));
        products.add(new Product("McMenu", 5, 2));
        products.add(new Product("McMenu", 5, 2));

        return products;

    }

    @Override
    public void onClick(View view) {
        //TODO manageClick
    }

    @Override
    public void onItemRemoved(float subtotal) {
        updateTotal(subtotal);
    }

    private void updateTotal(float subtotal) {
        if (total == 0) return;
        total -= subtotal;
        totalTv.setText(String.valueOf(total));
    }
}