package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.what2mix.R;
import com.what2mix.domain.Recipe;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private TextView tvRecipeTitle, tvRecipeAuthor, tvRecipeDate, tvRecipeDescription;
    private LinearLayout ingredientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        assignLayoutElements();

    }

    private void assignLayoutElements() {
        tvRecipeTitle = findViewById(R.id.tvRecipeTitle);
        tvRecipeAuthor = findViewById(R.id.tvRecipeAuthor);
        tvRecipeDate = findViewById(R.id.tvRecipeDate);
        tvRecipeDescription = findViewById(R.id.tvRecipeDescriptionTitle);
        ingredientsListView = findViewById(R.id.ingredientsListViewRecipe);
    }

    private View setView (final String name) {

        View ingredientItem = getLayoutInflater().inflate(R.layout.ingredient_item_recipe, null, false);
        TextView ingredientName = ingredientItem.findViewById(R.id.tvIngredientNameRecipe);

        ingredientName.setText(name);

        return ingredientItem;
    }

    private void addIngredientList (List<String> ingredientList) {
        for (String s : ingredientList) {
            View ingredientItem = setView(s);

            ingredientsListView.addView(ingredientItem);
        }
    }

    private void setRecipeContent (Recipe recipe) {
        String title = recipe.getTitle();
        String author = recipe.getUserId();
        String date = recipe.getCreatedAt();
        String description = recipe.getDescription();

        tvRecipeTitle.setText(title);
        tvRecipeDescription.setText(description);
        tvRecipeAuthor.setText(author);
        tvRecipeDate.setText(date);
    }
}