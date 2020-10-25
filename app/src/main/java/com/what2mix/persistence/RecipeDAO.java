package com.what2mix.persistence;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.domain.Recipe;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("recipes");
    private String userId = null;
    private List<Recipe> recipes = null;
    private List<String> ingredients = null;


    public void writeNewRecipe(Recipe recipe) {

        database.push().setValue(recipe);
    }

    //TODO Testar e escolher qual melhor método
    public List<Recipe> findAllByUserId(String userIdParameter) {

        userId = userIdParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    String userIdDatabase = data.child("userId").getValue().toString();

                    if (userId.equals(userIdDatabase)) {

                        // Método 1
                        Recipe recipe = data.getValue(Recipe.class);

                        // Método 2
//                        String id = data.getKey();
//                        String description = data.child("description").getValue().toString();
//
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
//                        LocalDate createdAt = LocalDate.parse(data.child("createdAt").getValue().toString(), formatter);
//
//                        List<String> ingredients = new ArrayList<>();
//
//                        for (DataSnapshot ingredient : data.child("ingredients").getChildren()){
//                            ingredients.add(ingredient.getValue().toString());
//                        }
//
//                        Recipe recipe = new Recipe(id, userIdDatabase, description, createdAt, ingredients);

                        recipes.add(recipe);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return null;

    }

    public List<Recipe> findAllByIngredients(List<String> ingredientsParameter) {
        ingredients = ingredientsParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada receita do banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    // Varre cada ingrediente da lista de ingredientes presente no banco de dados
                    for (DataSnapshot dataIngredient : data.child("ingredients").getChildren()){

                        // Compara se a lista de ingredientes preenchida pela busca contém o ingrediente do banco de dados
                        if (ingredients.contains(dataIngredient.getValue().toString())){
                            Recipe recipe = data.getValue(Recipe.class);
                            recipes.add(recipe);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return recipes;
    }
}
