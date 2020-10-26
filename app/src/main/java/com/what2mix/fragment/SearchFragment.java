package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.what2mix.R;
import com.what2mix.business.IngredientBO;
import com.what2mix.business.RecipeBO;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private ImageView btAddIngredients;
    private AutoCompleteTextView actvIngredients;
    private List<String> ingredientsList = new ArrayList<>();
    private LinearLayout ingredientsListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actvIngredients = view.findViewById(R.id.actvIngredients);
        btAddIngredients = view.findViewById(R.id.ivAddButton);
        ingredientsListView = view.findViewById(R.id.ingredientsListView);

        setAutoComplete();

        btAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

    }

    private void getIngredientsOnDatabase() {
        ingredientsList = ingredientBO.getAllIngredientsNames();
    }

    private void updateAutoComplete() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ingredientsList);
        actvIngredients.setAdapter(adapter);
    }

    private void setAutoComplete(){
        getIngredientsOnDatabase();
        updateAutoComplete();
    }

    private void addView() {
        View ingredientItem = getLayoutInflater().inflate(R.layout.ingredent_item, null, false);

        TextView ingredientName = ingredientItem.findViewById(R.id.tvIngredientName);
        ingredientName.setText(actvIngredients.getText().toString());
        actvIngredients.setText("");

        ingredientItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsListView.removeView(view);
            }
        });

        ingredientsListView.addView(ingredientItem);

    }


}
