package com.what2mix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.R;
import com.what2mix.domain.Ingredient;
import com.what2mix.persistence.IngredientDAO;

// Classe improvisada para implementação de ingredientes no banco de dados
public class IngredientsImprovisedActivity extends AppCompatActivity {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("ingredients");
    private String name = null;
    private Boolean isDuplicated = false;
    private Ingredient ingredient = null;
    private EditText txtIngredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_improvised);
    }

    public void addIngredients(View view) {
        txtIngredientName = findViewById(R.id.txtIngredientName);

        name = txtIngredientName.getText().toString();

        writeNewIngredient();

        finish();
    }

    // Código improvisado para registro de ingredientes no banco de dados
    public void writeNewIngredient() {

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
                            Toast.makeText(getApplicationContext(), "Sucesso !", Toast.LENGTH_LONG).show();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Nome já existe", Toast.LENGTH_LONG).show();
                                }
                            });

                } else {
                    Toast.makeText(getApplicationContext(), "Nome já existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}