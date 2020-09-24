package com.what2mix.domain;

import java.util.Objects;

public class Ingredient {

    private String ingredientName;
    private long ingredientId;

    public Ingredient(String ingredientName, long ingredientId) {
        this.ingredientName = ingredientName;
        this.ingredientId = ingredientId;
    }

    public Ingredient() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getIngredientId() == that.getIngredientId() &&
                getIngredientName().equals(that.getIngredientName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredientName(), getIngredientId());
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingredientName='" + ingredientName + '\'' +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
