package com.what2mix.model;

import java.util.Objects;

public class Ingredients {

    private String ingredientName;
    private long ingredientId;

    public Ingredients(String ingredientName, long ingredientId) {
        this.ingredientName = ingredientName;
        this.ingredientId = ingredientId;
    }

    public Ingredients() {
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
        if (!(o instanceof Ingredients)) return false;
        Ingredients that = (Ingredients) o;
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
