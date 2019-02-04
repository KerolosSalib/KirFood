package com.example.kirfood.ui.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kirfood.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdabter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<String> data;
    public RestaurantAdabter(Context context, ArrayList<String> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_restaurant,parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestaurantViewHolder vh =  (RestaurantViewHolder) holder;
        vh.restaurantName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        public TextView restaurantName;
        public RestaurantViewHolder(View itemView){
            super(itemView);
            restaurantName = itemView.findViewById(R.id.name_text_view);

        }
    }
}
