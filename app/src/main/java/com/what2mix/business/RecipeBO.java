package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.DataInsufficientException;
import com.what2mix.persistence.RecipeDAO;

import java.time.LocalDate;
import java.util.List;

public class RecipeBO {

    private RecipeDAO dao;

    public void register(Long id, String title, String description, LocalDate createdAt, List<Ingredient> ingredients) throws DataInsufficientException {
        validate(id, title, description, ingredients);
        Recipe recipe = new Recipe(id, title, description, createdAt, ingredients);
        dao.writeNewRecipe(recipe);
    }

    private void validate(Long id, String title, String description, List<Ingredient> ingredients) throws DataInsufficientException {

        // FIXME Revisar mensagens
        if (id.equals(null)){
            throw new DataInsufficientException("Você não está logado");
        }
        if (title.equals(null) || title.trim().isEmpty()){
            throw new DataInsufficientException("Título vazio");
        }
        if (description.equals(null) || description.trim().isEmpty()){
            throw new DataInsufficientException("Descrição vazia");
        }
        if (ingredients.equals(null)){
            throw new DataInsufficientException("Selecione ingredientes");
        }
    }
}
