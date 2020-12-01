package com.what2mix.business;

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
