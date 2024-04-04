/*
package com.example.bhojan;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;
    private EditText searchEditText;
    private Button americanButton, italianButton, mexicanButton, asianButton, veganButton, ketoButton, paleoButton, vegetarianButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Initialize views
        recipeRecyclerView = findViewById(R.id.recipe_recycler_view);
        searchEditText = findViewById(R.id.search_edit_text);
        americanButton = findViewById(R.id.american_button);
        italianButton = findViewById(R.id.italian_button);
        mexicanButton = findViewById(R.id.mexican_button);
        asianButton = findViewById(R.id.asian_button);
        veganButton = findViewById(R.id.vegan_button);
        ketoButton = findViewById(R.id.keto_button);
        paleoButton = findViewById(R.id.paleo_button);
        vegetarianButton = findViewById(R.id.vegetarian_button);

        // Initialize cuisine and diet buttons click listeners
        setButtonClickListener(americanButton, "American");
        setButtonClickListener(italianButton, "Italian");
        setButtonClickListener(mexicanButton, "Mexican");
        setButtonClickListener(asianButton, "Asian");
        setButtonClickListener(veganButton, "Vegan");
        setButtonClickListener(ketoButton, "Keto");
        setButtonClickListener(paleoButton, "Paleo");
        setButtonClickListener(vegetarianButton, "Vegetarian");

        // Initialize recipe list
        recipeList = new ArrayList<>();
        // Add some sample recipes to the list
        // For example:
        // recipeList.add(new Recipe("Recipe 1", R.drawable.recipe1));
        // recipeList.add(new Recipe("Recipe 2", R.drawable.recipe2));

        // Initialize recipe adapter
        recipeAdapter = new RecipeAdapter(recipeList);

        // Set the layout manager and adapter for the recycler view
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeRecyclerView.setAdapter(recipeAdapter);

        // Set search edit text listener
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Handle search query change
                // For example:
                // filterRecipes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.recipe_item, parent, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            Recipe currentRecipe = recipeList.get(position);
            holder.bind(currentRecipe);
        }

        @Override
        public int getItemCount() {
            return recipeList.size();
        }


        // RecipeViewHolder class
        public class RecipeViewHolder extends RecyclerView.ViewHolder {
            private ImageView recipeImageView;
            private TextView recipeTitleTextView;

            public RecipeViewHolder(View itemView) {
                super(itemView);
                recipeImageView = itemView.findViewById(R.id.recipe_image);
                recipeTitleTextView = itemView.findViewById(R.id.recipe_title);
            }

            public void bind(Recipe recipe) {
                // Set the recipe image and title here
                // For example:
                recipeImageView.setImageResource(recipe.getImageResourceId());
                recipeTitleTextView.setText(recipe.getTitle());
            }
        }
    }
*/
