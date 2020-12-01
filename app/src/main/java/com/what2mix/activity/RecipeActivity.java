package com.what2mix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.R;
import com.what2mix.business.RecipeBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.Recipe;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private TextView tvRecipeTitle, tvRecipeAuthor, tvRecipeDate, tvRecipeDescription;
    private ImageView ivFavorite;
    private LinearLayout ingredientsListView;
    private Recipe recipe;
    private FirebaseAuth auth = FirebaseConfig.getFirebaseAuth();
    private String currentUserId;
    private boolean userLiked = false;
    private boolean userOwner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        assignLayoutElements();

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        getCurrentUserId();
        setRecipeContent(recipe);
        userLiked();

    }

    private void assignLayoutElements() {
        tvRecipeTitle = findViewById(R.id.tvRecipeTitle);
        tvRecipeAuthor = findViewById(R.id.tvRecipeAuthor);
        tvRecipeDate = findViewById(R.id.tvRecipeDate);
        tvRecipeDescription = findViewById(R.id.tvRecipeDescription);
        ingredientsListView = findViewById(R.id.ingredientsListViewRecipe);
        ivFavorite = findViewById(R.id.ivFavorite);
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
        String author = recipe.getCreatedBy();
        String date = recipe.getCreatedAt();
        String description = recipe.getDescription();

        addIngredientList(recipe.getIngredients());
        tvRecipeTitle.setText(title);
        tvRecipeDescription.setText(description);
        tvRecipeAuthor.setText(author);
        tvRecipeDate.setText(date);
    }

    private void favorite(Boolean userLiked) {
        if (userLiked) {
            recipe.getUsersLike().remove(currentUserId);

        }
    }

    private Boolean userLiked() {
        if (!RecipeBO.userLiked(recipe.getUsersLike(), currentUserId)) {
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.lightGrey));
            return false;
        } else{
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            return true;
        }
    }

    private boolean userIsOwner(String userId) {
        if (userId.equals(currentUserId)) {
            return true;
        }
        return false;
    }

    private void getCurrentUserId() {
        String email = auth.getCurrentUser().getEmail();
        DatabaseReference databaseRef = FirebaseConfig.getFirebaseReference().child("users");

        databaseRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    currentUserId = data.getKey().toString();

                    userLiked = userLiked();
                    userOwner = userIsOwner(recipe.getUserId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });

    }

}