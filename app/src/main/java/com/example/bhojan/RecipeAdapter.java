package com.example.bhojan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhojan.R;

import java.util.List;

public  class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.dishNameTextView.setText(recipe.getDishName());
        holder.mealTypeTextView.setText(recipe.getMealType());
        holder.ingredientsTextView.setText(recipe.getIngredients());
        holder.descriptionTextView.setText(recipe.getDescription());
        holder.imageView.setImageBitmap(recipe.getImage());
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView dishNameTextView;
        TextView mealTypeTextView;
        TextView ingredientsTextView;
        TextView descriptionTextView;
        ImageView imageView;
        RecipeViewHolder(View view) {
            super(view);
            dishNameTextView = view.findViewById(R.id.dish_name_text_view);
            mealTypeTextView = view.findViewById(R.id.meal_type_text_view);
            ingredientsTextView = view.findViewById(R.id.ingredients_text_view);
            descriptionTextView = view.findViewById(R.id.description_text_view);
            imageView = view.findViewById(R.id.img);
        }
    }
}
