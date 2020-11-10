package com.what2mix.persistence;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class RecipeDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("recipes");
    private String userId = null;
    private List<Recipe> recipes = null;
    private List<Ingredient> ingredients = null;


    public void writeNewRecipe(Recipe recipe) {

        database.push().setValue(recipe);
    }

    //TODO Testar e escolher qual melhor método
    public List<Recipe> findAllByUserId(String userIdParameter) {

        userId = userIdParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada receita do banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    // Popula o objeto
                    Recipe recipe = data.getValue(Recipe.class);
                    recipe.setId(data.getKey());

                    // Verifica se o ID de usuário correponde
                    if (recipe.getUserId().equals(userId)) {
                        recipes.add(recipe);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return recipes;

    }

    public List<Recipe> findAllByIngredients(List<Ingredient> ingredientsParameter) {
        ingredients = ingredientsParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada receita do banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    // Popula o objeto
                    Recipe recipe = data.getValue(Recipe.class);
                    recipe.setId(data.getKey());

                    // Verifica se a lista de ingredientes da busca contém a lista de ingredientes da receita
                    if (ingredients.containsAll(recipe.getIngredientsId())) {
                        recipes.add(recipe);

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
