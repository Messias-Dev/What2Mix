package com.what2mix.persistence;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.what2mix.domain.Recipe;

public class RecipeDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("recipes");

    // TODO Método para gravar receita no Realtime Database
    public void writeNewRecipe(Recipe recipe) {

        // TODO AutoId
        database.child(null).setValue(recipe);
    }

}
