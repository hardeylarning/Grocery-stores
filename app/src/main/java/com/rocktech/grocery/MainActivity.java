package com.rocktech.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        init();

        // TODO: 2/22/2021 Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Database", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("number", 2);
        editor.putString("name", "Roqeeb");
        editor.commit();

        setSupportActionBar(toolbar);
        //
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
      //  drawerLayout.addDrawerListener(toggle);
        //
       // toggle.syncState();
        //
        navigationView.setNavigationItemSelectedListener(this);
        //
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new MainFragment()).commit();
    }

    private void init() {
        Log.d(TAG, "init: started");
        //
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.cart:
                Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(this, "About Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms:
                Toast.makeText(this, "Terms Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.licenses:
                Toast.makeText(this, "Licenses Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.categories:
                Toast.makeText(this, "Category Selected", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
        return false;
    }
}
