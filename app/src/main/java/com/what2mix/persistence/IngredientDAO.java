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

                }
                else{
                    Toast.makeText(context, "Nome já existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void readAllIngredients() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //TODO Pegar todos os ingredientes sem critério
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Ingredient findByName(String nameParameter){

        name = nameParameter;

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    String dataName = data.child("name").getValue().toString();

                    if (name.equals(dataName)){
                        ingredient = data.getValue(Ingredient.class);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  ingredient;
    }

    public List<String> findAllNames(){
        ingredientsName = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    String ingredient = data.child("name").getValue().toString();
                    ingredientsName.add(ingredient);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  ingredientsName;
    }
}
