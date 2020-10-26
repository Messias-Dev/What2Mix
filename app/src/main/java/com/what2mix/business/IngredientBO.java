package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.IngredientDAO;

import java.util.ArrayList;
import java.util.List;

public class IngredientBO {
    private IngredientDAO dao = new IngredientDAO();

    public List<String> getAllIngredientsNames(){
        List<String> ingredientsNames= dao.findAllNames();
        return ingredientsNames;
    }

    public Ingredient getIngredientByName(String name) throws InputSearchException {

        validateIngredientName(name);

        Ingredient ingredient = dao.findByName(name);

        return ingredient;
    }

    private void validateIngredientName(String name) throws InputSearchException {
        if (name.equals(null) || name.trim().isEmpty()){
            throw new InputSearchException("Campo vazio !");
        }
    }
}
