package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.IngredientDAO;
import com.what2mix.persistence.RecipeDAO;

import java.util.List;

public class SearchBO {

    private RecipeDAO recipeDAO = new RecipeDAO();
    private IngredientDAO ingredientDAO = new IngredientDAO();

    public List<Recipe> findAllRecipesByIngredients(List<String> ingredients) throws InputSearchException {

        validateIngredients(ingredients);
        recipeDAO.findAllByIngredients(ingredients);


        return null;
    }

    public Ingredient findIngredientByName(String name) throws InputSearchException {

        validateIngredient(name);

        Ingredient ingredient = ingredientDAO.findByName(name);

        return ingredient;
    }

    private void validateIngredients(List<String> ingredients) throws InputSearchException {

        if (ingredients.isEmpty()){
            throw new InputSearchException("Escolha ao menos um ingrediente !");
        }
    }

    private void validateIngredient(String name) throws InputSearchException {
        if (name.equals(null) || name.trim().isEmpty()){
            throw new InputSearchException("Campo vazio !");
        }
    }
}
