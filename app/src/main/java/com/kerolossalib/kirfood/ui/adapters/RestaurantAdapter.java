package com.kerolossalib.kirfood.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.datamodels.Restaurant;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    Context context;
    private boolean isGridMode=  false;

    public boolean isGridMode() {
        return isGridMode;
    }

    public void setGridMode(boolean gridMode) {
        isGridMode = gridMode;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isGridMode() ? R.layout.item_restaurant_grid : R.layout.item_restaurant;
        View view = inflater.inflate(layoutId, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestaurantViewHolder vh = (RestaurantViewHolder) holder;
        Restaurant item = data.get(position);
        vh.restaurantName.setText(item.getrName());
        vh.restaurantAddress.setText(item.getrAddress());
        vh.restaurantMinOrder.setText(String.valueOf(item.getrMinOrder()));

        Glide.with(context).load(item.getrImage()).into(vh.restauranImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView menuButton;
        private ImageView restauranImage;
        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView restaurantMinOrder;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            restauranImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantMinOrder = itemView.findViewById(R.id.restaurant_min_order);
            menuButton = itemView.findViewById(R.id.menu_button);
            
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2/5/2019  
                }
            });
        }
    }
    
    
}
