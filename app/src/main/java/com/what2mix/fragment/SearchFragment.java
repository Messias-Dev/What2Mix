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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFragment extends Fragment {

    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private ImageView btAddIngredients;
    private AutoCompleteTextView actvIngredients;
    private Button btSearchRecipes;
    private RecyclerView rvSearchResult;
    private List<String> ingredientsNameList = null;
    private List<Ingredient> ingredientsList = ingredientBO.getAllIngredients();
    private Set<Ingredient> ingredientsSearch = new HashSet<>();
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

    private void getIngredientsName() {
        ingredientsNameList = new ArrayList<>();
        ingredientsNameList = ingredientBO.getAllIngredientsNames();
    }

    private void updateAutoComplete() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ingredientsNameList);
        actvIngredients.setAdapter(adapter);
    }

    private void setAutoComplete() {
        getIngredientsName();
        updateAutoComplete();
    }

    private void addView() {

        boolean conditional = false;

        System.out.println("===================== FRONT : Entrou no addView =====================");
        String ingredientName = actvIngredients.getText().toString();

        for (Ingredient ingredient : ingredientsList) {

            if (ingredient.getName().equals(ingredientName)) {

                // Adiciona na listView
                View ingredientItem = setView(ingredientName);
                ingredientsListView.addView(ingredientItem);

                // Adiciona na lista de busca
                ingredientsSearch.add(ingredient);
                conditional = true;
            }

        }

        if (conditional){
            // Imprime se encontrou
            System.out.println("================= ENCONTROU =================");
            Toast.makeText(getContext(), "Ingrediente selecionado !", Toast.LENGTH_LONG).show();
        }
        else{
            // Imprime se NÃO encontrou
            System.out.println("================= NÃO ENCONTROU =================");
            Toast.makeText(getContext(), "Ingrediente não existente !", Toast.LENGTH_LONG).show();
        }

    }

    private void removeView(View view) {
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
