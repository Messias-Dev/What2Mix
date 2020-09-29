package com.what2mix.persistence;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.what2mix.domain.Recipe;

public class RecipeDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("recipes");


    public void writeNewRecipe(Recipe recipe) {

        database.push().setValue(recipe);
    }

}
