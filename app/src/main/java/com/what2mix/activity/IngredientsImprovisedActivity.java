package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.what2mix.R;
import com.what2mix.persistence.IngredientDAO;

public class IngredientsImprovisedActivity extends AppCompatActivity {

    private IngredientDAO dao = new IngredientDAO();
    private EditText txtIngredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_improvised);
    }

    public void addIngredients(View view) {
        txtIngredientName = findViewById(R.id.txtIngredientName);

        String name = txtIngredientName.getText().toString();

       if (dao.writeNewIngredient(name)){
           Toast.makeText(getApplicationContext(), "Nome já existe !", Toast.LENGTH_LONG).show();
       }
    }
}