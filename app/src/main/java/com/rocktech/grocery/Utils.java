package com.rocktech.grocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rocktech.grocery.Model.GroceryItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String TAG = "Utils";
    public static final String DATABASE_NAME = "database";
    private static int ID = 0;

    public static int getID() {
        ID++;
        return ID;
    }

    public Utils() {
    }
     public List<GroceryItem> getAllItems (Context context){
         Log.d(TAG, "allItems: started");
         Gson gson = new Gson();
         SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
         Type type = new TypeToken<List<GroceryItem>>(){}.getType();
         List<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", null), type);
         return allItems;
     }
    
    public void initDatabase(Context context){
        Log.d(TAG, "initDatabase: Stored");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,
                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String file = gson.toJson(new GroceryItem("cheese", "new", "https://",
//                "burger", 23, 2.5));
//        editor.putString("cheese", file);
//        editor.apply();

        Gson gson = new Gson();

        Type type = new TypeToken<List<GroceryItem>>(){}.getType();

        List<GroceryItem> possibleItems = gson.fromJson(sharedPreferences.getString("allItems", ""), type);

        if (possibleItems == null){
            initAllItems(context);
        }

    }
    private void initAllItems(Context context){
        Log.d(TAG, "initAllItems: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        List<GroceryItem> groceryItems = new ArrayList<>();

        GroceryItem iceCream = new GroceryItem("ice cream", "produced of fresh milk",
                "https://post.healthline.com/wp-content/uploads/2020/09/healthy-eating-ingredients-1200x628-facebook-1200x628.jpg",
                "food", 34, 4.9);
        groceryItems.add(new GroceryItem("cheese", "Best cheese",
                "https://www.evergreenslc.com/images/easyblog_articles/160/pg-green-foods-st-patricks-day-06-full.jpg",
                "vanilla", 230, 4.5));
        groceryItems.add(new GroceryItem("Cucumber", "Best cheese",
                "https://post.healthline.com/wp-content/uploads/2020/09/healthy-eating-ingredients-1200x628-facebook-1200x628.jpg",
                "food", 230, 4.5));
        groceryItems.add(new GroceryItem("Coca Cola", "Best cheese",
                "https://www.evergreenslc.com/images/easyblog_articles/160/pg-green-foods-st-patricks-day-06-full.jpg",
                "vanilla", 250, 6.5));
        groceryItems.add(new GroceryItem("Spaghetti", "Best spaghetti",
                "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/nutraingredients.com/article/2020/02/24/study-different-foods-linked-to-different-types-of-stroke/10734771-1-eng-GB/Study-Different-foods-linked-to-different-types-of-stroke_wrbm_large.jpg",
                "vanilla", 280, 5.5));
        groceryItems.add(new GroceryItem("Clear Shampoo", "Best Shampoo",
                "https://post.healthline.com/wp-content/uploads/2020/09/healthy-eating-ingredients-1200x628-facebook-1200x628.jpg",
                "vanilla", 230, 4.5));
        groceryItems.add(new GroceryItem("Axe body spray", "Best cheese",
                "https://www.evergreenslc.com/images/easyblog_articles/160/pg-green-foods-st-patricks-day-06-full.jpg",
                "food", 230, 7.5));
        groceryItems.add(new GroceryItem("Kleenex", "Best cheese",
                "https://www.evergreenslc.com/images/easyblog_articles/160/pg-green-foods-st-patricks-day-06-full.jpg",
                "vanilla", 230, 4.5));
        groceryItems.add(new GroceryItem("cheese", "Best cheese",
                "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/nutraingredients.com/article/2020/02/24/study-different-foods-linked-to-different-types-of-stroke/10734771-1-eng-GB/Study-Different-foods-linked-to-different-types-of-stroke_wrbm_large.jpg",
                "vanilla", 230, 4.5));
        groceryItems.add(iceCream);


        String finalList = gson.toJson(groceryItems);
        editor.putString("allItems", finalList);
        editor.apply();
    }
}
