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

public class IngredientDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("ingredients");
    private Context context = null;
    private String nameExample = null;
    private Ingredient ingredient = null;
    private Boolean isDuplicated = false;


    public void writeNewIngredient(Context tela, String name) {
        nameExample = name;
        context = tela;

//        ingredient = new Ingredient(nameExample);
//        database.push().setValue(ingredient);

//        database.setValue(null);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String nameExist = snapshot.child("name").getValue().toString();
                        if (nameExample.equals(nameExist)) {
                            System.out.println("====================================");
                            System.out.println(nameExist + "  É DUPLICADO");
                            isDuplicated = true;
                            System.out.println("====================================");

                        }

                    }
                }
                if (!isDuplicated) {
                    ingredient = new Ingredient(nameExample);
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
                        System.out.println(snapshot.child("name"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
