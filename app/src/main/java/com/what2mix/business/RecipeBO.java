package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputSearchException;
import com.what2mix.exception.InsufficientDataException;
import com.what2mix.persistence.RecipeDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipeBO {

    // Referência a persistência
    private RecipeDAO dao = new RecipeDAO();



    // Pega todas as receitas por ingrediente via persistência
//    public List<Recipe> getAllRecipesByIngredients(List<Ingredient> ingredients) throws InputSearchException {
//
//        validateIngredients(ingredients);
//
//        // Lista para ID de ingredientes
//        List<String> ingredientsId = new ArrayList<>();
//
//        for (Ingredient ingredient : ingredients){
//            ingredientsId.add(ingredient.getId());
//        }
//
//        List<Recipe> recipes = dao.findAllByIngredients(ingredientsId);
//
//        return recipes;
//    }

    // Pega todas as receitas por usuário via persistência
    public List<Recipe> getAllRecipesByUser(String userId) throws InputSearchException {

        List<Recipe> recipes = dao.findAllByUserId(userId);

        return recipes;
    }

    // Valida parâmetros para criação da receita
    public static Recipe validate(String userId, String title, String description, String createdAt, List<String> ingredients) throws InputNameException, InsufficientDataException {

        // FIXME Revisar mensagens
        if (userId.equals(null)) {
            throw new InputNameException("Você não está logado");
        }
        if (title.equals(null) || title.trim().isEmpty()) {
            throw new InsufficientDataException("Título vazio");
        }
        if (description.equals(null) || description.trim().isEmpty()) {
            throw new InsufficientDataException("Descrição vazia");
        }
        if (ingredients.equals(null)) {
            throw new InsufficientDataException("Selecione ingredientes");
        }
        validateIngredients(ingredients);

        Recipe recipe = new Recipe(userId, title, description, createdAt, ingredients);

        return recipe;

    }

    // Valida especificamente ingredientes da receita
    private static void validateIngredients(List<String> ingredients) throws InsufficientDataException {

        if (ingredients.isEmpty()) {
            throw new InsufficientDataException("Escolha ao menos um ingrediente !");
        }
    }
}
