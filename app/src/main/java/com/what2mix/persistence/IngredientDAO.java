package com.what2mix.persistence;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.activity.IngredientsImprovisedActivity;
import com.what2mix.domain.Ingredient;

public class IngredientDAO {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("ingredients");
    private String nameExample = null;
    private Ingredient ingredient = null;
    private Boolean isDuplicated = false;


    public Boolean writeNewIngredient(String name) {
        nameExample = name;
        isDuplicated = false;

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
                            System.out.println(nameExist+"  É DUPLICADO");
                            isDuplicated = true;
                            System.out.println("====================================");

                        }

                    }
                }
                if (!isDuplicated){
                    ingredient = new Ingredient(nameExample);
                    database.push().setValue(ingredient);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return isDuplicated;

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
