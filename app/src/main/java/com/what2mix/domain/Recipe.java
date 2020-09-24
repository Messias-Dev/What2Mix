package com.what2mix.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Recipe {

    private long recipeId;
    private String recipeName;
    private User owner;
    private LocalDate creationDate;
    private List<Ingredient> ingredients;

    public Recipe(long recipeId, String recipeName, User owner, LocalDate creationDate, List<Ingredient> ingredients) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.owner = owner;
        this.creationDate = creationDate;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return getRecipeId() == recipe.getRecipeId() &&
                Objects.equals(getRecipeName(), recipe.getRecipeName()) &&
                Objects.equals(getOwner(), recipe.getOwner()) &&
                Objects.equals(getCreationDate(), recipe.getCreationDate()) &&
                Objects.equals(getIngredients(), recipe.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getRecipeName(), getOwner(), getCreationDate(), getIngredients());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", owner=" + owner +
                ", creationDate=" + creationDate +
                ", ingredients=" + ingredients +
                '}';
    }
}
