package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.R;
import com.what2mix.business.IngredientBO;
import com.what2mix.business.RecipeBO;
import com.what2mix.config.FirebaseConfig;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InsufficientDataException;
import com.what2mix.exception.UserNotFoundException;
import com.what2mix.util.DateUtil;

import java.util.ArrayList;
import java.util.List;


public class CreateFragment extends Fragment {

    private Button btCreate;
    private EditText etRecipeTitle, etRecipeDescription;
    private IngredientBO ingredientBO = new IngredientBO();
    private AutoCompleteTextView actvIngredients;
    private LinearLayout ingredientsListView;
    private FirebaseAuth auth = FirebaseConfig.getFirebaseAuth();
    private String userId = null;
    private List<String> ingredientsList = new ArrayList<>();
    private List<String> selectedIngredientsList = new ArrayList<>();
    private ArrayAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignLayoutElements(view);
        getIngredientsName();


        actvIngredients.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedIngredient = adapter.getItem(position).toString();
                addIngredient(selectedIngredient);
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserAndCreate();
            }
        });

    }

    private void createRecipe() {
        DatabaseReference recipeRef = FirebaseConfig.getFirebaseReference().child("recipes");

        String date = DateUtil.getDate();
        String title = etRecipeTitle.getText().toString();
        String description = etRecipeDescription.getText().toString();
        String name = auth.getCurrentUser().getDisplayName();

        Recipe recipe = null;
        try {
            recipe = RecipeBO.validate(userId, title, description, date, name, selectedIngredientsList);
            recipeRef.push().setValue(recipe);
            clearAll();
            Toast.makeText(getContext(), "Receita criada com sucesso!", Toast.LENGTH_LONG).show();
        } catch (InsufficientDataException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (UserNotFoundException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void addIngredient(String ingredientName){
        View ingredientItem = setIngredientView(ingredientName);

        if (ingredientItem != null) {
            selectedIngredientsList.add(ingredientName);
            ingredientsListView.addView(ingredientItem);
        } else {
            Toast.makeText(getContext(), "Ingrediente inválido ou repetido", Toast.LENGTH_LONG).show();
        }

        actvIngredients.setText("");
        for (String s : selectedIngredientsList) {
            System.out.println(s);
        }
    }

    private View setIngredientView(final String selectedIngredient) {
        if (!IngredientBO.isDuplicated(selectedIngredientsList, selectedIngredient)) {
            View ingredientItem = getLayoutInflater().inflate(R.layout.ingredent_item, null, false);

            TextView ingredientName = ingredientItem.findViewById(R.id.tvIngredientName);
            ingredientName.setText(selectedIngredient);

            ingredientItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeIngredient(view, selectedIngredient);
                }
            });

            return ingredientItem;
        }

        return null;
    }

    private void removeIngredient(View view, String ingredient) {
        ingredientsListView.removeView(view);
        selectedIngredientsList.remove(ingredient);
    }

    private void updateAutoComplete () {
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ingredientsList);
        actvIngredients.setAdapter(adapter);
        actvIngredients.setText("");
        actvIngredients.setEnabled(true);
    }

    private void getIngredientsName() {
        DatabaseReference ingredientsRef = FirebaseConfig.getFirebaseReference().child("ingredients");

        ingredientsRef.orderByChild("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ingredientsList.add(data.child("name").getValue().toString());
                }
                updateAutoComplete();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUserAndCreate() {
        String email = auth.getCurrentUser().getEmail();
        DatabaseReference databaseRef = FirebaseConfig.getFirebaseReference().child("users");

        databaseRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    userId = data.getKey().toString();
                    createRecipe();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });

    }

    private void clearAll() {
        ingredientsListView.removeAllViews();
        selectedIngredientsList = new ArrayList<>();
        etRecipeTitle.setText("");
        etRecipeDescription.setText("");
    }

    private void assignLayoutElements(View view) {
        btCreate = view.findViewById(R.id.btCreateRecipe);
        etRecipeDescription = view.findViewById(R.id.etRecipeDescription);
        etRecipeTitle = view.findViewById(R.id.etRecipeTitle);
        ingredientsListView = view.findViewById(R.id.ingredientsListViewCreate);
        actvIngredients = view.findViewById(R.id.actvIngredientsCreate);
    }
}
