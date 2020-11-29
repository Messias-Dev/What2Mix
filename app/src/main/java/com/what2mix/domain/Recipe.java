package com.what2mix.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Recipe {

    // Atributos
    private String id;
    private String userId;
    private String title;
    private String description;
    private String createdAt;
    private List<String> ingredients;


    private List<String> usersLike;

    // Construtor geral (para Firebase)
    public Recipe() {
    }

    // Construtor para consulta no banco de dados
    public Recipe(String id, String userId, String title, String description, String createdAt, List<String> ingredients, List<String> usersLike) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredients = ingredients;
        this.usersLike = usersLike;
    }

    // Construtor para registro no banco de dados
    public Recipe(String userId, String title, String description, String createdAt, List<String> ingredients) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredients = ingredients;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getUsersLike() {
        return usersLike;
    }

    public void setUsersLike(List<String> usersLike) {
        this.usersLike = usersLike;
    }

    // Equals and HashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(getId(), recipe.getId()) &&
                Objects.equals(getUserId(), recipe.getUserId()) &&
                Objects.equals(getTitle(), recipe.getTitle()) &&
                Objects.equals(getDescription(), recipe.getDescription()) &&
                Objects.equals(getCreatedAt(), recipe.getCreatedAt()) &&
                Objects.equals(getIngredients(), recipe.getIngredients()) &&
                Objects.equals(getUsersLike(), recipe.getUsersLike());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTitle(), getDescription(), getCreatedAt(), getIngredients(), getUsersLike());
    }

    // toString

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", ingredients=" + ingredients +
                ", usersLike=" + usersLike +
                '}';
    }
}
