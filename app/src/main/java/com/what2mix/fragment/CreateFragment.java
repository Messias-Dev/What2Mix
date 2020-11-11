package com.what2mix.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.what2mix.R;
import com.what2mix.business.IngredientBO;
import com.what2mix.business.RecipeBO;
import com.what2mix.business.UserBO;
import com.what2mix.domain.Ingredient;
import com.what2mix.domain.User;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputSearchException;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CreateFragment extends Fragment {

    private Button btCreate;
    private EditText etRecipeTitle, etRecipeDescription;
    private ImageView ivAddButtonCreate;
    private IngredientBO ingredientBO = new IngredientBO();
    private RecipeBO recipeBO = new RecipeBO();
    private UserBO userBO = new UserBO();
    private AutoCompleteTextView actvIngredients;
    private LinearLayout ingredientsListView;
    private List<String> ingredientsNameList = null;
    private List<Ingredient> ingredientsList = ingredientBO.getAllIngredients();
    private List<Ingredient> ingredientsCreate = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignLayoutElements(view);
        setAutoComplete();

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRecipe();
            }
        });

        ivAddButtonCreate.setOnClickListener(new View.OnClickListener() {
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

    // TODO Verificação para evitar itens duplicados
    private void addView() {
        String ingredientName = actvIngredients.getText().toString();
        Ingredient ingredient = verifyIngredient(ingredientName);

        if (ingredient != null) {
            View ingredientItem = setView(ingredientName);
            ingredientsListView.addView(ingredientItem);
            ingredientsCreate.add(ingredient);
            actvIngredients.setText("");
        } else {
            Toast.makeText(getContext(), "Ingrediente inválido!", Toast.LENGTH_LONG).show();
        }
    }

    private void removeView(View view) {
        ingredientsListView.removeView(view);
    }

    private View setView(final String name) {
        View ingredientItem = getLayoutInflater().inflate(R.layout.ingredent_item, null, false);

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
            if (ingredient.getName().equals(s)) {
                return ingredient;
            }
        }

        return null;
    }

    private void removeIngredientFromList(String s) {
        int i = 0;
        System.out.println(i);

        for (Ingredient ingredient : ingredientsCreate) {
            System.out.println(ingredient.getName());
        }

        for (Ingredient ingredient : ingredientsCreate) {
            if (ingredient.getName().equals(s)) {
                ingredientsCreate.remove(i);
                System.out.println("Removi o ingrediente " + ingredient.getName() + ", esperado: " + s);
                return;
            }
            i++;
        }


    }


    private void assignLayoutElements(View view) {
        btCreate = view.findViewById(R.id.btCreateRecipe);
        ivAddButtonCreate = view.findViewById(R.id.ivAddButtonCreate);
        etRecipeDescription = view.findViewById(R.id.etRecipeDescription);
        etRecipeTitle = view.findViewById(R.id.etRecipeTitle);
        ingredientsListView = view.findViewById(R.id.ingredientsListViewCreate);
        actvIngredients = view.findViewById(R.id.actvIngredientsCreate);
    }

    private void clearAllInputs() {
        etRecipeDescription.setText("");
        etRecipeTitle.setText("");
        ingredientsCreate = new ArrayList<>();
        ingredientsListView.removeAllViews();

    }


    private void createRecipe() {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String createdAt = formatter.format(calendar.getTime());

        User user = userBO.getLoggedUser(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        System.out.println(user);

        try {
            recipeBO.register("1234", etRecipeTitle.getText().toString(), etRecipeDescription.getText().toString(), createdAt, ingredientsCreate);
            clearAllInputs();
        } catch (InputNameException e) {
            // TODO imprimir exceções
            e.printStackTrace();
        } catch (InputSearchException e) {
            // TODO imprimir exceções
            e.printStackTrace();
        }
    }

}
