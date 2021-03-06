package com.rocktech.grocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.rocktech.grocery.Model.GroceryItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private BottomNavigationView bottomNavigationView;
    private RecyclerView popularRec, newRec, suggestedRec;
    private GroceryItemAdapter popularAdapter, newAdapter, suggestedAdapter;
    private Utils utils;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_menu, container, false);
       initViews(view);
       bottomOnclick();
       //
         utils = new Utils();
        utils.initDatabase(getActivity());
        //
        initRecViews();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("database", Context.MODE_PRIVATE);
        String returnedValue = sharedPreferences.getString("cheese", "");
        Gson gson = new Gson();
        GroceryItem cheese = gson.fromJson(returnedValue, GroceryItem.class);
        Toast.makeText(getActivity(), cheese.getName() +" "+cheese.getDescription(), Toast.LENGTH_SHORT).show();
        return view;
    }
    private void initRecViews (){
        newAdapter = new GroceryItemAdapter(getActivity());
        popularAdapter = new GroceryItemAdapter(getActivity());
        suggestedAdapter = new GroceryItemAdapter(getActivity());
        //
        newRec.setAdapter(newAdapter);
        popularRec.setAdapter(popularAdapter);
        suggestedRec.setAdapter(suggestedAdapter);
        //
        newRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        suggestedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        List<GroceryItem> newItems = utils.getAllItems(getActivity());

        //

        //
        Comparator<GroceryItem> newItemsCompare = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem item, GroceryItem t1) {
                return item.getId() - t1.getId();
            }
        };
        Comparator<GroceryItem> reverseNewItems = Collections.reverseOrder(newItemsCompare);
        Collections.sort(newItems, reverseNewItems);
        //
        if (null != newItems){
            newAdapter.setGroceryItems(newItems);
        }
        //

        List<GroceryItem> popularItems = utils.getAllItems(getActivity());

        Comparator<GroceryItem> itemComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem t1, GroceryItem t2) {
                return compareByPopularity(t1, t2);
            }
        };
        //
        Comparator<GroceryItem> reverse = Collections.reverseOrder(itemComparator);
        Collections.sort(popularItems, reverse);

        popularAdapter.setGroceryItems(popularItems);

        //
        List<GroceryItem> suggestedItems = utils.getAllItems(getActivity());
        Comparator<GroceryItem> suggestedComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem item, GroceryItem t1) {
                return item.getUserPoint() - t1.getUserPoint();
            }
        };

        Comparator<GroceryItem> reverseSuggested = Collections.reverseOrder(suggestedComparator);
        Collections.sort(suggestedItems, reverseSuggested);
        //
        suggestedAdapter.setGroceryItems(suggestedItems);
    }
    private int compareByPopularity(GroceryItem item1, GroceryItem item2){
        if (item1.getPopularityPoint() > item2.getPopularityPoint()){
            return 1;
        }
        else if (item1.getPopularityPoint() < item2.getPopularityPoint()){
            return -1;
        }
        else {
            return 0;
        }
    }

    private void initViews(View view){
        Log.d(TAG, "initViews: Views");
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        popularRec = view.findViewById(R.id.popularItemsRec);
        newRec = view.findViewById(R.id.newItemsRec);
        suggestedRec = view.findViewById(R.id.suggestedItemsRec);
    }
    //
    private void bottomOnclick(){
        Log.d(TAG, "bottomOnclick: started");
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        Toast.makeText(getActivity(), "Search Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home:
                        Toast.makeText(getActivity(), "Home Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart:
                        Toast.makeText(getActivity(), "Cart Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
