package com.kerolossalib.kirfood.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.datamodels.Product;
import com.kerolossalib.kirfood.datamodels.Restaurant;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter {


    private LayoutInflater mInflter;
    private ArrayList<Product> data;
    Context context;

    public ProductAdapter(Context context, ArrayList<Product> data) {

        this.data = data;
        mInflter = LayoutInflater.from(context);
        this.context = context;
    }

    public ProductAdapter(Context context) {

        this.data = new ArrayList<>();
        mInflter = LayoutInflater.from(context);
        this.context = context;
    }

    public interface OnQuanityChangedListener {
        void onChange(float price);
    }

    public OnQuanityChangedListener getOnQuanityChangedListener() {
        return onQuanityChangedListener;
    }

    public void setOnQuanityChangedListener(OnQuanityChangedListener onQuanityChangedListener) {
        this.onQuanityChangedListener = onQuanityChangedListener;
    }

    private OnQuanityChangedListener onQuanityChangedListener;

    public void setData(ArrayList<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = mInflter.inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ProductViewHolder vh = (ProductViewHolder) viewHolder;
        Product product = data.get(position);


        vh.productName.setText(product.getName());
        vh.productPrice.setText(String.valueOf(product.getPrice()));
        vh.productQty.setText(String.valueOf(product.getQuantity()));
        Glide.with(context).load(product.getImageUrl()).into(vh.productImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<Product> getSelectedProdutcs (){
        ArrayList<Product> selectedProducts = new ArrayList<>();
        for(Product product : data ){
            if (product.getQuantity() > 0 )
                selectedProducts.add(product);
        }
        return selectedProducts;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productPrice, productQty;
        ImageView  productImage, addBtn,removeBtn;


        ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);


            productQty = itemView.findViewById(R.id.item_quantity);

            addBtn = itemView.findViewById(R.id.up_button);
            removeBtn = itemView.findViewById(R.id.down_button);

            productImage = itemView.findViewById(R.id.product_image);

            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product product = data.get(getAdapterPosition());


            if (view.getId() == R.id.up_button) {
                product.increaseQuantity();
                notifyItemChanged(getAdapterPosition());
                onQuanityChangedListener.onChange(product.getPrice());

            } else if (view.getId() == R.id.down_button) {

                if (product.getQuantity() == 0) return;
                product.decreaseQuantity();
                notifyItemChanged(getAdapterPosition());
                onQuanityChangedListener.onChange(product.getPrice() * -1);


            }


        }
    }
}