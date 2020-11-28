package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.what2mix.R;
import com.what2mix.business.IngredientBO;
import com.what2mix.business.RecipeBO;
import com.what2mix.config.FirebaseConfig;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private AutoCompleteTextView actvIngredients;
    private Button btSearchRecipes;
    private RecyclerView rvSearchResult;
    private LinearLayout ingredientsListView;

    private List<String> ingredientsList = new ArrayList<>();
    private List<String> selectedIngredientsList = new ArrayList<>();
    private ArrayAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

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

        btSearchRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecipesByIngredients();
            }
        });

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

    private void getRecipesByIngredients() {
        DatabaseReference recipesRef = FirebaseConfig.getFirebaseReference().child("recipes");

        recipesRef.orderByChild("ingredients");

    }


    private void assignLayoutElements(View view) {
        actvIngredients = view.findViewById(R.id.actvIngredientsSearch);
        ingredientsListView = view.findViewById(R.id.ingredientsListViewSearch);
        btSearchRecipes = view.findViewById(R.id.btSearchRecipes);
        rvSearchResult = view.findViewById(R.id.rvSearchResults);
    }


}
