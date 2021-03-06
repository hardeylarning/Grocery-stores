package com.rocktech.grocery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rocktech.grocery.Model.GroceryItem;

import java.util.ArrayList;
import java.util.List;

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder> {
    private static final String TAG = "GroceryItemAdapter";
    private Context context;
    private List<GroceryItem> groceryItems = new ArrayList<>();

    public GroceryItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_view_list,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");
        holder.itemName.setText(groceryItems.get(position).getName());
        holder.itemPrice.setText(String.valueOf(groceryItems.get(position).getPrice()));
        Glide.with(context).asBitmap()
                .load(groceryItems.get(position).getImageUrl())
                .into(holder.itemImage);

//        holder.itemImage.setImageResource();

        /**
         *
         * Glide.with(fragment)
         *     .load(url)
         *     .into(imageView);
         *
         *      Glide
         *     .with(myFragment)
         *     .load(url)
         *     .centerCrop()
         *     .placeholder(R.drawable.loading_spinner)
         *     .into(myImageView);
         * */

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2/23/2021 navigation
            }
        });


    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }

    public void setGroceryItems(List<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemPrice, itemName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.txtItemImage);
            itemPrice = itemView.findViewById(R.id.txtItemPrice);
            itemName = itemView.findViewById(R.id.txtItemName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
