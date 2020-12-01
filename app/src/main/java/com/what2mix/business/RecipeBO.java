package com.what2mix.business;

import androidx.core.content.ContextCompat;

import com.what2mix.R;
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
    public static Recipe validate(String userId, String title, String description, String createdAt, String createdBy, List<String> ingredients) throws InsufficientDataException, UserNotFoundException {

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

        Recipe recipe = new Recipe(userId, title, description, createdAt, createdBy, ingredients);

        return recipe;

    }

    public static Boolean userLiked(List<String> usersLiked, String userId) {
        if (usersLiked == null) {
            return false;
        } else if (usersLiked.contains(userId)) {
            return true;
        }
        return false;
    }
}
