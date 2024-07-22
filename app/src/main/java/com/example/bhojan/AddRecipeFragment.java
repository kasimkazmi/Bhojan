package com.example.bhojan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhojan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeFragment extends Fragment implements AddRecipeDialog.OnAddClickListener, FragmentResultListener {

    private static final String DIALOG_TAG = "AddRecipeDialog";
    private static final String REQUEST_KEY = "addRecipeRequestKey";
    private static final String RESULT_KEY = "addRecipeResultKey";

    private List<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter recipeAdapter;

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getChildFragmentManager().setFragmentResultListener(REQUEST_KEY, this, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        RecyclerView recipeRecyclerView = view.findViewById(R.id.recipe_recycler_view);
        recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        recipeRecyclerView.setAdapter(recipeAdapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            showAddRecipeDialog();
        });

        return view;
    }

    private void showAddRecipeDialog() {
        AddRecipeDialog addRecipeDialog = new AddRecipeDialog(requireContext(), this);
        addRecipeDialog.show(getChildFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onAddClick(String dishName, String mealType, String ingredients, String description, Bitmap image) {
        Recipe newRecipe = new Recipe(dishName, mealType, ingredients, description, image);
        recipes.add(newRecipe);
        recipeAdapter.notifyDataSetChanged();

        Log.d("AddRecipeFragment", "Received data:");
        Log.d("AddRecipeFragment", "Dish Name: " + dishName);
        Log.d("AddRecipeFragment", "Meal Type: " + mealType);
        Log.d("AddRecipeFragment", "Ingredients: " + ingredients);
        Log.d("AddRecipeFragment", "Description: " + description);
        Log.d("AddRecipeFragment", "Image: " + image);
        // You can also use the "image" Bitmap if needed

        // Perform any further processing or actions with the received data

        // Update the added recipe views in the RecyclerView
        int position = recipes.size() - 1;
        recipeAdapter.notifyItemInserted(position);
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if (requestKey.equals(REQUEST_KEY)) {
            String dishName = result.getString(AddRecipeDialog.KEY_DISH_NAME);
            String mealType = result.getString(AddRecipeDialog.KEY_MEAL_TYPE);
            String ingredients = result.getString(AddRecipeDialog.KEY_INGREDIENTS);
            String description = result.getString(AddRecipeDialog.KEY_DESCRIPTION);
            Bitmap image = result.getParcelable(AddRecipeDialog.KEY_IMAGE);

            onAddClick(dishName, mealType, ingredients, description, image);
        }
    }

    public static AddRecipeFragment newInstance() {
        return new AddRecipeFragment();
    }
}