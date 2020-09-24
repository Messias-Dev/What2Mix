package com.what2mix.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Recipe {

    private long id;
    private String name;
    private User owner;
    private LocalDate creationDate;
    private List<Ingredient> ingredients;

    public Recipe(long id, String name, User owner, LocalDate creationDate, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.creationDate = creationDate;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return getId() == recipe.getId() &&
                Objects.equals(getName(), recipe.getName()) &&
                Objects.equals(getOwner(), recipe.getOwner()) &&
                Objects.equals(getCreationDate(), recipe.getCreationDate()) &&
                Objects.equals(getIngredients(), recipe.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOwner(), getCreationDate(), getIngredients());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + id +
                ", recipeName='" + name + '\'' +
                ", owner=" + owner +
                ", creationDate=" + creationDate +
                ", ingredients=" + ingredients +
                '}';
    }
}
