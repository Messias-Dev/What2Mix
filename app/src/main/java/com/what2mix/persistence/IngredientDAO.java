package com.what2mix.persistence;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.activity.IngredientsImprovisedActivity;
import com.what2mix.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("ingredients");
    private Context context = null;
    private String name = null;
    private Ingredient ingredient = null;
    private Boolean isDuplicated = false;
    private List<String> ingredientsName = null;
    private List<Ingredient> ingredients = null;
    private Boolean condition = false;

    // Código improvisado para registro de ingredientes no banco de dados
    public void writeNewIngredient(Context tela, String nameParameter) {
        name = nameParameter;
        context = tela;

//        ingredient = new Ingredient(name);
//        database.push().setValue(ingredient);

//        database.setValue(null);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String nameExist = snapshot.child("name").getValue().toString();
                        if (name.equals(nameExist)) {
                            System.out.println("====================================");
                            System.out.println(nameExist + "  É DUPLICADO");
                            isDuplicated = true;
                            System.out.println("====================================");

                        }

                    }
                }
                if (!isDuplicated) {
                    ingredient = new Ingredient(name);
                    database.push().setValue(ingredient).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "Sucesso !", Toast.LENGTH_LONG).show();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Nome já existe", Toast.LENGTH_LONG).show();
                                }
                            });

                } else {
                    Toast.makeText(context, "Nome já existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    // Consulta de todos os nomes de ingredientes no banco de dados
    public List<String> findAllNames() {
        ingredientsName = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada ingrediente no banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    // Resgata especificamente o atributo "name"
                    String ingredient = data.child("name").getValue().toString();

                    // Adiciona o atributo em lista
                    ingredientsName.add(ingredient);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return ingredientsName;
    }

    // Consulta de todos os ingredientes no banco de dados
    public List<Ingredient> findAll() {

        ingredients = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Varre cada ingrediente no banco de dados
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    // Resgata o objeto do banco de dados e popula o com atributos de nome igual ao construtor
                    ingredient = data.getValue(Ingredient.class);

                    // Seta o id
                    ingredient.setId(data.getKey());

                    // Adiciona objeto em lista
                    ingredients.add(ingredient);

                    ingredient = null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return ingredients;
    }
}
