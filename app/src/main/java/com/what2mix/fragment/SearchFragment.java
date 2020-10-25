package com.what2mix.fragment;

import android.os.Bundle;
import android.view.Gravity;
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
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private IngredientBO bo = new IngredientBO();
    private ImageView btAddIngredients;
    private AutoCompleteTextView actvIngredients;
    private List<String> ingredientsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actvIngredients = view.findViewById(R.id.actvIngredients);

        setAutoComplete();

    }

    private void getIngredientsOnDatabase() {
        ingredientsList = bo.getAllIngredientsNames();
    }

    private void updateAutoComplete() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ingredientsList);
        actvIngredients.setAdapter(adapter);
    }

    private void setAutoComplete(){
        getIngredientsOnDatabase();
        updateAutoComplete();
    }


}
