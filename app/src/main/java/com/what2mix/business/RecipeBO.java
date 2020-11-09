package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.RecipeDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipeBO {

    private RecipeDAO dao = new RecipeDAO();

    public void register(Recipe recipe) {
        dao.writeNewRecipe(recipe);
    }

    public List<Recipe> getAllRecipesByIngredients(List<Ingredient> ingredients) throws InputSearchException {

        validateIngredients(ingredients);
        List<Recipe> recipes = dao.findAllByIngredients(ingredients);

        return recipes;
    }

    public List<Recipe> getAllRecipesByUser(String userId) throws InputSearchException {

        List<Recipe> recipes = dao.findAllByUserId(userId);

        return recipes;
    }

    public Recipe validate(String userId, String title, String description, String createdAt, List<Ingredient> ingredients) throws InputNameException, InputSearchException {

        // FIXME Revisar mensagens
        if (userId.equals(null)) {
            throw new InputNameException("Você não está logado");
        }
        if (title.equals(null) || title.trim().isEmpty()) {
            throw new InputNameException("Título vazio");
        }
        if (description.equals(null) || description.trim().isEmpty()) {
            throw new InputNameException("Descrição vazia");
        }
        if (ingredients.equals(null)) {
            throw new InputNameException("Selecione ingredientes");
        }
        validateIngredients(ingredients);

        List<String> ingredientsId = new ArrayList<>();

        for (Ingredient ingredient : ingredients){
            ingredientsId.add(ingredient.getId());
        }

        Recipe recipe = new Recipe(userId, title, description, createdAt, ingredientsId);

        return recipe;
    }

    private void validateIngredients(List<Ingredient> ingredients) throws InputSearchException {

        if (ingredients.isEmpty()) {
            throw new InputSearchException("Escolha ao menos um ingrediente !");
        }
    }
}
