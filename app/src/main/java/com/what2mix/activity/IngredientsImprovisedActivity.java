package com.what2mix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.what2mix.R;
import com.what2mix.persistence.IngredientDAO;

// Classe improvisada para implementação de ingredientes no banco de dados
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

        dao.writeNewIngredient(getApplicationContext(), name);
        finish();
    }
}