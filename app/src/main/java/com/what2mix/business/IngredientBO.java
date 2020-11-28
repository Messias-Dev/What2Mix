package com.what2mix.business;

import android.os.AsyncTask;

import com.what2mix.domain.Ingredient;
import com.what2mix.exception.InputSearchException;
import com.what2mix.persistence.IngredientDAO;

import java.util.ArrayList;
import java.util.List;

public class IngredientBO {

       public static Boolean isDuplicated(List<String> ingredients, String ingredient){
        Boolean condition = false;

        if (ingredients.contains(ingredient)){
            condition = true;
        }

        return condition;
    }

}
