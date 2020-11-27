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
    private List<String> ingredientsId = null;

    // Registra receita no banco de dados
    public void writeNewRecipe(Recipe recipe) {

        database.push().setValue(recipe);
    }

    // Consulta todas as receitas por id de usuário no banco de dados
    //TODO Testar e escolher qual melhor método
    public List<Recipe> findAllByUserId(String userIdParameter) {

        userId = userIdParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada receita do banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    // Resgata o objeto do banco de dados e popula o com atributos de nome igual ao construtor
                    Recipe recipe = data.getValue(Recipe.class);

                    // Seta o id
                    recipe.setId(data.getKey());

                    // Verifica se o ID de usuário correponde
                    if (recipe.getUserId().equals(userId)) {

                        // Adiciona receita na lista
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

    // Consulta todas as receitas por id de usuário no banco de dados
    public List<Recipe> findAllByIngredients(List<String> ingredientsParameter) {
        ingredientsId = ingredientsParameter;
        recipes = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada receita do banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    // Resgata o objeto do banco de dados e popula o com atributos de nome igual ao construtor
                    Recipe recipe = data.getValue(Recipe.class);

                    // Seta o id
                    recipe.setId(data.getKey());
                    System.out.println(recipe.getTitle());
                    System.out.println(recipe.getIngredientsId().toString());
                    System.out.println(ingredientsId);

                    // Verifica se a lista de ingredientes da busca contém a lista de ingredientes da receita
                    if (ingredientsId.containsAll(recipe.getIngredientsId())) {
                        System.out.println("Achei");

                        // Adiciona na lista
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
