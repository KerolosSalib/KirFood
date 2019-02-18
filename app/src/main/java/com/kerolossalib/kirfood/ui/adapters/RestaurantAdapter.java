package com.kerolossalib.kirfood.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.datamodels.Restaurant;
import com.kerolossalib.kirfood.ui.activities.ShopActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;
    private boolean isGridMode = false;

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

    public RestaurantAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
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
        vh.restaurantName.setText(item.getName());
        vh.restaurantAddress.setText(item.getAddress());
        vh.restaurantMinOrder.setText(String.valueOf(item.getMinimumOrder()));
        vh.restaurantRating.setText(String.valueOf(item.getRating()));
        Glide.with(context).load(item.getImageUrl()).into(vh.restauranImage);
    }

    public void setData(ArrayList<Restaurant> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private CardView menuCardView;
        private ImageView restauranImage;
        private TextView restaurantName;
        private TextView restaurantAddress;
        private TextView restaurantMinOrder;
        private TextView restaurantRating;


        RestaurantViewHolder(View itemView) {
            super(itemView);
            menuCardView = itemView.findViewById(R.id.restaurant_card_view);
            restauranImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantMinOrder = itemView.findViewById(R.id.restaurant_min_order);
            restaurantRating = itemView.findViewById(R.id.restaurant_rating);


            menuCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2/5/2019
                    String idRestaurant = data.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context, ShopActivity.class);
                    intent.putExtra("id", idRestaurant);
                    context.startActivity(intent);
                }
            });
        }
    }


}
