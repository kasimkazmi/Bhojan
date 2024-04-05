package com.example.bhojan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView dishNameTextView;
        private TextView mealTypeTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            dishNameTextView = itemView.findViewById(R.id.dish_name_text_view);
            mealTypeTextView = itemView.findViewById(R.id.meal_type_text_view);
        }

        public void bind(Recipe recipe) {
            dishNameTextView.setText(recipe.getDishName());
            mealTypeTextView.setText(recipe.getMealType());
        }
    }
}