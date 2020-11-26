package com.what2mix.business;

import android.os.AsyncTask;

import com.what2mix.domain.Ingredient;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.IngredientDAO;

import java.util.ArrayList;
import java.util.List;

public class IngredientBO {
    private IngredientDAO dao = new IngredientDAO();

    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredients = dao.findAll();
        return ingredients;
    }

    public List<String> getAllIngredientsNames(){
        List<String> ingredientsNames= dao.findAllNames();
        return ingredientsNames;
    }

}
