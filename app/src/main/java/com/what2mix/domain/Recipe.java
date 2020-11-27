package com.what2mix.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Recipe {

    // Atributos
    private String id;
    private String userId;
    private String title;
    private String description;
    private String createdAt;
    private List<String> ingredientsId;

    // Construtor geral (para Firebase)
    public Recipe() {
    }

    // Construtor para consulta no banco de dados
    public Recipe(String id, String userId, String title, String description, String createdAt, List<String> ingredientsId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredientsId = ingredientsId;
    }

    // Construtor para registro no banco de dados
    public Recipe(String userId, String title, String description, String createdAt, List<String> ingredientsId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredientsId = ingredientsId;
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

    public List<String> getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(List<String> ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return getId().equals(recipe.getId()) &&
                getUserId().equals(recipe.getUserId()) &&
                getTitle().equals(recipe.getTitle()) &&
                getDescription().equals(recipe.getDescription()) &&
                getCreatedAt().equals(recipe.getCreatedAt()) &&
                getIngredientsId().equals(recipe.getIngredientsId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTitle(), getDescription(), getCreatedAt(), getIngredientsId());
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
                ", ingredientsId=" + ingredientsId +
                '}';
    }
}
