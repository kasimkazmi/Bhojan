package com.example.bhojan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class AddRecipeFragment extends Fragment {

    private List<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter recipeAdapter;

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    private AddRecipeDialog.OnAddClickListener onAddClickListener = new AddRecipeDialog.OnAddClickListener() {
        @Override
        public void onAddClick(String dishName, String mealType, String ingredients, String description, Bitmap image) {
            // Create a new Recipe object with the provided information
            Recipe newRecipe = new Recipe(dishName, mealType, ingredients, description, image);

            // Add the new recipe to the recipes list
            recipes.add(newRecipe);

            // Notify the adapter that a new item has been inserted
            recipeAdapter.notifyItemInserted(recipes.size() - 1);
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        RecyclerView recipeRecyclerView = view.findViewById(R.id.recipe_recycler_view);
        recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        recipeRecyclerView.setAdapter(recipeAdapter);

        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            AddRecipeDialog addRecipeDialog = new AddRecipeDialog(getContext(), requireActivity(), onAddClickListener);
            addRecipeDialog.show();
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