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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.R;
import com.what2mix.business.RecipeBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.Recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeActivity extends AppCompatActivity {

    private TextView tvRecipeTitle, tvRecipeAuthor, tvRecipeDate, tvRecipeDescription;
    private ImageView ivFavorite;
    private LinearLayout ingredientsListView;
    private Recipe recipe;
    private FirebaseAuth auth = FirebaseConfig.getFirebaseAuth();
    private String currentUserId;
    private boolean userLiked = false;
    private DatabaseReference recipesRef = FirebaseConfig.getFirebaseReference().child("recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setStatusAndActionBar();
        assignLayoutElements();

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        getCurrentUserId();
        setRecipeContent(recipe);

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite();
            }
        });

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

    private void favorite() {
        if (userLiked) {
            recipe.getUsersLike().remove(currentUserId);
            recipe.setCountLike(recipe.getCountLike() - 1);
            userLiked = false;
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.lightGrey));
        } else {
            recipe.getUsersLike().add(currentUserId);
            recipe.setCountLike(recipe.getCountLike() + 1);
            userLiked = true;
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        }

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(recipe.getId() + "/usersLike/", recipe.getUsersLike());
        childUpdates.put(recipe.getId() + "/countLike/", recipe.getCountLike());
        recipesRef.updateChildren(childUpdates);

    }

    private void setFavorite() {
        if (userLiked) {
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        } else {
            ivFavorite.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.lightGrey));
        }
    }


    private void getCurrentUserId() {
        String email = auth.getCurrentUser().getEmail();
        DatabaseReference databaseRef = FirebaseConfig.getFirebaseReference().child("users");

        databaseRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    currentUserId = data.getKey();

                    userLiked = RecipeBO.userLiked(recipe.getUsersLike(), currentUserId);
                    setFavorite();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });

    }

    public void setStatusAndActionBar(){
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}