package com.what2mix.business;

import com.what2mix.domain.Ingredient;
import com.what2mix.domain.Recipe;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputSearchException;
import com.what2mix.exception.InsufficientDataException;
import com.what2mix.exception.UserNotFoundException;
import com.what2mix.persistence.RecipeDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecipeBO {

    // Valida parâmetros para criação da receita
    public static Recipe validate(String userId, String title, String description, String createdAt, List<String> ingredients) throws InsufficientDataException, UserNotFoundException {

        // FIXME Revisar mensagens
        if (userId.equals(null)) {
            throw new UserNotFoundException("Você não está logado");
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
        if (ingredients.isEmpty()) {
            throw new InsufficientDataException("Escolha ao menos um ingrediente !");
        }

        Recipe recipe = new Recipe(userId, title, description, createdAt, ingredients);

        return recipe;

    }
}
