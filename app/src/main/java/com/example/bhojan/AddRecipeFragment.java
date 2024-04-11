package com.example.bhojan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class AddRecipeFragment extends Fragment {

    private RecyclerView recipeRecyclerView;
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter recipeAdapter;
    private Button addButton;

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipe_recycler_view);
        recipeAdapter = new RecipeAdapter(recipes);
        recipeRecyclerView.setAdapter(recipeAdapter);

        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        addButton = view.findViewById(R.id.add_recipe_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the AlertDialog.Builder class
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // Set the title for the dialog
                builder.setTitle("Add Recipe");

                // Create a View object to inflate the layout for the dialog
                View view = LayoutInflater.from(getContext()).inflate(R.layout.add_recipe_dialog, null);

                // Initialize the input fields for the recipe name and type
                final EditText dishNameEditText = view.findViewById(R.id.dish_name_edit_text);
                final EditText mealTypeEditText = view.findViewById(R.id.meal_type_edit_text);

                // Set the positive button for the dialog
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Get the values entered by the user
                        String dishName = dishNameEditText.getText().toString();
                        String mealType = mealTypeEditText.getText().toString();

                        // Create a new Recipe object and add it to the list of recipes
                        Recipe newRecipe = new Recipe(dishName, mealType);
                        recipes.add(newRecipe);

                        // Notify the adapter that the data has changed
                        recipeAdapter.notifyItemInserted(recipes.size() - 1);
                    }
                });

                // Set the negative button for the dialog
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog when the user clicks the "Cancel" button
                        dialog.dismiss();
                    }
                });

                // Create the dialog and display it
                builder.create().show();
            }
        });

        return view;
    }

    public static AddRecipeFragment newInstance() {
        return new AddRecipeFragment();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        recipeAdapter.notifyDataSetChanged();
    }
}