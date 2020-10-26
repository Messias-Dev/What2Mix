package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.what2mix.R;
import com.what2mix.business.IngredientBO;
import com.what2mix.business.RecipeBO;
import com.what2mix.domain.Ingredient;
import com.what2mix.exception.InputSearchException;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private ImageView btAddIngredients;
    private AutoCompleteTextView actvIngredients;
    private Button btSearchRecipes;
    private RecyclerView rvSearchResult;
    private List<String> ingredientsNameList = new ArrayList<>();
    private List<Ingredient> ingredientsList = new ArrayList<>();

    private LinearLayout ingredientsListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignLayoutElements(view);
        setAutoComplete();

        btAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

    }

    private void getIngredientsOnDatabase() {
        ingredientsNameList = ingredientBO.getAllIngredientsNames();
    }

    private void updateAutoComplete() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ingredientsNameList);
        actvIngredients.setAdapter(adapter);
    }

    private void setAutoComplete(){
        getIngredientsOnDatabase();
        updateAutoComplete();
    }

    private void addView() {
        String ingredientName = actvIngredients.getText().toString();
        Ingredient ingredient = null;
        try {
             ingredient = ingredientBO.getIngredientByName(ingredientName);

        } catch (InputSearchException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (ingredient == null){
            Toast.makeText(getContext(), "Ingrediente nulo", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), ingredient.getName(), Toast.LENGTH_LONG).show();
        }


        View ingredientItem = setView(ingredientName);
        ingredientsListView.addView(ingredientItem);

    }

    private void removeView(View view){
        ingredientsListView.removeView(view);
    }

    private View setView(String name) {
        View ingredientItem = getLayoutInflater().inflate(R.layout.ingredent_item, null, false);

        TextView ingredientName = ingredientItem.findViewById(R.id.tvIngredientName);
        ingredientName.setText(name);

        ingredientItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               removeView(view);
            }
        });

        return ingredientItem;
    }

    private void assignLayoutElements(View view) {
        actvIngredients = view.findViewById(R.id.actvIngredients);
        btAddIngredients = view.findViewById(R.id.ivAddButton);
        ingredientsListView = view.findViewById(R.id.ingredientsListView);
        btSearchRecipes = view.findViewById(R.id.btSearchRecipes);
        rvSearchResult = view.findViewById(R.id.rvSearchResults);
    }



}
