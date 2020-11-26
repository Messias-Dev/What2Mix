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
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputSearchException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchFragment extends Fragment {

    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private ImageView btAddIngredients;
    private AutoCompleteTextView actvIngredients;
    private Button btSearchRecipes;
    private RecyclerView rvSearchResult;
    private LinearLayout ingredientsListView;
    private List<String> ingredientsNameList = null;
    private List<Ingredient> ingredientsList = ingredientBO.getAllIngredients();
    private List<Ingredient> ingredientsSearch = new ArrayList<>();
    private List<Recipe> recipesFound = new ArrayList<>();


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

        btSearchRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecipes();
            }
        });

    }

    private void getRecipes(){
        try {
            recipesFound = new RecipeBO().getAllRecipesByIngredients(ingredientsSearch);
            System.out.println("Tentei");
        } catch (InputSearchException e) {
            e.printStackTrace();
        }

        for (Recipe recipe : recipesFound) {
            System.out.println(recipe.getTitle());
        }

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

    // TODO Verificação para evitar itens duplicados
    private void addView() {
        String ingredientName = actvIngredients.getText().toString();
        Ingredient ingredient = verifyIngredient(ingredientName);

        if (ingredient != null) {
            View ingredientItem = setView(ingredientName);
            ingredientsListView.addView(ingredientItem);
            ingredientsSearch.add(ingredient);
            actvIngredients.setText("");
        } else {
            Toast.makeText(getContext(), "Ingrediente inválido!", Toast.LENGTH_LONG).show();
        }
    }

    private void removeView(View view) {
        ingredientsListView.removeView(view);
    }

    private View setView(final String name) {
        final View ingredientItem = getLayoutInflater().inflate(R.layout.ingredent_item, null, false);

        TextView ingredientName = ingredientItem.findViewById(R.id.tvIngredientName);
        ingredientName.setText(name);

        ingredientItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(view);
                removeIngredientFromList(name);
            }
        });

        return ingredientItem;
    }

    private Ingredient verifyIngredient(String s) {
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getName().equals(s)){
                return ingredient;
            }
        }

        return null;
    }

    private void removeIngredientFromList(String s){
        int i = 0;
        System.out.println(i);

        for (Ingredient ingredient : ingredientsSearch) {
            System.out.println(ingredient.getName());
        }

        for (Ingredient ingredient : ingredientsSearch){
            if (ingredient.getName().equals(s)){
                ingredientsSearch.remove(i);
                System.out.println("Removi o ingrediente " + ingredient.getName() + ", esperado: " + s);
                return;
            }
            i++;
        }


    }

    private void assignLayoutElements(View view) {
        actvIngredients = view.findViewById(R.id.actvIngredientsSearch);
        btAddIngredients = view.findViewById(R.id.ivAddButtonSearch);
        ingredientsListView = view.findViewById(R.id.ingredientsListViewSearch);
        btSearchRecipes = view.findViewById(R.id.btSearchRecipes);
        rvSearchResult = view.findViewById(R.id.rvSearchResults);
    }


}
