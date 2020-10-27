package com.what2mix.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Recipe {

    private String id;
    private String userId;
    private String title;
    private String description;
    private LocalDate createdAt;
    private List<String> ingredientsId;

    public Recipe() {
    }

    public Recipe(String id, String userId, String title, String description, LocalDate createdAt, List<String> ingredientsId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredientsId = ingredientsId;
    }

    public Recipe(String userId, String title, String description, LocalDate createdAt, List<String> ingredientsId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.ingredientsId = ingredientsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getingredientsId() {
        return ingredientsId;
    }

    public void setingredientsId(List<String> ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

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
                getingredientsId().equals(recipe.getingredientsId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTitle(), getDescription(), getCreatedAt(), getingredientsId());
    }
}
