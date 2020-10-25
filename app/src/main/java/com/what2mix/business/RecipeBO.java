package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputNameException;
import com.what2mix.persistence.RecipeDAO;

import java.time.LocalDate;
import java.util.List;

public class RecipeBO {

    private RecipeDAO dao;

    public void register(String id, String title, String description, LocalDate createdAt, List<String> ingredients) throws InputNameException {
        validate(id, title, description, ingredients);
        Recipe recipe = new Recipe(id, title, description, createdAt, ingredients);
        dao.writeNewRecipe(recipe);
    }

    private void validate(String id, String title, String description, List<String> ingredients) throws InputNameException {

        // FIXME Revisar mensagens
        if (id.equals(null)){
            throw new InputNameException("Você não está logado");
        }
        if (title.equals(null) || title.trim().isEmpty()){
            throw new InputNameException("Título vazio");
        }
        if (description.equals(null) || description.trim().isEmpty()){
            throw new InputNameException("Descrição vazia");
        }
        if (ingredients.equals(null)){
            throw new InputNameException("Selecione ingredientes");
        }
    }
}
