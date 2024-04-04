package com.example.bhojan;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class RecipeActivity extends AppCompatActivity {

    // Initialize UI elements
    private Toolbar toolbar;
    private SearchView searchView;
    private ActionBar actionBar;
    private Button americanButton;
    private Button italianButton;
    private Button mexicanButton;
    private Button asianButton;
    private Button veganButton;
    private Button ketoButton;
    private Button paleoButton;
    private Button vegetarianButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Initialize toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Discover Recipes");
        }

        // Initialize RecyclerView and set LayoutManager
        RecyclerView recipeRecyclerView = findViewById(R.id.recipe_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recipeRecyclerView.setLayoutManager(layoutManager);

        // Initialize search view
        searchView = findViewById(R.id.search_view);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submit
                filterRecipes(query);
                // Clear search view focus
                searchView.clearFocus();
                // Close keyboard
                View view = RecipeActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) RecipeActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle search query change
                return false;
            }
        });

        // Initialize cuisine buttons
        americanButton = findViewById(R.id.american_button);
        italianButton = findViewById(R.id.italian_button);
        mexicanButton = findViewById(R.id.mexican_button);
        asianButton = findViewById(R.id.asian_button);

        // Initialize diet buttons
        veganButton = findViewById(R.id.vegan_button);
        ketoButton = findViewById(R.id.keto_button);
        paleoButton = findViewById(R.id.paleo_button);
        vegetarianButton = findViewById(R.id.vegetarian_button);

        // Set click listeners for cuisine and diet buttons
        setButtonClickListener(americanButton, "American");
        setButtonClickListener(italianButton, "Italian");
        setButtonClickListener(mexicanButton, "Mexican");
        setButtonClickListener(asianButton, "Asian");
        setButtonClickListener(veganButton, "Vegan");
        setButtonClickListener(ketoButton, "Keto");
        setButtonClickListener(paleoButton, "Paleo");
        setButtonClickListener(vegetarianButton, "Vegetarian");
    }

    // Set click listener for buttons
    private void setButtonClickListener(Button button, String filter) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click and filter recipes
                filterRecipes(filter);
            }
        });
    }

    // Filter recipes based on the selected filter
    private void filterRecipes(String filter) {
        // Implement the logic to filter the recipes based on the selected filter
        // For example, you can use an API call or a local database query to filter the recipes
        // Then, update the UI with the filtered recipes
    }
}