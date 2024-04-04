package com.example.bhojan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new WindowInsetsListener());

        // Initialize bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.HOME) {
                    // Handle Add Recipe tab click
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new HomeFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.Add) {
                    // Handle Add Recipe  tab click
                    // Handle Add Recipe tab click
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AddRecipeFragment())
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.PROFILE) {
                    // Handle Profile tab click
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProfileFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });

        // Set default tab selection
        bottomNavigationView.setSelectedItemId(R.id.HOME);
    }
}