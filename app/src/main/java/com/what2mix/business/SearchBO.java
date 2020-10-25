package com.what2mix.business;

import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.RecipeDAO;

import java.util.List;

public class SearchBO {

    private RecipeDAO recipeDAO = new RecipeDAO();

    public List<Recipe> findAllRecipesByIngredients(List<String> ingredients) throws InputSearchException {

        validateIngredients(ingredients);
        recipeDAO.findAllByIngredients(ingredients);


        return null;
    }

    private void validateIngredients(List<String> ingredients) throws InputSearchException {

        if (ingredients.isEmpty()){
            throw new InputSearchException("Escolha ao menos um ingrediente !");
        }


        for (String ingredient : ingredients){

        }
    }
}
