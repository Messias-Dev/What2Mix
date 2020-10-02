package com.what2mix.persistence;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.what2mix.domain.Ingredient;

public class IngredientDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("ingredients");

    public void writeNewIngredient(String name) {

            Ingredient ingredient = new Ingredient(name);
            database.push().setValue(ingredient);

    }

}
