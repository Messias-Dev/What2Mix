package com.what2mix.business;

import com.what2mix.persistence.IngredientDAO;

import java.util.ArrayList;
import java.util.List;

public class IngredientBO {
    private IngredientDAO dao = new IngredientDAO();

    public List<String> getAllIngredientsNames(){
        List<String> ingredientsNames= dao.findAllNames();
        return ingredientsNames;
    }
}
